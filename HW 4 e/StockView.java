
/**
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */
public interface StockView
{
    /**
     * Notifies this StockChart(the model) so it can update its display of the StockView.
     * @param m StockChart notifying this StockView(the view)
     */
     public void notify(StockChart m);
}
