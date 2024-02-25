package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.BaseBallDao;
import dto.Batter;
import dto.Human;
import dto.Pitcher;

public class Select extends JFrame implements ActionListener{
	BaseBallDao dao = BaseBallDao.getInstance();
	JLabel label1;
	JLabel label2[] = new JLabel[7];
	JLabel label3[] = new JLabel[7];
	JLabel label4[] = new JLabel[7];
	JTextField tf;
	JButton button,button1, button2;
	Container content;
	
	
	public Select() {
		setLayout(null);
		
		label1 = new JLabel("검색할 선수 이름 입력");
		label1.setBounds(250, 10, 230, 20);
		add(label1);
		
		tf = new JTextField();
		tf.setBounds(260, 30, 100, 20);
		add(tf);
		
		button1 = new JButton("검색");
		button1.setBounds(380, 30, 70, 20);
		button1.addActionListener(this);
		add(button1);
		
		for (int i = 0; i < label2.length; i++) {
			label2[i] = new JLabel();
			label2[i].setBounds(380, 90+30*i, 70, 30);
			add(label2[i]);
		}
		
		for (int i = 0; i < label3.length; i++) {
			String str[] = {"선수번호","이름","나이","키","승리/타석수","패배/안타","방어율/타율"};
			label3[i] = new JLabel(str[i]);
			label3[i].setBounds(210, 90+30*i, 70, 30);
			label3[i].setHorizontalAlignment(JLabel.CENTER);
			add(label3[i]);
		}
		
		
		
		button = new JButton("Menu");
		button.setBounds(450, 350, 80, 30);
		button.addActionListener(this);
		add(button);
		
		setBounds(0, 0, 640, 500);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == button) {
			dispose();
			new Menu();
		}
		if(!tf.getText().equals("")) {
			int index = dao.select(tf.getText());
			Human h = dao.list.get(index);
			content = getContentPane();
			if(obj == button1) {
				
				if(h instanceof Pitcher) {
					String str[] = {h.getNumber()+"",h.getName(),h.getAge()+"",h.getHeight()+"",((Pitcher)h).getWin()+"",
							((Pitcher)h).getLose()+"",((Pitcher)h).getDefence()+""};
					for (int i = 0; i < label2.length; i++) {
						label2[i].setText(str[i]);
					}
				}
				else if(h instanceof Batter) {
					String str[] = {h.getNumber()+"",h.getName(),h.getAge()+"",h.getHeight()+"",((Batter)h).getBatcount()+"",
							((Batter)h).getHit()+"",((Batter)h).getHitAvg()+""};
					for (int i = 0; i < label2.length; i++) {
						label2[i].setText(str[i]);
					}
				}
			}
		}
	}
}
