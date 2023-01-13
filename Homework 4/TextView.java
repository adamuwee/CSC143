import java.util.ArrayList;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;
/**
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */
public class TextView extends JScrollPane implements StockView 
{
    // JFrame height and Width
    private int width, height;
    StockChart model;
    /**
     * Constructor for objects of class TextView
     */
    public TextView(StockChart model) {
        super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.model = model;
        draw();

    }

    public void draw() {
         JTextArea tx = new JTextArea(getString());
         tx.setFont(new Font("Comic", Font.BOLD, 12));
         setViewportView(tx);
         setPreferredSize(new Dimension(600, 100));
         
         setSize(600,350);
    }
    /**
     * Prints the data on the terminal window
     */    
    private String getString() {
        ArrayList<QuarterlyStockEarnings> qrtEarning = this.model.getQuarterlyStockPrices();
        StringBuffer sb = new StringBuffer();
        DecimalFormat df = new DecimalFormat("0.00");

        String stockSymbol =  model.getStockSymbol().toString();
        sb = sb.append("Stock Symbol: " + stockSymbol + "\n") ;
        sb = sb.append("==================" + "\n");
        
        // Display Earnings
        int lastIdx = 0;
        
        for(QuarterlyStockEarnings q : qrtEarning) {
            
            DailyStockPrice firstDay = model.getDailyStockPrice(lastIdx);
            lastIdx = this.model.getDailyStockPriceIndex(q.getDate());
            DailyStockPrice currentDay = model.getDailyStockPriceDate( q.getDate() );
            
            
            sb = sb.append(q.getDate() + " Earnings: " + q.getEarnings() + "\n");               
            sb = sb.append("Quarterly Change From " + firstDay.getDate() + " to " +  currentDay.getDate() + " : " +
                            df.format( model.percentChange(firstDay.getDate(),currentDay.getDate())*100 ) + "%" + "\n");
                                
            // Get Maximum and Minimum values
            // Create an ArrayList up to the current date
            ArrayList d = new ArrayList();
            for (int i = 0; i <= model.getDailyStockPriceIndex(q.getDate()); i++) {
                d.add(model.getDailyStockPrice(i));
            }
            DailyStockPrice high = getMax(d);
            DailyStockPrice low = getMin(d);
            
            sb = sb.append("Maxium Value: " + high.getHigh() + " - " + high.getDate() + "\n");
            sb = sb.append("Minimum Value: " + low.getLow() + " - " + low.getDate() + "\n");
        } 
    

        
        // Display Percent Change over one Year
        DailyStockPrice firstDay = model.getDailyStockPrice(0);
        DailyStockPrice lastDay = model.getDailyStockPrice( model.getDailyPriceLength()  - 1);
        sb = sb.append("Percentage Change Over One Year: " + 
                    df.format( model.percentChange(firstDay.getDate(),lastDay.getDate()) * 100) + "%" + "\n");
        
        return sb.toString();
}   
        private DailyStockPrice getMax(ArrayList d) {
            ArrayList<DailyStockPrice> tempArray = d;
            double high = 0;
            DailyStockPrice tempDSP = null;
            
            for(DailyStockPrice temp: tempArray) {
             if (temp.getHigh() > high) {
                 high = temp.getHigh();
                 tempDSP = temp;
                }
               
            }
            
            return tempDSP;

            
            
        }
        private DailyStockPrice getMin(ArrayList d) {
            
            double low = getMax(d).getHigh();
            ArrayList<DailyStockPrice> tempArray = d;
            DailyStockPrice tempDSP = null;
            for(DailyStockPrice temp: tempArray) {
             if (temp.getLow() < low) {
                 low = temp.getLow();
                  tempDSP = temp;
                }
            }
            return tempDSP;

            
            
        }        
        
    /**
     * Notifies this StockChart(the model) so it can update its display of the StockView.
     * @param m StockChart notifying this StockView(the view)
     */
     public void notify(StockChart m){
        this.model = m;
        this.draw();     
       }


}
