import java.util.ArrayList;
import java.io.File;
import javax.swing.*;
/**
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */
public class StockChart
{
  private final String folderPath = "C:\\StockData\\";
  private String fileName = "SUNW.txt";
  private StringBuffer stockSymbol;
  private boolean firstRun = true;
                           
  
    ArrayList<DailyStockPrice> dailyPrices = new ArrayList();
    ArrayList<QuarterlyStockEarnings> quarterlyEarnings = new ArrayList();

    /**
     * Constructor for objects of class StockChart
     */
    public StockChart()
    {   
        getStockData();
    }
    
    /**
     * Gets the data from the file
     * @param r the TextFileReader
     */
    private void getStockData() {
        TextFileReader r = new TextFileReader(this.folderPath + this.fileName);
        
        // Get line from text file and split up into two arrays
        //one of size 3 and another one of size 4
        do {
            
            String line = r.readLine();
            if (line == null) {
                break;
            }
            String[] data = line.split("\t");
            // Check where the reader is at in the textFile
            if ( data.length <= 1 && firstRun) {
                
                String[] temp = data[0].split("//");
                if (temp[1] != null) {
                this.stockSymbol = new StringBuffer(temp[1]);
                firstRun = false;
            }
            }
            if (data.length == 4) {
            String high = data[0]; 
            String low = data[1]; 
            String close = data[2]; 
            String date = data[3];
            Double iHigh = new Double(high);
            Double iLow = new Double(low);
            Double iClose = new Double(close);
            DailyStockPrice dp = new DailyStockPrice(iHigh.doubleValue(),iLow.doubleValue(),iClose.doubleValue(),date);
            dailyPrices.add(dp);
            }
            else if (data.length == 3) {
                String date = data[1];
                String trend = data[2];  
                Double dEarnings = new Double(data[0]);
                QuarterlyStockEarnings qE = new QuarterlyStockEarnings(dEarnings.doubleValue(),date,trend);
                quarterlyEarnings.add(qE);
            }
        }while(true);
        
        r.close();
    }
    
    /**
     * Loads the Stock file
     * @param s the file path of the Stock File
     */
    public void loadStockFile(String s) {
        this.fileName = s;
    }
    
    /** 
     * @return Returns an arraylist of all the daily stock prices
     */
    public ArrayList getAllDailyStockPrices() {
        ArrayList<DailyStockPrice> copyOfDailyStockPrice = new ArrayList();
        for(DailyStockPrice d : dailyPrices) {
            copyOfDailyStockPrice.add(new DailyStockPrice(d.getHigh(),d.getLow(),d.getClose(),d.getDate()));
        }
        return copyOfDailyStockPrice;
    }
    /** 
     * @return Returns an arraylist of all the Quarterly Stock Earnings
     */
    public ArrayList getAllQuarterlyStockEarnings() {
        ArrayList<QuarterlyStockEarnings> copyOfQuarterlyStockEarnings = new ArrayList();
        for(QuarterlyStockEarnings q : quarterlyEarnings) {
                copyOfQuarterlyStockEarnings.add(new QuarterlyStockEarnings(q.getEarnings(), q.getDate(), q.getTrend()));
        }
        return copyOfQuarterlyStockEarnings;
    }
    /** 
     * @return Returns the file path of the stock file as a string
     */  
    public String getFilePath() {
        return (this.folderPath + this.fileName);
    }
    /** 
    * @return Returns the Daily Stock Price by index value
    */
    public DailyStockPrice getDailyStockPrice(int index) {
        return dailyPrices.get(index);
    }
    /** 
    * @return Returns the Quarterly Stock Earnings by index value
    */
    public QuarterlyStockEarnings getQuarterlyStockEarnings(int index) {
        return quarterlyEarnings.get(index);
    }
    
    /** 
    * @return Returns the object with the highest Daily Stock Price high 
    */
    public DailyStockPrice getDailyStockPriceHigh() {
        double high = 0;
        DailyStockPrice dHigh = null;
        for(DailyStockPrice d : dailyPrices) {
            if (d.getHigh() > high) {
                high = d.getHigh();
                dHigh = d;
            }
        }
        return dHigh;
    }
    /** 
    * @return Returns the object with the lowest Daily Stock Price low 
    */
    public DailyStockPrice getDailyStockPriceLow() {
        double high = ((DailyStockPrice)this.getDailyStockPriceHigh()).getHigh();
        DailyStockPrice dLow = null;
        for(DailyStockPrice d : dailyPrices) {
            if (d.getLow() < high) {
                high = d.getLow();
                dLow = d;
            }
        }
        return dLow;
    }
    
    /** 
    * @return Returns the value of the lowest Daily Stock Price low 
    */
    public double getLowestLow() {
        return ((DailyStockPrice)this.getDailyStockPriceLow()).getLow();
    }
    
    /** 
    * @return Returns the value of the highest Daily Stock Price high 
    */ 
    public double getHighestHigh() {
        return ((DailyStockPrice)this.getDailyStockPriceHigh()).getHigh();
    }
  
