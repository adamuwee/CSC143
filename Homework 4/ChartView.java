import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */
public class ChartView extends JPanel implements StockView
{

    //The model to be displayed
    private StockChart model;
    // Graph Height and Width
    private int gWidth, gHeight;
    // JFrame height and Width
    private int width, height;
    // Month Range for current View
    private int monthRange;
    // Width and Height of individual rectangle width and Space width
    private double rectWidth,spaceWidth;
	
	// X coordinate for selection line
	private int xLine;
	
	// X coordinates for highLight rectangle
	private int start, stop;
    
    /**
     * Constructor for objects of class ChartView
     * @param model StockChart model to be displayed in JFrame
     */
    public ChartView(StockChart model)
    {
        this.setBackground(Color.gray);
        this.model = model;
     }
     
     /**
      * Draws the components of the Graph
      * @param g Graphics Element
      */
     public void paintComponent(Graphics g)
     {
         super.paintComponent(g);
         this.setBackground(Color.gray);
         //Do not create a view if the model is null
         if (this.model == null){             
             return;             
            }
          //Use 2D features
          Graphics2D g2D =  (Graphics2D) g;
          g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
          
          //Get the width and hight of the drawing are
          this.height = this.getHeight();
          this.width = this.getWidth();

          //The dimensions of the table 
          this.gWidth = (int)(  this.width * 0.8 );
          this.gHeight = (int)( this.height  * 0.8 );
          // Draw Shadow rectangle and graph rectangle
          g.setColor(Color.black);
          g.fillRect( (int)(width * 0.1) + 4, (int)(height * 0.1)  + 4,  this.gWidth, gHeight);
          g.setColor(Color.yellow);
          g.fillRect( (int)(width * 0.1), (int)(height * 0.1),  this.gWidth, gHeight);
          
          // Draw each aspect of the graph
          drawDates(g);
          drawGrid(g);
          drawGraphScale(g);     
          drawGraph(g);
          drawQtrEarnings(g);
		  drawLine(g);
		  drawHighLight(g);
		  
     }
     
     /**
      * Draws the data from the model
      * Automatically resizes and scales the data when the window is changed
      * @param g Graphics Element
      */
     public void drawGraph(Graphics g) 
     {
         // ArrayList of DailyStockPrices and Quarterly Earnings
         ArrayList<DailyStockPrice> dailyPrices = this.model.getDailyStockPrices();
         ArrayList quarterlyPrices = this.model.getQuarterlyStockPrices();
         
         // Get values that are 10% of the JFrame height ( MAX height )
         int graphHeight = gHeight;
         int yTop = (int)(height * 0.1);
         int yBot = (int)(yTop + graphHeight);
         
         // Get the Highest High and Lowest Low
         double hHigh = this.model.getHighestHigh();
         double lLow = this.model.getLowestLow();
         
         // Draw each data point for each DailyStockPrice from the Model
         double counter = 0;
         for(DailyStockPrice d : dailyPrices) {
             // Get High, Low, and close for current DailyStockPrice
             double high = (d.getHigh());
             double low = (d.getLow());
             double close = (d.getClose());

             // The graphRange is the ratio between the range of 
             // the DailyStockPrices and the height of the graph
             double graphRange = ( (double)graphHeight / ( hHigh - lLow )  );
             
            // Get three data components for each DailyStockPrice and Multiply by graphRange
            double top = yTop + (double)(hHigh - high) * graphRange;      
            double bottom = (yTop + graphHeight) - (low - lLow)*graphRange;
            double closeLine = top + (double)(high - close) * graphRange;
             
            // Horizontal Alignment
            // Width of each rectangle plus space
            double n = (double)model.getDailyPriceLength();  // Number of data Points
            int L = this.gWidth;  // Length of Graph
            
            // Ratio between the Length of Graph and number of data points
            // This will give us an approximate number of divisions that can be made into the
            // given graph size.
            double t = ( L / n );

            // Size of each Rectangle drawn on the screen
            rectWidth = (2 * t / 3.0);

            // Size of space between each rectangle ( The Space width is a percentage of the rectangle width )
            spaceWidth = ( (L - rectWidth * model.getDailyPriceLength()) / (model.getDailyPriceLength())    );
            
            // Initial X position
            int omega = (int)(width * 0.1) + 1;
            
            // Inital x component and final x component for each rectangle
            int x0 = (int)( omega + counter);
            int x1 = (int)( rectWidth );
            
            // Draw Rectangle
            g.setColor(Color.blue);
            g.fillRect(x0 , (int)top, x1, (int)(bottom - top) );
            
            // Increase counter by the sum of the rectangle width and the space width
            counter += (rectWidth + spaceWidth);
            
            // Draw the Close Line indicator
            g.setColor(Color.black);
            g.drawLine(x0-1,  (int)closeLine ,x0+1, (int)closeLine);
            }
        }
    /**
     * Draws the Vertical Scale on the right hand side of the graph
     * @param g Graphics Element
     */
    public void drawGraphScale(Graphics g) 
       {
           
         // Get High, Low, and close for current DailyStockPrice
         double hHigh = (this.model.getHighestHigh());
         double lLow = (this.model.getLowestLow());
         double increase = (hHigh - lLow)/2;
        
         //the dimensions for the lables of the graph
         double x1 = ((width * 0.9) + 7);

         //The y values for the indicators
         double yhigh = (((height) * 0.1 )+ 10);
         double ylow = this.gHeight + yhigh - 10;      
         double ymid = yhigh + ((this.gHeight)/2)- 5; 
 
         // Y values for 5 indicators
         double y1 = yhigh + ((ymid - yhigh)/2);
         double y2 = ymid + ((ylow - ymid)/2);
          
         DecimalFormat df = new DecimalFormat("0.00");
         //The highest value of the graph
         String high = new String(df.format(hHigh));
     
         //The lowest value of the graph
         String low = new String( df.format(lLow) );
         
         String middle = new String( df.format(hHigh - (increase)) );
          
         //Aditional values for 5 indicator
         String mY1 = new String( df.format(hHigh - (increase / 2) + 1.25 ) );

         String mY2 = new String( df.format(lLow + (increase / 2) - 1.25) );
          
         //The color of the text for the graph
         g.setColor(Color.blue);
         // If the graph height is less than 100 pixels then only 3 labels are viewable
         // This is done to prevent clustering and for readability of the data
         if (this.gHeight <= 100){
             g.drawString("$"+high,(int)x1, (int)yhigh); 
             g.drawString("$"+middle,(int)x1, (int)ymid); 
             g.drawString("$"+low,(int)x1,(int)ylow);       
           }
          else{
               g.drawString("$"+high,(int)x1, (int)yhigh);
               g.drawString("$"+mY1,(int)x1, (int)y1);
               g.drawString("$"+middle,(int)x1, (int)ymid); 
               g.drawString("$"+mY2,(int)x1, (int) y2); 
               g.drawString("$"+low,(int)x1,(int)ylow);     
           }              
       }

