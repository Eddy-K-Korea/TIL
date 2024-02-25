package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Panel.BatterPanel;
import Panel.PitcherPanel;

public class Insert extends JFrame implements ActionListener{

	Container contentPane;
	JLabel label;
	JButton button1,button2;
	ButtonGroup buttonG;
	JPanel panel;
	
	public Insert() {
		
		label = new JLabel("INSERT");
		label.setBackground(Color.yellow);
		label.setForeground(Color.red);
		add(label);
		
		button1 = new JButton("투수");
		button1.addActionListener(this);
		
		button2 = new JButton("타자");
		button2.addActionListener(this);
		
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(170, 5, 300, 30);
		panel.add(label);
		panel.add(button1);
		panel.add(button2);
		
		PitcherPanel pp = new PitcherPanel(this);
		contentPane = getContentPane();
		contentPane.add(panel,BorderLayout.NORTH);
		contentPane.add(pp,BorderLayout.CENTER);
		
		setBounds(0, 0, 640, 500);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		contentPane = getContentPane();
		contentPane.remove(1);
		if(obj == button1) {
			contentPane.add(new PitcherPanel(this),BorderLayout.CENTER);
		}
		else if(obj == button2){
			contentPane.add(new BatterPanel(this),BorderLayout.CENTER);
		}
		revalidate();
	}
}
