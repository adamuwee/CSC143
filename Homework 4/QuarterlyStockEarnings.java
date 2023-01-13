
/**
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */
public class QuarterlyStockEarnings
{
    String date;
    double earnings;
    String trend;
    double maxEarnings;

    /**
     * Constructor for objects of class QuarterlyStockEarnings
     */
    public QuarterlyStockEarnings(double earnings, String date, String trend)
    {
        this.earnings = earnings;
        this.date = date;
        this.trend = trend;
        

    }

    public String toString() {
     return "Earnings: " + this.earnings + " Date: " + this.date + " Trend: " + this.trend;   
    }
    
    public boolean equals(Object o) {
     if ( o instanceof QuarterlyStockEarnings) {
         QuarterlyStockEarnings qSE = (QuarterlyStockEarnings)o;
         return ( qSE.date.equals(this.date) && qSE.earnings == this.earnings && qSE.trend.equals(this.trend));
        }
        else {
            return false;
        }
    }
    
    public QuarterlyStockEarnings clone() {
     return new QuarterlyStockEarnings(this.earnings, this.date, this.trend);   
    }
    public double getEarnings() {
        return this.earnings;
    }
    public String getDate() {
        return this.date;
    }
    public String getTrend() {
        return this.trend;
    }
}
