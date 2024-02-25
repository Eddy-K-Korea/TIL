package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import dao.AbookDao;
import dao.MemberDao;
import dto.AbookDto;

public class DateContent extends JFrame implements ActionListener {

	JPanel jp;
	JTextField tf1[];
	JTextField tf2[];
	JLabel lb1[];
	JLabel lb2[];
	JButton search, back;
	JTextArea textArea;
	
	public DateContent() {
		super("기간별 내역");
		
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0, 0, 400, 150);
		jp.setBackground(Color.white);
		
		lb1 = new JLabel[3];
		tf1 = new JTextField[3];
		String str1[] = {"년","월","일"};
		for (int i = 0; i < str1.length; i++) {
			lb1[i] = new JLabel(str1[i]);
			tf1[i] = new JTextField();
			lb1[i].setBounds(90 + 80*i, 20, 20, 15);
			tf1[i].setBounds(50+80*i, 20, 40, 20);
			add(lb1[i]);
			add(tf1[i]);
		}
		
		lb2 = new JLabel[3];
		tf2 = new JTextField[3];
		String str2[] = {"년","월","일"};
		for (int i = 0; i < str1.length; i++) {
			lb2[i] = new JLabel(str2[i]);
			tf2[i] = new JTextField();
			lb2[i].setBounds(110 + 80*i, 50, 20, 15);
			tf2[i].setBounds(70+80*i, 50, 40, 20);
			add(lb2[i]);
			add(tf2[i]);
		}
	
	
		search = new JButton("결과보기");
		search.setBounds(230, 90, 130, 30);
		search.addActionListener(this);
		
		add(search);
		
		textArea = new JTextArea();			
		textArea.setLineWrap(true);		
		textArea.setLocation(50, 150);
		textArea.setSize(280, 200);
		textArea.setBackground(Color.yellow);
		
		add(textArea);
		
		back = new JButton("메뉴로 돌아가기");
		back.setBounds(200, 370, 130, 30);
		back.addActionListener(this);
		add(back);		
	
		add(jp);
		
		setBounds(600, 200, 400, 500);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String id = MemberDao.getInstance().getLoginId();
		AbookDao adao = AbookDao.getInstance();
		if(obj == back) {
			dispose();
			new MenuView();
		}
		else if(obj == search) {
			if(tf1[0].getText().equals("")||tf1[1].getText().equals("")||tf1[2].getText().equals("")) {
				JOptionPane.showMessageDialog(null, "날짜를 입력해주세요");
			}
			else {
				String str = tf1[0].getText().trim() + tf1[1].getText().trim() + tf1[2].getText().trim();
				String str2 = tf2[0].getText().trim() + tf2[1].getText().trim() + tf2[2].getText().trim();
				
				List<AbookDto> list = adao.dateContent(id, str, str2);
				if( list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
						String s = list.get(i).getId()+" 님,  "+list.get(i).getAmount()+""+" 원,  "+IO(list.get(i).getIo_kind())+", "+list.get(i).getContent()+" , ["+list.get(i).getDate()+"]";
						try {
							textArea.insert(s+"\n", textArea.getLineStartOffset(0));
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
				}	
			}	
		}
	}
	
	public String IO(String str) {
		String result;
		if(str.equals("I")) {
			result = "수입";
		}
		else {
			result = "지출";
		}
		return result;
	}
}
