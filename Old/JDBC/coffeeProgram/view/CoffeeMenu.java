package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CoffeeMenu extends JFrame {

	JLabel title;
	JLabel[] cofName;
	JLabel[] price1;
	JLabel[] price2;
	JLabel[] price3;
	JPanel pan;
	
	public CoffeeMenu() {
		super("메뉴판");
		setLayout(null);
		
		title = new JLabel("가격표");
		title.setBounds(150, 30, 80, 30);
		add(title);
		
		pan = new JPanel();
		pan.setLayout(null);
		pan.setBounds(0, 0, 350, 400);
		
		
		CoffeePanel cp = new CoffeePanel();
		cp.setBounds(30, 80, 280, 220);
		add(cp);
		
		pan.setBackground(Color.yellow);
		add(pan);
		setBounds(300, 100, 350, 400);
		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
