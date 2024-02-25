package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CoffeePanel extends JPanel{
	
	JLabel[] cofName;
	JLabel[] price1;
	JLabel[] price2;
	JLabel[] price3;
	JPanel jp1, jp2,jp3,jp4;
	
	public CoffeePanel() {
		jp1 = new JPanel();
		jp1.setLayout(new GridLayout(11,1));
		jp1.setBackground(Color.white);
		cofName = new JLabel[11];
		String name[] = {"Espresso Beverages","헤이즐넛 카리멜 모카","카라멜 마끼아또","화이트 초콜릿 모카","카라멜 모카","카페 모카","카라멜 라떼","카페 라떼","카푸치노","아메리카노","오늘의 커피"};
		for (int i = 0; i < cofName.length; i++) {
			cofName[i] = new JLabel(name[i]);
			jp1.add(cofName[i]);
		}
		
		jp2 = new JPanel();
		jp2.setLayout(new GridLayout(11,1));
		jp2.setBackground(Color.white);
		price1 = new JLabel[11];
		String price[] = {"Short","4800","4300","4300","4300","3800","3800","3300","3300","2800","2500"};
		for (int i = 0; i < price1.length; i++) {
			price1[i] = new JLabel(price[i]);
			price1[i].setHorizontalAlignment(JLabel.CENTER);
			jp2.add(price1[i]);
		}
		
		jp3 = new JPanel();
		jp3.setLayout(new GridLayout(11,1));
		jp3.setBackground(Color.white);
		price2 = new JLabel[11];
		String pc1[] = {"Tall","5300","4800","4800","4800","4300","4300","3800","3800","3300","3000"};
		for (int i = 0; i < price2.length; i++) {
			price2[i] = new JLabel(pc1[i]);
			price2[i].setHorizontalAlignment(JLabel.CENTER);
			jp3.add(price2[i]);
		}
		
		jp4 = new JPanel();
		jp4.setLayout(new GridLayout(11,1));
		jp4.setBackground(Color.white);
		price3 = new JLabel[11];
		String pc2[] = {"Grande","5800","5300","5300","5300","4800","4800","4300","4300","3800","3500"};
		for (int i = 0; i < price3.length; i++) {
			price3[i] = new JLabel(pc2[i]);
			price3[i].setHorizontalAlignment(JLabel.CENTER);
			jp4.add(price3[i]);
		}
		
		add(jp1);
		add(jp2);
		add(jp3);
		add(jp4);
		
		setBackground(Color.white);
	}

	
		
}
