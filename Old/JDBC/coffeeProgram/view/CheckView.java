package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dao.MemberDao;
import dao.OrderDao;
import dto.OrderDto;

public class CheckView extends JFrame implements ActionListener {

	JLabel title,lb1;
	JLabel col [];
	JTextArea ta;
	JButton back, exit;
	JPanel jp;
	List<String> cont = new ArrayList<String>();
	
	public CheckView() {
		super("주문");
		setLayout(null);
		
		jp = new JPanel();
		jp.setBounds(0, 0, 540, 500);
		jp.setBackground(Color.pink);
		
		title = new JLabel("주문 내역");
		title.setBounds(230, 50, 100, 30);
		add(title);
		
		lb1 = new JLabel("Espresso Beverages");
		lb1.setBounds(60, 100, 130, 30);
		add(lb1);
		
		col = new JLabel[5];
		String[] str = {"크기", "시럽","잔","other","총액"};
		for (int i = 0; i < col.length; i++) {
			col[i] = new JLabel(str[i]);
			col[i].setBounds(190+40*i, 100, 400, 30);
			add(col[i]);
		}
	
		ta = new JTextArea();
		ta.setBounds(50, 130, 420, 260);
		add(ta);
		List<String>str2 = content();
		for (int i = 0; i < str2.size(); i++) {
			ta.append(str2.get(i)+ "\n");
		}
		
		
		back = new JButton("주문 화면");
		exit = new JButton("종료");
		back.setBounds(300, 400, 100, 30);
		exit.setBounds(412, 400, 60, 30);
		back.addActionListener(this);
		exit.addActionListener(this);
		add(back);
		add(exit);
		add(jp);
	
		setBounds(600, 200, 540, 500);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = MemberDao.getInstance().getLoginId();
		Object obj = e.getSource();
		if(obj == back) {
			dispose();
			new OrderView();
		}
		else if(obj == exit) {
			JOptionPane.showMessageDialog(null, "이용해 주셔서 감사합니다.");
			OrderDao dao = OrderDao.getInstance();
			dao.deleteData(id);
			System.exit(0);
		}	
	}
	
	public List<String> content() {
		MemberDao dao = MemberDao.getInstance();
		OrderDao odao = OrderDao.getInstance();
		
		String id = dao.getLoginId();
		List<OrderDto> list = odao.contentData(id);
		
		
		int price = 0;
		String str = null;
		
		for (int i = 0; i < list.size(); i++) {
			String coffee = list.get(i).getCoffee();
			String c_size = list.get(i).getC_size();
			if(coffee.equals("헤이즐럿 카라멜 모카")) {
				if(c_size.equals("Short")) {
					price = 4800;
				}
				else if(c_size.equals("Tall")) {
					price = 5300;
				}
				else {
					price = 5800;
				}
			}
			else if(coffee.equals("카라멜 마끼아또")||coffee.equals("화이트 초콜렛 모카")||coffee.equals("카라멜 모카")) {
				if(c_size.equals("Short")) {
					price = 4300;
				}
				else if(c_size.equals("Tall")) {
					price = 4800;
				}
				else {
					price = 5300;
				}
			}
			else if(coffee.equals("카페 모카")||coffee.equals("카라멜 라떼")) {
				if(c_size.equals("Short")) {
					price = 3800;
				}
				else if(c_size.equals("Tall")) {
					price = 4300;
				}
				else {
					price = 4800;
				}
			}
			else if(coffee.equals("카페 라떼")||coffee.equals("카푸치노")) {
				if(c_size.equals("Short")) {
					price = 3300;
				}
				else if(c_size.equals("Tall")) {
					price = 3800;
				}
				else {
					price = 4300;
				}
			}
			else if(coffee.equals("아메리카노")) {
				if(c_size.equals("Short")) {
					price = 2800;
				}
				else if(c_size.equals("Tall")) {
					price = 3300;
				}
				else {
					price = 3800;
				}
			}
			else {
				if(c_size.equals("Short")) {
					price = 2500;
				}
				else if(c_size.equals("Tall")) {
					price = 3000;
				}
				else {
					price = 3500;
				}
			}
			str = coffee +" / "
					+c_size+" / "
					+ list.get(i).getSyrup()+" / " 
					+ list.get(i).getCount()+" 잔/ " 
					+ list.get(i).getOther()+" / "
					+ price*list.get(i).getCount()+"원";
		
			cont.add(str);
		}
		return cont;
	}

}
