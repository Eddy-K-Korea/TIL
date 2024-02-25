package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AbookDao;
import dao.MemberDao;
import dto.AbookDto;

public class AddView extends JFrame implements ActionListener {

	JPanel jp;
	JButton add, back;
	JTextField amount, content;
	JLabel lb1, lb2;
	int index;
	public AddView() {

		super("추가");
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.white);
		jp.setBounds(0, 0, 400, 500);

		String io[] = { "수입", "지출" };
		JComboBox choice = new JComboBox(io);
		choice.setBounds(50, 80, 80, 30);
		choice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent b) {
				JComboBox cb = (JComboBox)b.getSource();    // 수입 . 지출 선택으로 데이터베이스 저장시 I, O로 
				index = cb.getSelectedIndex();

			}
		});
		jp.add(choice);

		lb1 = new JLabel("금액");
		lb1.setBounds(70, 130, 50, 40);

		lb2 = new JLabel("내용");
		lb2.setBounds(70, 190, 50, 40);

		jp.add(lb1);
		jp.add(lb2);

		amount = new JTextField();
		amount.setBounds(150, 130, 180, 40);
		content = new JTextField();
		content.setBounds(150, 190, 180, 40);

		jp.add(amount);
		jp.add(content);

		add = new JButton("추가");
		add.setBounds(145, 250, 80, 40);
		add.addActionListener(this);
		back = new JButton("메뉴로 돌아가기");
		back.setBounds(40, 320, 300, 40);
		back.addActionListener(this);

		jp.add(add);
		jp.add(back);

		add(jp);

		setBounds(600, 200, 400, 500);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bTitle =(JButton)e.getSource();
		AbookDao dao = AbookDao.getInstance();
		
		int n = 0;
		
		if(bTitle == add) {
			if(amount.getText().equals("")||content.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸을 채워주세요");
			}
			else {
				if(index == 0) {
					String str = amount.getText().trim();
					int amt = Integer.parseInt(str);
					String cnt = content.getText().trim();
					String id = MemberDao.getInstance().getLoginId();
					 
					AbookDto dto = new AbookDto(id, "I", amt, cnt, "");
					
					n = dao.insertData(dto);
					
					
				}
				else if(index == 1) {
					String str = amount.getText().trim();
					int amt = Integer.parseInt(str);
					String cnt = content.getText().trim();
					String id = MemberDao.getInstance().getLoginId();
					 
					AbookDto dto = new AbookDto(id, "O", amt, cnt, "");
					
					n = dao.insertData(dto);
				}
				if(n == 1) {
					JOptionPane.showMessageDialog(null, "내용이 추가 되었습니다.");
					amount.setText("");
					content.setText("");
				}
				else {
					if(amount.getText().equals("")||content.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "빈칸을 채워주세요");
					}
				}
			}
			
		}
		if(bTitle == back) {
			dispose();
			new MenuView();
		}
	}
}
