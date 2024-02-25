package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.BaseBallDao;
import dto.Human;

public class Delete extends JFrame implements ActionListener{

	JLabel label;
	JPanel panel, panel2;
	JTextField textfield;
	JButton menu, dele;
	Container content;
	
	public Delete() {
		setLayout(null);
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(200, 100, 200, 100);
		
		label = new JLabel("삭제할 선수 이름 입력");
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label);
		
		textfield = new JTextField();
		textfield.setBounds(200, 200, 200, 30);
		
		
		
		menu = new JButton("Menu");
		menu.setBounds(400, 170, 80, 30);
		menu.addActionListener(this);
		
		dele = new JButton("삭제");
		dele.setBounds(400, 200, 80, 30);
		dele.addActionListener(this);
		
		
		content = getContentPane();
		content.add(panel,BorderLayout.CENTER);
		content.add(textfield,BorderLayout.CENTER);
		content.add(menu);
		content.add(dele);
		
		setBounds(0, 0, 640, 500);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		
		if(obj == menu) {
			dispose();
			new Menu();
		}
		else if(obj == dele) {
			BaseBallDao bd = BaseBallDao.getInstance();
			
			String str = textfield.getText();
			
			boolean b = bd.delete(str);
				if(b) {
					JOptionPane.showMessageDialog(null, "삭제 성공 하였습니다.");
				}
				else {
					JOptionPane.showMessageDialog(null, "없는 선수 입니다.");
				}
		}
	}
}
