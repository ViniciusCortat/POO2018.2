package temp;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;

public class MultiplePanels implements ActionListener {
	JFrame window = new JFrame("Multiplos painels");
	JPanel panel_01 = new JPanel();
	JPanel panel_02 = new JPanel();
	JButton click = new JButton();
	JLabel text = new JLabel("Button clciked");
	
	MultiplePanels() {
		panel_01.setBackground(Color.DARK_GRAY);
		panel_02.setBackground(Color.BLACK);
		
		window.add(panel_01,BorderLayout.CENTER);
		window.add(panel_02,BorderLayout.PAGE_END);
		
		window.setSize(400,500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	
}