    /**
     * Draws the dates on the bottom of the graph according to the range
     * @param g Graphics Element
     */
    private void drawDates(Graphics g) {
     // Creates a temp array for all of the months read from the StockChart Model
     int[] tempMonths = new int[model.getDailyPriceLength()];
     // Creates an Array of the possible months to be viewed.  A Maximum of 12 months
     int[] months = new int[12];
     // Get the array of dailyStockPrices from the model
     ArrayList<DailyStockPrice> dsp = model.getDailyStockPrices();
     int k = 0;
     for(DailyStockPrice d : dsp) {
         // Finds the month embedded in the string given by the DailyStockPrice
         String[] dates = ( d.getDate() ).split("/");
         // The first array element is the month
         String month = dates[0];
         // Use a wrapper to convert the String to a primitive type integer
         Integer i = new Integer(month);
         // Add interger to tempMonths Array
         tempMonths[k] = i.intValue();
         k++;
        }
    // Get range of months, It can be assumed that months will not be skipped 
    // and date will be continuous.
    int firstMonth = tempMonths[0];
    int lastMonth = tempMonths[tempMonths.length - 1];
    // Range of Months viewed
    monthRange = lastMonth - firstMonth;
    
    // For each month that there is data available, draw on the graph at the appropriate position
    // Index of current month
    int idx = 0;
    for( int i = firstMonth; i < lastMonth; i++) {
        months[idx] = i;
    }
    // Draw Correct Months on Graph
    String[] strMonths = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    // Graph Width
    int graphWidth = this.gWidth;
    // Initial X Position
    double x0 = width * 0.1;
    // Initial Y Position
    double y0 = height * 0.9 + 0.9 * 18;
    // Iterate through the range of months
    double counter = 0;
    for (int i = firstMonth - 1; i <= lastMonth - 1; i++) {
        double x = x0 + counter;
        double y = y0;
        g.setColor(Color.blue);
        g.drawString(strMonths[i],(int)x,(int)y );
        // Increase by the ratio between the graphWidth and the number of months shown
        counter += (graphWidth/months.length);
    }
    
    }

    /**
     * Returns the Height of the JFrame for testing purposes
     */
    public int getHeightTest() {
        return height;
    }

    /**
     * Returns the Width of the JFrame for testing purposes
     */
    public int getWidthTest() {
        return this.width;
    }

