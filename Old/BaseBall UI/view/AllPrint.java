package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import Panel.AllPrintPanel;

public class AllPrint extends JFrame {

	Container contentPane;
	
	public AllPrint() {
		
		setLayout(null);
		
		AllPrintPanel ap = new AllPrintPanel(this);
		
		contentPane = getContentPane();
		contentPane.add(ap);
		contentPane.setLocation(100, 100);
		
		setBounds(0, 0, 640, 500);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
}
