package view;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.MemberDao;
import dao.OrderDao;
import dto.OrderDto;

public class OrderView extends JFrame implements ActionListener {

	JLabel lb, lb1, lb2, lb3, lb4;
	JButton menu, order;
	JPanel toppan, menupan, botpan, midpan, sizepan, shotpan, syruppan, pan;
	JTextField tf;
	JComboBox cb;
	JRadioButton[] rb1, rb2;
	Checkbox cb1, cb2;
	

	public OrderView() {
		super("주문");
		setLayout(null);

		pan = new JPanel();
		pan.setLayout(null);
		pan.setBounds(0, 0, 600, 500);
		pan.setBackground(Color.white);
		
		
		toppan = new JPanel();
		toppan.setLayout(new GridLayout(1, 2,130,0));
		toppan.setBounds(130, 50, 370, 60);
		toppan.setBackground(Color.white);
		lb1 = new JLabel("커피 한잔");
		toppan.add(lb1);

		menu = new JButton("가격표");
		menu.addActionListener(this);
		toppan.add(menu);

		String[] coffee = { "커피를 선택", "헤이즐럿 카라멜 모카", "카라멜 마끼아또", "화이트 초콜렛 모카", "카라멜 모카", "카페 모카", "카라멜 라떼", "카페 라떼",
				"카푸치노", "아메리카노", "오늘의 커피" };
		cb = new JComboBox(coffee);
		cb.setBounds(70, 100, 430, 30);
		cb.setBackground(Color.orange);
		cb.addActionListener(this);
		
		menupan = new JPanel();
		menupan.setLayout(new GridLayout(1, 1));
		menupan.setBounds(100, 120, 400, 30);
		menupan.add(cb);

		midpan = new JPanel();
		midpan.setLayout(new GridLayout(1, 3));
		midpan.setBounds(80, 170, 440, 250);
		midpan.setBackground(Color.white);
		
		sizepan = new JPanel();
		sizepan.setLayout(new GridLayout(5, 1));
		sizepan.setBackground(Color.white);
		lb = new JLabel("SIZE");
		sizepan.add(lb);
		String sz[] = { "Short", "Tall", "Grande" };
		rb1 = new JRadioButton[3];
		ButtonGroup btng = new ButtonGroup();
		for (int i = 0; i < rb1.length; i++) {
			rb1[i] = new JRadioButton(sz[i]);
			rb1[i].setBackground(Color.white);
			btng.add(rb1[i]);
			sizepan.add(rb1[i]);
		}

		rb1[0].setSelected(true);
		rb1[1].setSelected(false);
		rb1[2].setSelected(false);

		midpan.add(sizepan);

		syruppan = new JPanel();
		syruppan.setLayout(new GridLayout(5, 1));
		syruppan.setBackground(Color.white);
		lb4 = new JLabel("Syrup");
		syruppan.add(lb4);
		String sz2[] = { "바닐라", "카라멜", "헤이즐럿" };
		rb2 = new JRadioButton[3];
		ButtonGroup btng2 = new ButtonGroup();
		for (int i = 0; i < rb2.length; i++) {
			rb2[i] = new JRadioButton(sz2[i]);
			rb2[i].setBackground(Color.white);
			btng2.add(rb2[i]);
			syruppan.add(rb2[i]);
		}
		rb2[0].setSelected(false);
		rb2[1].setSelected(false);
		rb2[2].setSelected(false);

		midpan.add(syruppan);

		shotpan = new JPanel();
		shotpan.setLayout(new GridLayout(5, 1));
		shotpan.setBackground(Color.white);
		lb2 = new JLabel("The Other");
		shotpan.add(lb2);
		cb1 = new Checkbox("샷 추가", false);
		cb2 = new Checkbox("휘핑크림", false);

		shotpan.add(cb1);
		shotpan.add(cb2);

		midpan.add(shotpan);

		botpan = new JPanel();
		botpan.setLayout(new GridLayout(1, 3));
		botpan.setBounds(237, 400, 260, 20);
		botpan.setBackground(Color.white);

		tf = new JTextField();
		tf.setBounds(20, 100, 80, 20);
		tf.setHorizontalAlignment(JTextField.RIGHT);
		botpan.add(tf);

		lb3 = new JLabel("잔");
		lb3.setBounds(100, 100, 40, 20);
		botpan.add(lb3);

		order = new JButton("주문");
		order.setBounds(300, 100, 80, 50);
		order.addActionListener(this);
		botpan.add(order);

		add(toppan);
		add(menupan);
		add(botpan);
		add(midpan);
		add(pan);
		setBounds(600, 200, 600, 500);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == menu) {
			new CoffeeMenu();
		} else if (obj == order) {
			String id = MemberDao.getInstance().getLoginId();
			String coffee = (String)cb.getSelectedItem();
				if(coffee.equals("커피를 선택")) {
					JOptionPane.showMessageDialog(null, "커피를 선택하세요");
				}
			String c_size = "Short";
			if(tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "개수를 입력해 주세요");
			}
			int count_c = Integer.parseInt(tf.getText());
			
			
			if(rb1[0].isSelected()) {
				c_size = "Short";
			}
			else if(rb1[1].isSelected()) {
				c_size = "Tall";
			}
			else if(rb1[2].isSelected()) {
				c_size = "Grande";
			}
			String syrup = "";
			if(rb2[0].isSelected()) {
				syrup = "바닐라";
			}
			else if(rb2[1].isSelected()) {
				syrup = "카라멜";
			}
			else if(rb2[2].isSelected()) {
				syrup = "헤이즐럿";
			}
			else {
				syrup = "선택 안함";
			}
			String other="선택 안함";
			if(cb1.getState() == true && cb2.getState() == false) {
				other = "샷 추가";
			}
			else if(cb1.getState() == false && cb2.getState() == true) {
				other = "휘핑 크림";
			}
			else if(cb1.getState() == true && cb2.getState() == true){
				other = "샷+휘핑";
			}
			
			OrderDto dto = new OrderDto(id, coffee, c_size, syrup, other, count_c);
			OrderDao dao = OrderDao.getInstance();
			int insert = dao.insertOrder(dto);
			if(insert == 1) {
				JOptionPane.showMessageDialog(null, "주문 완료 되었습니다");
				dispose();
				new CheckView();
			}
		}
	}

}
