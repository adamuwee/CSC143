import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Controller implements ActionListener
{
    StockChart model;
    ChartView view;
    TextView textView;
    
    public Controller(StockChart model,ChartView view, TextView textView) 
    {
        this.model = model;
        this.view = view;
        this.textView = textView;
    }
    
        public void actionPerformed(ActionEvent e) {
            
        String actionCommand = e.getActionCommand();
        
        if (actionCommand.equals( "NewSymbol" )) {
            // Changes the model
            try {
            this.model.setStockFile( ((JTextField)e.getSource() ).getText() );
            
            
        }
        catch(Exception exp) {
            JOptionPane.showMessageDialog(null,"You cannot type a valid Stock Symbol", "Wrong!", JOptionPane.WARNING_MESSAGE);
        }
       
            this.view.notify(model);
            this.textView.notify(model);
        }
        if (actionCommand.equals("Reset") ){
            String currentSymbol = model.getStockSymbol().toString();
            model = new StockChart();
            System.out.println("sdf");
            try{
            model.setStockFile(currentSymbol);
        }
        catch(Exception exp) {
        }
           
        
        }
         view.notify(model);
            textView.notify(model);
    }

}
