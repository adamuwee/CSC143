import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.comm.*;

public class Main {

	// Label Font
	private static Font lblFont = new Font("Verdana",Font.BOLD,20);
	private static Color lblColor = new Color(120,120,0);
	private static Color frameColor = new Color(200,200,200);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Set JFrame
		JFrame frame = new JFrame("Pulser Control - Java Version 1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Container c = frame.getContentPane();

		// Add Components
		
		// Pulse Length
		JPanel jpLength = new JPanel(new GridLayout(2,1));
		// Text Field
		final JTextField txtLength = new JTextField("100",5);
		JPanel jpLentxt = new JPanel();
		jpLentxt.add(txtLength);
		txtLength.setFont(lblFont);
		// Pulse Length Label
		JLabel lblLength = new JLabel("Pulse Length (ms)");
		JPanel jpLenlbl = new JPanel();
		jpLenlbl.add(lblLength);
		lblLength.setFont(lblFont);
		lblLength.setForeground(lblColor);
		// Add to Panel
		jpLength.add(jpLenlbl,BorderLayout.NORTH);
		jpLength.add(jpLentxt,BorderLayout.SOUTH);

		
		// Pulse Pause
		JPanel jpPause = new JPanel(new GridLayout(2,1));
		// Text Field
		final JTextField txtPause = new JTextField("100",5);
		JPanel jpPautxt = new JPanel();
		jpPautxt.add(txtPause);
		txtPause.setFont(lblFont);
		// Pulse Length Label
		JLabel lblPause = new JLabel("Pulse Pause (ms)");
		JPanel jpPaulbl = new JPanel();
		jpPaulbl.add(lblPause);
		lblPause.setFont(lblFont);
		lblPause.setForeground(lblColor);
		// Add to Panel
		jpPause.add(jpPaulbl,BorderLayout.NORTH);
		jpPause.add(jpPautxt,BorderLayout.SOUTH);
		
		
		// Pulse Cycle
		JPanel jpCycle = new JPanel(new GridLayout(2,1));
		// Text Field
		final JTextField txtCycle = new JTextField("10",5);
		JPanel jpCyctxt = new JPanel();
		jpCyctxt.add(txtCycle);
		txtCycle.setFont(lblFont);
		// Pulse Length Label
		JLabel lblCycle = new JLabel("Pulse Cycle");
		JPanel jpCyclbl = new JPanel();
		jpCyclbl.add(lblCycle);
		lblCycle.setFont(lblFont);
		lblCycle.setForeground(lblColor);
		// Add to Panel
		jpCycle.add(jpCyclbl,BorderLayout.NORTH);
		jpCycle.add(jpCyctxt,BorderLayout.SOUTH);
		
		// Send Button
		JPanel jpSend = new JPanel();
		JButton btnSend = new JButton("   Send   ");
		btnSend.setFont( new Font("Verdana",Font.BOLD,36));
		jpSend.add(btnSend);
		
		// Reset Button
		JPanel jpReset = new JPanel();
		final JButton btnReset = new JButton("Reset");
		jpReset.add(btnReset);
		
		// Main Panel
		JPanel main = new JPanel( new GridLayout(6,1));
		main.add(new JLabel("     "),BorderLayout.CENTER);
		main.add(jpLength,BorderLayout.CENTER);
		main.add(jpPause,BorderLayout.CENTER);
		main.add(jpCycle,BorderLayout.CENTER);
		main.add(jpSend,BorderLayout.CENTER);
		main.add(jpReset,BorderLayout.NORTH);
		
		// Add main Panel to JFrame
		c.add(main);
		
		// Set Size and Color
		frame.setSize(300,500);
		frame.setBackground(frameColor);
		
		// Show Frame
		frame.setVisible(true);
		
		// Controller
		// Send Button
		btnSend.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Send Values to Serial Port
				
				// Check each value to make sure it is an integer
				// if value is a number round to an integer value
				int length = 100;
				int pause = 100;
				int cycle = 10;
				
				// Pulse Length
				try {
					Double d = new Double(txtLength.getText()); 
					// Cast to Integer
					length = (int)d.doubleValue(); 
					if (length > 3000 || length < 0){
						throw new Exception();
					}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,"Pulse Length Error" + "\n" + "Please Enter an Integer between 1 and 3000",
							"Invalid Input - Pulse Length",
							JOptionPane.ERROR_MESSAGE);
					length = -1;
					
				}
				
				// Pulse Pause
				try {
					Double d = new Double(txtPause.getText()); 
					// Cast to Integer
					pause = (int)d.doubleValue(); 
					if (pause > 3000 || pause < 0){
						throw new Exception();
					}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,"Pulse Pause Error" + "\n" + "Please Enter an Integer between 1 and 3000",
							"Invalid Input - Pulse Pause",
							JOptionPane.ERROR_MESSAGE);
					pause = -1;
				}		
			
				// Pulse Cycle
				try {
					Double d = new Double(txtCycle.getText()); 
					// Cast to Integer
					cycle = (int)d.doubleValue(); 
					if (cycle > 30 || cycle < 0){
						throw new Exception();
					}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,"Pulse Cycle Error" + "\n" + "Please Enter an Integer between 1 and 30",
							"Invalid Input - Pulse Cycle",
							JOptionPane.ERROR_MESSAGE);
					cycle = -1;
					
				}
				
				// Check if values are valid after checking
				if ( length > 0 && pause > 0 && cycle > 0 ) {
					sendPulse(length,pause,cycle);
				}
				else{
					btnReset.doClick();
				}
				
			}
		});
		// Reset Button
		btnReset.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Set text to default values
				txtLength.setText("100");
				txtPause.setText("100");
				txtCycle.setText("10");			
			}
		});
	}
	/**
	 * Sends the values to the Serial Port
	 * @param length
	 * @param pause
	 * @param cycle
	 */
	private static void sendPulse(int length, int pause, int cycle){
	
	}
	
}
