import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MouseHandler extends MouseInputAdapter implements MouseListener  {
    ChartView view;
    ArrayList comp = new ArrayList();
    ArrayList<Integer> rectPoints = new ArrayList();
    StockChart model;
    TextView textView;
    ArrayList<DailyStockPrice> dsp = new ArrayList();
    ArrayList<QuarterlyStockEarnings> qse = new ArrayList();
    String first, last;
    
    public MouseHandler(ChartView view, ArrayList comp, TextView textView) {
        this.view = view;   
        this.comp = comp;
        rectPoints = view.getRectPoints();
        model = view.getModel();
        this.textView = textView;
        dsp = model.getDailyStockPrices();
        qse = model.getQuarterlyStockPrices();
  
    }
   /**
    * MouseCliked
    * Use to get the location of where the mouse was clicked
    * @param x the x-coordinate of the click
    * @param y the y-coordinate of the click
    */     
   public void mouseClicked(MouseEvent e) {
        
    }
 

  /**
    * MousePressed
    * Use to get the location of where the mouse was pressed
    * @param x the x-coordinate of the click
    * @param y the y-coordinate of the click
    */     
    public void mousePressed(MouseEvent e){
       // Get Mouse Coordinates
        int x = e.getX();
        int y = e.getY();
        
        // Get Graph Coordinates
        int leftSide = (int)(view.getWidthTest() * 0.1);
        int rightSide = leftSide + (int)(view.getWidthTest() * 0.8);
        int top = (int)(view.getHeightTest()*.36);
        int bottom = top + view.getGraphHeight(); 
        int graphOffset = (int)(view.getWidthTest() * .1 );
        
        if (x > leftSide && x < rightSide && y < bottom && y > top){
            
            int point = (int)( ( x - graphOffset) * ( model.getDailyPriceLength() / (double)view.getGraphWidth() ) );
            int line = (int)(  point *( view.getSpaceWidth() + view.getRectWidth()) ) + graphOffset;
            DailyStockPrice d = dsp.get(point);
            
            ((JLabel)comp.get(0)).setText( "Date: " + d.getDate() );
            ((JLabel)comp.get(1)).setText( "Close: $" + (new Double(d.getClose() )).toString() );
            ((JLabel)comp.get(2)).setText( "High: $" + (new Double(d.getHigh() )).toString() );
            ((JLabel)comp.get(3)).setText( "Low: $" + (new Double(d.getLow() )).toString() );
            this.first = d.getDate();
            this.view.setStart((int)( line + view.getRectWidth() / 2 ) );
            this.view.setStop((int)( line + view.getRectWidth() / 2 ) );
        }
        else {
            
            this.view.setLine(graphOffset);
           
        }
         this.view.repaint();
    }
    
    /**
    * MouseReleased
    * Use to get the location of where the mouse was pressed
    * @param x the x-coordinate of the click
    * @param y the y-coordinate of the click
    */    
    public void mouseReleased(MouseEvent e){
       // Get Mouse Coordinates
        int x = e.getX();
        int y = e.getY();
        // Get Graph Coordinates
        int leftSide = (int)(view.getWidthTest() * 0.1);
        int rightSide = leftSide + (int)(view.getWidthTest() * 0.8);
        int top = (int)(view.getHeightTest()*.36);
        int bottom = top + view.getGraphHeight(); 
        int graphOffset = (int)(view.getWidthTest() * .1 );
         if (x > leftSide && x < rightSide && y < bottom && y > top){
            
            // Show InfoBar
            int point = (int)( ( x - graphOffset) * ( model.getDailyPriceLength() / (double)view.getGraphWidth() ) );
            int line = (int)(  point *( view.getSpaceWidth() + view.getRectWidth()) ) + graphOffset;
            
            
            DailyStockPrice d = dsp.get(point);
            ((JLabel)comp.get(0)).setText( "Date: " + d.getDate() );
            ((JLabel)comp.get(1)).setText( "Close: $" + (new Double(d.getClose() )).toString() );
            ((JLabel)comp.get(2)).setText( "High: $" + (new Double(d.getHigh() )).toString() );
            ((JLabel)comp.get(3)).setText( "Low: $" + (new Double(d.getLow() )).toString() );
            this.last = d.getDate();
            this.view.setStop( 0 );
            
            // Zoom into graph
            this.model.setNewStockPrices(this.first,this.last);
            //this.textView.notify(model);
            this.view.notify(model);
			
        }
        else {
            this.view.setLine(graphOffset);   
        }
        this.view.setStart(0);
        this.view.setStop(0);
        this.view.repaint();
    }
    
 public void mouseDragged(MouseEvent e) {
       // Get Mouse Coordinates
        int x = e.getX();
        int y = e.getY();
        
        // Get Graph Coordinates
        int leftSide = (int)(view.getWidthTest() * 0.1);
        int rightSide = leftSide + (int)(view.getWidthTest() * 0.8);
        int top = (int)(view.getHeightTest()*.36);
        int bottom = top + view.getGraphHeight();   
        int graphOffset = (int)(view.getWidthTest() * .1 );
        if (x > leftSide && x < rightSide && y < bottom && y > top){
            // Show InfoBar
            
            int point = (int)( ( x - graphOffset) * ( model.getDailyPriceLength() / (double)view.getGraphWidth() ) );
            int line = (int)(  point *( view.getSpaceWidth() + view.getRectWidth()) ) + graphOffset;
            this.view.setLine((int)( line + view.getRectWidth() / 2 ) );
            
            DailyStockPrice d = dsp.get(point);
            ((JLabel)comp.get(0)).setText( "From " + this.first + " to " + this.last );
            ((JLabel)comp.get(1)).setText( "Close: $" + (new Double(d.getClose() )).toString() );
            ((JLabel)comp.get(2)).setText( "High: $" + (new Double(d.getHigh() )).toString() );
            ((JLabel)comp.get(3)).setText( "Low: $" + (new Double(d.getLow() )).toString() );
            this.last= d.getDate();
            this.view.setStop(line);
            }
        else {
            this.view.setStart(graphOffset);
            this.view.setStop(graphOffset);
        }
        this.view.repaint();
    }
    
    
    public void mouseMoved(MouseEvent e) {       
        // Get Mouse Coordinates
        int x = e.getX();
        int y = e.getY();
        // Get Graph Coordinates
        int leftSide = (int)(view.getWidthTest() * 0.1);
        int rightSide = leftSide + (int)(view.getWidthTest() * 0.8);
        int top = (int)(view.getHeightTest()*.36);
        int bottom = top + view.getGraphHeight();   
        int graphOffset = (int)(view.getWidthTest() * .1 );
        // Show correct date for mouse rollover
        if (x > leftSide && x < rightSide && y < bottom && y > top){            
            
            
            
            int point = (int)( ( x - graphOffset) * ( model.getDailyPriceLength() / (double)view.getGraphWidth() ) );
            int line = (int)(  point *( view.getSpaceWidth() + view.getRectWidth()) ) + graphOffset;
            this.view.setLine((int)( line + view.getRectWidth() / 2 ) );
            
            DailyStockPrice d = dsp.get(point);
            ((JLabel)comp.get(0)).setText( "Date: " + d.getDate() );
            ((JLabel)comp.get(1)).setText( "Close: $" + (new Double(d.getClose() )).toString() );
            ((JLabel)comp.get(2)).setText( "High: $" + (new Double(d.getHigh() )).toString() );
            ((JLabel)comp.get(3)).setText( "Low: $" + (new Double(d.getLow() )).toString() );
                }
        else {
            this.view.setLine(graphOffset);
        }        
    this.view.repaint();
}
}

