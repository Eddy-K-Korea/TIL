package Panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.BaseBallDao;
import view.AllPrint;
import view.Menu;

public class AllPrintPanel extends JPanel implements ActionListener{

	JLabel label;
	JLabel label2[] = new JLabel[dao.BaseBallDao.getInstance().list.size()];
	JButton btnmenu, btnAllPrint;
	AllPrint allPrint;
	
	public AllPrintPanel(AllPrint allprint) {

		this.allPrint = allprint;
		
		BaseBallDao dao = BaseBallDao.getInstance();
		
		setLayout(null);
		
		label = new JLabel("AllPrint");
		label.setBounds(285, 0, 100, 100);
		add(label);
		
		for (int i = 0; i < label2.length; i++) {
			label2[i] = new JLabel();
			label2[i].setBounds(60, 80+20*i, 200, 15);
			add(label2[i]);
		}
		
		btnmenu = new JButton("Menu");
		btnmenu.setBounds(160, 350, 100, 50);
		btnmenu.addActionListener(this);
		add(btnmenu);
		
		btnAllPrint = new JButton("Print");
		btnAllPrint.setBounds(310, 350, 100, 50);
		btnAllPrint.addActionListener(this);
		add(btnAllPrint);
		
		setBounds(10, 20, 600, 400);
		setBackground(Color.white);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		
		if(obj == btnmenu) {
			allPrint.dispose();
			new Menu();
		}
		else if(obj == btnAllPrint) {
			BaseBallDao dao = BaseBallDao.getInstance();
			String str[] = dao.allPrint();
			for (int i = 0; i < str.length; i++) {
				label2[i].setText(str[i]);
			}
		}
	}
}
