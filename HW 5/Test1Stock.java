import java.util.ArrayList;
import javax.swing.*;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;

/**
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */

public class Test1Stock extends StockChart
{
    private static final int NUM_OF_DAILY_STOCK = 200;
    private static final int LOW = 20;
    private static final int HIGH = 180;
    
    //Print Test Results
    private static final boolean PRINT_TEST_RESULTS = false;

    /**
     * Constructor for objects of class Test1Stock
     */
    public Test1Stock()
    {
        super.dailyPrices = new ArrayList();
        
        int month = 1;
        int day = 1;
        for( int i = 1; i < NUM_OF_DAILY_STOCK + 1; i++) {
            day++;
            if (day > 31) {
                month++;
                day = 1;
            }
            // Get almost correct date
            StringBuffer date = new StringBuffer(month + "/" + day + "/" + "2005");

            
            //Format prices to two decimal places
            DecimalFormat df = new DecimalFormat("0.00");
            double high = (new Double(df.format( (Math.random()* (HIGH - LOW)) + LOW ))).doubleValue();
            double low = (new Double(df.format( (high - LOW)*Math.random()+LOW ))).doubleValue();
            double close = (new Double(df.format( ( high + low ) / (2) ))).doubleValue();
            
            // Test - Make sure that the high is highest, the low is the lowest and the close is somewhere in between.
            if (high < low){
                JOptionPane.showMessageDialog(null, "high < low" + high + "\n" + low + "\n" + close);
                break;
            }
            else if(close > high) {
                JOptionPane.showMessageDialog(null, "close > high" + high + "\n" + low + "\n" + close);
                break;
            }
            else if(close < low) {
                JOptionPane.showMessageDialog(null, "close < low" + high + "\n" + low + "\n" + close);
                break;
            }
            if (PRINT_TEST_RESULTS) {
            System.out.println("i = " + i + "  high: " + high + "  low: " + low + "  close: " + close);
        }
            
            dailyPrices.add(new DailyStockPrice(high,low,close,date.toString()));
          }
          

          // Print if Successful
          if (PRINT_TEST_RESULTS) {
          System.out.println("Test Successful");
         } 
    }
    
    public ArrayList getDailyStockPrices() {
        return this.dailyPrices;
    }

}
