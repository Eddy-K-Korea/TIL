package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuView extends JFrame implements ActionListener {

	JPanel jp;
	JLabel lb1;
	JButton addbtn, datebtn,searchbtn;
	
	public MenuView() {

		super("메뉴");
		
		jp = new JPanel();
		jp.setBounds(0, 0, 300, 280);
		jp.setBackground(Color.white);
		jp.setLayout(null);
		
		lb1 = new JLabel("메뉴");
		lb1.setBounds(130, 20, 50, 30);
		jp.add(lb1);
		
		addbtn = new JButton("추가");
		addbtn.setBounds(80, 80, 130, 40);
		addbtn.addActionListener(this);
		datebtn = new JButton("기간별 내역");
		datebtn.setBounds(80, 135, 130, 40);
		datebtn.addActionListener(this);
		searchbtn = new JButton("항목별 검색");
		searchbtn.setBounds(80, 190, 130, 40);
		searchbtn.addActionListener(this);
		
		jp.add(addbtn);
		jp.add(datebtn);
		jp.add(searchbtn);
		
		add(jp);
	
		setBounds(700, 300, 300, 300);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		if(obj == addbtn) {
			dispose();
			new AddView();
		}
		else if(obj == datebtn) {
			dispose();
			new DateContent();
		}
		else if(obj == searchbtn) {
			dispose();
			new KindView();
		}
	}

}
