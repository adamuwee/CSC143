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
    public static void main (String[] arg)
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
        
        //The two parts of the north panel        
        JPanel north = new JPanel(new GridLayout(3,1) );
        JPanel northMenu = new JPanel(new GridLayout(1,4));
        Color panelColor = new Color (197, 208, 160);
        frame.setBackground(panelColor);
        
        //The label that says StockChart 
        JLabel stockSymbol = new JLabel("      StockChart      ");
        //JLabel stockSymbolToString = new JLabel(model.getStockSymbol().toString());
        stockSymbol.setFont(new Font("Verdana", Font.BOLD, 18));
        
                          
        // Add Label to TextField
        JLabel label = new JLabel("Enter Stock Symbol");
        label.setFont(new Font("Courrier", Font.ITALIC,14));
        northMenu.add(label);//,BorderLayout.CENTER);
        
        // Add the TextField
        JTextField txtStock = new JTextField(10);
        txtStock.setActionCommand("NewSymbol");
        txtStock.addActionListener(controller);
        
        //The inner panel for the label and the stock symbol txt
        JPanel innerPanel = new JPanel(new GridLayout(1,3));
        innerPanel.add(label);
        innerPanel.add(txtStock);
        innerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        
        //JPanel resetButtonPanel = new JPanel();
        JPanel resetButton = new JPanel(new GridLayout(1,3));
        JLabel empty1 = new JLabel("    ");  
        JLabel empty2 = new JLabel("    "); 
        JButton reset =  new JButton ("Reset");
        reset.setActionCommand("Reset");
        //Add an action listener for the button
        reset.addActionListener(controller);
        reset.setForeground(Color.white);
        reset.setBackground(Color.black);
        resetButton.add(empty1);
        resetButton.add(reset);
        resetButton.add(empty2);
        //resetButton.setBorder(BorderFactory.createRaisedBevelBorder());
        
        //The top of the Panel
        JPanel topPanel = new JPanel();
        //topPanel.setBackground(panelColor);
   
        //Add componets to the northMenu        
        northMenu.add(stockSymbol);
        northMenu.add(innerPanel);
        northMenu.add(resetButton);
 
        // Create InfoBar
        JPanel infoBar = new JPanel(new GridLayout(1,4));
        //infoBar.setBackground(Color.yellow);
        // Labels
        JLabel date = new JLabel("Date:");
        JLabel low = new JLabel("Low:");
        JLabel high = new JLabel("High:");
        JLabel close = new JLabel("Close:");
        // Add elements to infobar panel
        infoBarComp.add(date);infoBarComp.add(low);infoBarComp.add(high);infoBarComp.add(close);
        
        // Add to ArrayList so that the Controller can update
        infoBar.add(date); infoBar.add(low); infoBar.add(high); infoBar.add(close);
        //Formats the infoBar with a RaisedBevelBorder
        infoBar.setBorder(BorderFactory.createRaisedBevelBorder());
        infoBar.setBackground(panelColor);
       
        //Add componets to norh panel. The panel in locatin north in the frame
        north.add(topPanel);
        north.add(northMenu);
        north.add(infoBar);
 
 
        //The south panel that contains the text area and Footer
        JPanel south = new JPanel();
        JPanel innerSouthPanel = new JPanel();
        
        JLabel footer = new JLabel(" Created by Francisco Gomez and Adam Przybilla");
        footer.setFont(new Font("Verdana", Font.ITALIC, 10));
        //Adds the view to the south pabel
        south.add(textView);
        
        // Send array to mouseHandler
        MouseHandler mouseHandler = new MouseHandler(view,infoBarComp,textView);
        frame.addMouseListener(mouseHandler);
        frame.addMouseMotionListener(mouseHandler);
       
        
        c.add(north,BorderLayout.NORTH);
        c.add(view,BorderLayout.CENTER);
        c.add(south,BorderLayout.SOUTH);
        
        //c.add(footer,BorderLayout.PAGE_END);

        //Makes the frame visible
        frame.setSize(width, height);
        frame.setVisible(true);
        


    }
    
    

   
}
