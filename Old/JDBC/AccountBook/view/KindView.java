package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import dao.AbookDao;
import dao.MemberDao;
import dto.AbookDto;

public class KindView extends JFrame implements ActionListener {

	JPanel jp;
	JTextArea ta;
	JTextField tf;
	JButton search, back;
	
	public KindView() {
		super("항목별 검색");
		setLayout(null);
		
		jp = new JPanel();
		jp.setBounds(0, 0, 400, 500);
		jp.setBackground(Color.white);
		
		tf = new JTextField();
		tf.setBounds(50, 50, 200, 30);
		add(tf);
		
		
		search = new JButton("검색");
		search.setBounds(280, 50, 80, 30);
		search.addActionListener(this);
		add(search);
		
	
		ta = new JTextArea();			
		ta.setLineWrap(true);			
		ta.setBounds(40, 100, 300, 200);
		ta.setBackground(Color.yellow);

		add(ta);
		
		back = new JButton("메뉴로 돌아가기");
		back.setBounds(200, 350, 130, 30);
		back.addActionListener(this);
		add(back);
		
		JRootPane  rootPane  = getRootPane();   // 검색 버튼 엔터로 작동
        	rootPane.setDefaultButton(search);  
		
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
			if(tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "검색어를 입력해주세요");
			}
			else {
				String sh = tf.getText().trim();
				AbookDto dto = adao.kindSearch(id, sh);
				if( dto != null) {
					String str = dto.getId()+"님, "+dto.getAmount()+""+"원, "+IO(dto.getIo_kind())+", "+dto.getContent()+", ["+dto.getDate()+"]";
					try {
						ta.insert(str+"\n", ta.getLineStartOffset(0));
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "없는 항목 입니다.");
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
