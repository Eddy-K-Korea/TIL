package Panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.BaseBallDao;
import dto.Human;
import dto.Pitcher;
import view.Insert;
import view.Menu;

public class PitcherPanel extends JPanel implements ActionListener {

	JLabel label[] = new JLabel[6];
	JTextField textfield[] = new JTextField[6];
	JButton MenuBtn, saveBtn;
	
	Insert insert;
	
	public PitcherPanel(Insert insert) {
		this.insert = insert;
		setLayout(null);
	
		String str[] = {"이름: ","나이: ","키: ","승: ","패: ","방어율: "};
		for (int i = 0; i < str.length; i++) {
			label[i] = new JLabel(str[i]);
			label[i].setBounds(30, 50 + 40 * i, 100, 30);
			
			textfield[i] = new JTextField();
			textfield[i].setBounds(180, 50 + 40 * i, 200, 30);
			
			add(label[i]);
			add(textfield[i]);
			
		}
		
		MenuBtn = new JButton("Mune");
		MenuBtn.setBounds(110, 350, 100, 80);
		MenuBtn.addActionListener(this);
		add(MenuBtn);
		
		saveBtn = new JButton("저장");
		saveBtn.setBounds(310, 350, 100, 80);
		saveBtn.addActionListener(this);
		add(saveBtn);
		
		setBounds(40, 40, 500, 450);
		setBackground(Color.white);
		setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		
		if(obj == MenuBtn) {
			insert.dispose();
			new Menu();
		}
		else if(obj == saveBtn) {
			BaseBallDao bd = BaseBallDao.getInstance();
			Human human = null;
			
			for (int i = 0; i < textfield.length; i++) {
				if(textfield[i].equals("")) {
					JOptionPane.showMessageDialog(null, "모두 작성해주세요");
					return;
				}
			}
			
			String name = textfield[0].getText();
			int age = Integer.parseInt(textfield[1].getText());
			double height = Double.parseDouble(textfield[2].getText());
			int win = Integer.parseInt(textfield[3].getText());
			int lose = Integer.parseInt(textfield[4].getText());
			double defense = Double.parseDouble(textfield[5].getText());
			
			human = new Pitcher(0, name, age, height, win, lose, defense);
			
			boolean b = bd.insert(human);
			
			if(b) {
				JOptionPane.showMessageDialog(null, "성공적으로 저장 되었습니다.");
				for (int i = 0; i < textfield.length; i++) {
					textfield[i].setText("");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "저장에 실패 하였습니다.");
			}
		}
	}
}
