/**
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */
public class DailyStockPrice
{
    private String date;
    private double high;
    private double low;
    private double close;

    /**
     * Constructor for objects of class DailyStockPrice
     */
    public DailyStockPrice(double high, double low, double close, String date)
    {
        this.high = high; this.low = low; this.close = close; this.date = date;
    }
    
    /**
     * Returns the high value of the DailyStockPrice
     */ 
    public double getHigh() {
        return this.high;
    }
    /**
     * Returns the low value of the DailyStockPrice
     */
    public double getLow() {
        return this.low;
    }
    /**
     * Returns the close value of the DailyStockPrice
     */
    public double getClose() {
        return this.close;
    }
    /**
     * Returns date of the DailyStockPrice
     */
    public String getDate() {
        return this.date;
    }
    
    /**
     * Returns the componets of the Daily Stock Price in a 
     * String format
     */
    public String toString() {
        return "High: " + high + " Low: " + low + " Close: " + close + " Date: " + date;
    }   
    
    /**
     * Compares two objects of type DailyStockPrice
     */
    public boolean equals(Object o) {
        if (o instanceof DailyStockPrice) {
        DailyStockPrice dSC = (DailyStockPrice)o;
            return ( (dSC.date).equals(this.date) 
            && dSC.high == this.high && dSC.low == this.low && dSC.close == this.close);
    }
        else {
            return false;
        }
    }
    
    // Returns a Deep Copy
    public DailyStockPrice clone() {
        return new DailyStockPrice(this.high, this.low, this.close, this.date);
    }
}
