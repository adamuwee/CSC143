import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */
public class MainClass
{
    // instance variables - replace the example below with your own
    private int fLength, fWidth;    
    // For Testing Purposes a randomly generated stock chart can be made.
    private static final boolean STOCK_TEST = false;


    /**
     * Constructor for objects of class MainClass
     */
    public MainClass()
    {        
        
    }
    
    /**
     * Main Class to call the construction of the Stock Graph
     * and Text
     */    
    public static void main(String[] arg)
    {
        // Array of the Components for the mouse moved
        ArrayList infoBarComp = new ArrayList();
        
        //The componets of the application
        // Create the Model
        StockChart model;
        if (STOCK_TEST) {      
        model = new Test1Stock(); //Model
        }
        else {
        model = new StockChart(); //Model
        }

        ChartView view= new ChartView(model);  //GraphicsViews
        TextView textView = new TextView(model);
        
        // Add Controller
        Controller controller = new Controller(model,view,textView);
        

    
        //The window as a Frame
        JFrame frame = new JFrame("The One and Only Stock CO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        Container c = frame.getContentPane();
        JPanel mainPanel = new JPanel( new GridLayout(4,1,8,8) );
        
        int height = 600;
        // Width of each Daily Stock Price ( 3 Pixels )
        int DailyStockPriceWidth = 3;
        // Percentage larger that of the stock window ( 120% )
        double PercentageOfStockWindow = 1.20;
        
        int width = (int)( model.getDailyPriceLength()* DailyStockPriceWidth * PercentageOfStockWindow );
        

        
        /* ============================
         * =========== GUI ============
         * ============================
         */
        // North Panel
        JPanel north = new JPanel(new GridLayout(2,1) );
        JPanel northMenu = new JPanel(new GridLayout(1,2));
        JLabel stockSymbol = new JLabel("                   StockChart" );
        stockSymbol.setFont(new Font("Verdana", Font.ITALIC, 20));
        northMenu.add(stockSymbol,BorderLayout.LINE_START);
        
        // Add Label to TextField
        JLabel label = new JLabel("            Enter Stock Symbol");
        label.setFont(new Font("Courrier", Font.ITALIC, 20));
        northMenu.add(label,BorderLayout.CENTER);
        
        // Add the TextField
        JTextField txtStock = new JTextField(12);
        txtStock.setActionCommand("NewSymbol");
        txtStock.addActionListener(controller);
        
        northMenu.add(txtStock, BorderLayout.WEST);
        northMenu.setBackground(Color.blue);
        // Add north panel to NorthContainer
        north.setBackground(Color.blue);
        north.add(northMenu,BorderLayout.NORTH);
 
        // Create InfoBar
        JPanel infoBar = new JPanel(new GridLayout(1,4));
        
        infoBar.setBackground(Color.yellow);
        // Labels
        JLabel date = new JLabel("date:");
        JLabel low = new JLabel("low:");
        JLabel high = new JLabel("high:");
        JLabel close = new JLabel("close:");
        // Add elements to infobar panel
        infoBarComp.add(date);infoBarComp.add(low);infoBarComp.add(high);infoBarComp.add(close);
        // Add to ArrayList so that the Controller can update
        infoBar.add(date,BorderLayout.SOUTH);infoBar.add(low,BorderLayout.SOUTH);
        infoBar.add(high,BorderLayout.SOUTH);infoBar.add(close,BorderLayout.SOUTH);
        
        // Add infoBar to North Panel
        north.add(infoBar, BorderLayout.SOUTH);
 
        //The south panel that contains the text area and Footer
        JPanel south = new JPanel();
        //south.setBackground(Color.gray);

        south.add(textView, BorderLayout.CENTER);
        JLabel footer = new JLabel(" Created by Francisco Gomez and Adam Przybilla");
        footer.setFont(new Font("Verdana", Font.ITALIC, 10));
       //south.add(footer,BorderLayout.PAGE_END);
        //South.add(footer,BorderLayout.PAGE_END);
        //south.add(footer,BorderLayout.PAGE_END);
        JButton reset =  new JButton ("Reset");
        reset.setActionCommand("Reset");
        reset.addActionListener(controller);
        //
        south.add(reset,BorderLayout.SOUTH);
        south.add(footer,BorderLayout.PAGE_END);
        
        // Send array to mouseHandler
        MouseHandler mouseHandler = new MouseHandler(view,infoBarComp,textView);
        frame.addMouseListener(mouseHandler);
        frame.addMouseMotionListener(mouseHandler);
       
        c.add(south,BorderLayout.SOUTH);
        c.add(north,BorderLayout.NORTH);
        c.add(view,BorderLayout.CENTER);
        
        //c.add(footer,BorderLayout.PAGE_END);

        //Makes the frame visible
        frame.setSize(width, height);
        frame.setVisible(true);
        


    }

   
}
