

/**
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */
public class UnitTests extends junit.framework.TestCase
{
    /**
     * Default constructor for test class UnitTests
     */
    public UnitTests()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    public void testChartScale()
    {       
        ChartView cv = new ChartView( new StockChart() );
        // Yellow Rectangle should be 10% of main window
        int h = cv.getHeightTest();
        int w = cv.getWidthTest();
        int gH = cv.getGraphHeight();
        int gW = cv.getGraphWidth();
        assertTrue( gH == h*0.1 && gW == w * 0.1); 
    }
    
    

    public void testCheckDeepCopy()
    {
        StockChart subject = new StockChart();
        StockChart copy = (StockChart)subject.clone();
        assertFalse( subject.equals(copy));

    }
}