    /** 
    * @return Returns the Daily Stock Price by date 
    */
    public DailyStockPrice getDailyStockPriceDate(String date) {
        for(DailyStockPrice d : dailyPrices) {
            if (d.getDate().equals(date) ) {
                return d;
            }
        }
        return null;
    }
    
    /** 
    * @return Returns the percentage change between two Daily Stock Prices given two dates 
    */
    public double percentChange(String date1, String date2) {
        double close1 = ( getDailyStockPriceDate(date1) ).getClose();
        double close2 = ( getDailyStockPriceDate(date2) ).getClose();
        if( close1 == close2) {
            return 0;
        }
        else  {
            return -(1 - (close2 / close1));
        }
        
    }
    
    /** 
    * Returns the length of the DailyStockPrice arraylist.
    */
    public int getDailyPriceLength() {
        return dailyPrices.size();
    }
    
    /** 
    * Returns the length of the QuarterlyStockEarnings arraylist.
    */  
    public int getQuarterlyStockEarningsLength() {
        return quarterlyEarnings.size();
    }
    
    /** 
    * Returns the Daily Stock Prices of the arraylist.
    */  
    public ArrayList getDailyStockPrices() {
        
        return this.dailyPrices;
    }
    
    /** 
    * Returns the Quarterly Stock Prices of the arraylist.
    */  
    public ArrayList getQuarterlyStockPrices(){
        return this.quarterlyEarnings;
    }
    
    /** 
    * Returns the Quarterly Stock Prices of the arraylist.
    */ 
    public StringBuffer getStockSymbol() {
        return this.stockSymbol;
    }
    
    /** 
    * Returns the Daily Stock Price Index by date of the arraylist.
    */    
    public int getDailyStockPriceIndex(String date) {
        int i = 0;
        for(DailyStockPrice d : dailyPrices) {
            if (d.getDate().equals(date) ) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public Object clone() {
        StockChart temp = new StockChart();
        temp.dailyPrices = new ArrayList();
        for( DailyStockPrice d : dailyPrices) {
         DailyStockPrice h = d;
         temp.dailyPrices.add(d);
        }
        temp.quarterlyEarnings = new ArrayList();
        for( QuarterlyStockEarnings q : quarterlyEarnings) {
           temp.quarterlyEarnings.add(q);
        }

        
        temp.fileName = this.fileName;
        temp.stockSymbol = this.stockSymbol;
        temp.firstRun = this.firstRun;
        return temp;
    }
    
    public void setStockFile(String s) throws Exception  {
        
        this.fileName = (s + ".txt");
        try {
        getStockData();
    }
    catch(Exception e) {
        throw new Exception();
    }
    this.stockSymbol = new StringBuffer(s);
    dailyPrices.clear();
    quarterlyEarnings.clear();
    getStockData();
    }
    
    public void setNewStockPrices(String date1, String date2) {
        ArrayList dsp = new ArrayList();

        // Make sure the dates are in the correct order
        if ( !datesInOrder(date1,date2) ) {
            String temp = date1;
            date1 = date2;
            date2 = temp;
        }
        
        int firstIndex = getDailyStockPriceIndex(date1);
        int lastIndex = getDailyStockPriceIndex(date2);
        
        
        // Set New Range for StockPrice
        for( int i = firstIndex; i < lastIndex; i++) {
            dsp.add( getDailyStockPrice( i ) );
        }
        dailyPrices.clear();
        for(Object d : dsp) {
            dailyPrices.add((DailyStockPrice)d);
        }
        
        // Set New range for Quarterly Earnings
        ArrayList<QuarterlyStockEarnings> qse = (ArrayList)quarterlyEarnings.clone();
        
        
        // Check Dates to Include
        int n = 0;
        // Check each qse
        for (QuarterlyStockEarnings q : quarterlyEarnings) {
            
          if ( !(datesInOrder( date1, q.getDate() ) )) {
                qse.remove(q);
        }
        n++;
    }
        n = 0;
        for (QuarterlyStockEarnings q : quarterlyEarnings) {
            
        if ( ( datesInOrder(date2,q.getDate()) ) ) {
            qse.remove(q);
        }
        n++;
    }
    quarterlyEarnings.clear();
    for(Object q: quarterlyEarnings) {
    quarterlyEarnings.add((QuarterlyStockEarnings)q);
}
}
    /**
     * Checks whether the dates given are in chronological order
     */
    private boolean datesInOrder(String d1, String d2) {
        String[] date1 = d1.split("/");
        String[] date2 = d2.split("/");
        
        int d1Month = new Integer(date1[0]); int d1Day =  new Integer(date1[1]); int d1Year =  new Integer(date1[2]);
        int d2Month =  new Integer(date2[0]); int d2Day =  new Integer(date2[1]); int d2Year =  new Integer(date2[2]);
        
        if ( d1Year < d2Year) {
            return true;
        }
        else if ( d1Month < d2Month && d1Year <= d2Year) {
            return true;
        }
        else if ( d1Day < d2Day && d1Month <= d2Month && d1Year <= d2Year) {
        return true;
    }
    else{
        return false;
    }
    }
}
