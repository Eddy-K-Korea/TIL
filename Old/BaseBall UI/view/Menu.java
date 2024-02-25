package view;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{
	JPanel panel;
	JLabel label;
	JButton button[];
	
	public Menu() {
		
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(280, 30, 100, 30);
		panel.setBackground(Color.DARK_GRAY);
		
		label = new JLabel("BaseBall");
		label.setForeground(Color.white);
		panel.add(label);
		
		add(panel);
		
		button = new JButton[6];
		String str[] = {"추가","삭제","검색","수정","출력","저장"};
		
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton(str[i]);
			button[i].setBounds(230, 80+60*i, 200, 50);
			button[i].addActionListener(this);
			add(button[i]);
		}
		
		
		setBounds(0, 0, 640, 500);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton)e.getSource();
		String btnTitle = btn.getLabel();
		
		if(btnTitle.equals("추가")) {
			dispose();
			new Insert();
		}
		else if(btnTitle.equals("삭제")) {
			dispose();
			new Delete();
		}
		else if(btnTitle.equals("검색")) {
			dispose();
			new Select();
		}
		else if(btnTitle.equals("수정")) {
			dispose();
			new Updata();
		}
		else if(btnTitle.equals("출력")) {
			dispose();
			new AllPrint();
		}
		else if(btnTitle.equals("저장")) {
			bd.dataSave();
			JOptionPane.showMessageDialog(null, "저장 되었습니다.");
		}
	}

}
