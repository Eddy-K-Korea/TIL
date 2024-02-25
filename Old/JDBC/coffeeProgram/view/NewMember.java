package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.MemberDao;

public class NewMember extends JFrame implements ActionListener {
	JLabel lb1, lb2, lb3, lb4, lb5;
	JTextField tx1, tx2, tx3, tx4;
	JButton btn1, btn2;
	JPanel jp = new JPanel();

	public NewMember() {
		super("회원가입");
		setLayout(null);
		jp.setBounds(0, 0, 400, 500);
		jp.setBackground(Color.yellow);

		lb1 = new JLabel("회원가입");
		lb1.setBounds(160, 30, 100, 50);
		add(lb1);

		lb2 = new JLabel("ID");
		lb3 = new JLabel("PW");
		lb4 = new JLabel("이름");
		lb5 = new JLabel("나이");

		lb2.setBounds(80, 100, 100, 30);
		lb3.setBounds(80, 160, 100, 30);
		lb4.setBounds(80, 220, 100, 30);
		lb5.setBounds(80, 280, 100, 30);

		add(lb2);
		add(lb3);
		add(lb4);
		add(lb5);

		tx1 = new JTextField();
		tx2 = new JTextField();
		tx3 = new JTextField();
		tx4 = new JTextField();

		tx1.setBounds(135, 100, 150, 30);
		tx2.setBounds(135, 160, 150, 30);
		tx3.setBounds(135, 220, 150, 30);
		tx4.setBounds(135, 280, 150, 30);

		add(tx1);
		add(tx2);
		add(tx3);
		add(tx4);

		btn1 = new JButton("중복");
		btn1.setBounds(290, 100, 80, 30);
		btn1.addActionListener(this);
		btn2 = new JButton("회원가입");
		btn2.setBounds(40, 330, 300, 60);
		btn2.addActionListener(this);

		add(btn1);
		add(btn2);

		add(jp);
		setBounds(600, 200, 400, 500);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MemberDao dao = MemberDao.getInstance();
		Object obj = e.getSource();
		String id = tx1.getText().trim();
		
		if (obj == btn1) {
			boolean findId = dao.getId(id);
			if (findId == true) {
				JOptionPane.showMessageDialog(null, "이미 있는 ID 입니다.");
				tx1.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "사용 가능한 ID 입니다.");
			}
		} else if (obj == btn2) {
			if (tx1.getText().equals("") || tx2.getText().equals("") || tx3.getText().equals("")
					|| tx4.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸을 채워주세요");
			}else {
				String pw = tx2.getText().trim();
				String name = tx3.getText().trim();
				int age = Integer.parseInt(tx4.getText().trim());
				int add = dao.addMember(id, pw, name, age);
				if (add == 1) {
					JOptionPane.showMessageDialog(null, "회원가입 성공");
					dispose();
					new LoginView();
				}
			}
		}
	}
}