    /**
     * Returns the Height of the Graph for testing purposes 
     */
    public int getGraphHeight() {
        return this.gHeight;
        }
    /**
     * Returns the Width of the Graph for testing purposes
     */
    public int getGraphWidth() {
        return this.gWidth;
    }
    /**
     * Draw the background Grid
     */
    private void drawGrid(Graphics g) {
    int graphWidth = this.gWidth;
    double x0 = (this.width * 0.1);
    double y0 = height * 0.1;
    double y1 = height * 0.9 - height* 0.004;
    
    double counter = 0;
    // Draws vertical lines above the month
        for (int i = 0; i <= monthRange; i++) {
        double x = x0 + counter;
        double y = y0;
        Color c = new Color(220,220,100);
        g.setColor(c);
        g.drawLine((int)x, (int)y0, (int)x, (int)y1 );
        counter += (graphWidth/ ( monthRange + 1 ) );
        
    }
  }
  
  /**
   * Draw Quarterly Earnings on Graph.  Quarterly Earnings are represented by triangles
   * @param g Graphics Element
   */
  private void drawQtrEarnings(Graphics g) {
      ArrayList<QuarterlyStockEarnings> qse = model.getQuarterlyStockPrices();
      ArrayList<DailyStockPrice> dspTotal = model.getDailyStockPrices();
      /**
       * get dailyPriceStocks for each quarterly earnings day
       * multiply index by width of rect and space to get proper x position
       * do similar calculation to get y coord.
       */
      
      ArrayList<DailyStockPrice> dsp = new ArrayList();
      // Get the DailyStockPrices that occur on earnings date.
      StringBuffer[] dates = null;
      
      // Put Earnings days into an ArrayList
      for(QuarterlyStockEarnings q : qse) {
          dsp.add( model.getDailyStockPriceDate( q.getDate() ) );     
        }
 
      // Draw Triangle
      for( QuarterlyStockEarnings q : qse) {
          String date = q.getDate();
          DailyStockPrice d = model.getDailyStockPriceDate(date);
          int index = model.getDailyStockPriceIndex(date);
          double earnings = q.getEarnings();
          // Position where the triangle will be drawn
          double x0 = ( width * 0.1 ) + (index+1) * (rectWidth + spaceWidth);
          // Y Position is determined to be an arbitrary point between the close and the top of the graph
          double graphRatio = gHeight / ( model.getHighestHigh()-model.getLowestLow() );
          double y0 = ( height * 0.1 ) + (model.getHighestHigh() - d.getHigh() ) *graphRatio;
          // Draw trianlge based on positive, negative, or same earnings
          // Positive Earnings
          // Draw triangle up
          if (q.getTrend().equals("up")) {
              drawTriangle( g,(int)(x0), (int)(y0 - 5), (int)(x0 - 5), (int)(y0 + 5), (int)(x0 + 5), (int)(y0 + 5),Color.green);
            }
            // Negative Earnings
          else if (q.getTrend().equals("down")) {
              drawTriangle( g,(int)(x0), (int)(y0 + 5), (int)(x0 + 5), (int)(y0 - 5), (int)(x0 - 5), (int)(y0 - 5),Color.red);
            }
            // Zero Earnings
            else{
                drawTriangle( g,(int)(x0), (int)(y0 - 5), (int)(x0), (int)(y0 + 5), (int)(x0 + 5), (int)(y0),Color.red);
            }
            // Draw The Value of the Earnings
            g.setColor(Color.black);
            
            Double i = new Double(q.getEarnings());
            String e = new String( i.toString() );
            g.drawString( e,(int)(x0 + 5),(int)(y0 - 5));
        }
    }
    
    /**
     * Draws a triangle using Java's Graphics Class
     * @param g Graphics Element
     * @param x0 x0 Coordinate of Triangle
     * @param y0 y0 Coordinate of Triangle
     * @param x1 x1 Coordinate of Triangle
     * @param y1 y1 Coordinate of Triangle
     * @param x2 x2 Coordinate of Triangle
     * @param y2 y2 Coordinate of Triangle
     * @param color Color of Triangle
     */
    private void drawTriangle(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2, Color color) {
        int[] x = {x0, x1, x2};
        int[] y = {y0, y1, y2};
        g.setColor(color);
        g.fillPolygon(x,y,3);
    }

     /**
     * Notifies StockChart (the model) update its display of the StockView.
     * @param m StockChart notifying this ChartView
     */
     public void notify(StockChart m)
     {
         this.model = m;         
         this.repaint();         
     }
	 
	 private void drawLine(Graphics g) {
		 g.setXORMode(Color.yellow);
		 g.drawLine(this.xLine,(int)(this.height*0.1),this.xLine, (int)(this.height*0.1) + gHeight);		 
	 }
	 
	 public void setLine(int x){
		 this.xLine = x;
	 }
	 
	 public void setStart(int s){
		 this.start = s;
	 }
	 
	 public void setStop(int s){
		 this.stop = s;
	 }
	 private void drawHighLight(Graphics g) {
		 g.setXORMode(Color.YELLOW);
		 
		 g.fillRect(this.start,(int)(this.height*0.1),
				 	this.stop-this.start,(int)(this.height*0.1) + gHeight);
		 
		 
	 }
	 public int getStart() {
		 return this.start;
	 }
	 public int getStop() {
		 return this.stop;
	 }
}
    