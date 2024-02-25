# TextField
```java
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class WindowTest extends JFrame{ //implements ActionListener {

	JTextField textField;
	JTextArea textArea;
	
	JButton btn1, btn2;
	
	public WindowTest() {
		super("textfield");
		
		JPanel panel = new JPanel();
		textArea = new JTextArea();			// 게시판에 글을 쓰는 부분
		textArea.setLineWrap(true);			// 글쓰는 부분의 테두리 선을 만들어주는부분
		
		JScrollPane scrPane = new JScrollPane(textArea);
		scrPane.setPreferredSize(new Dimension(400, 300));
		panel.add(scrPane);
		
		JPanel botpan = new JPanel();
		
		textField = new JTextField(20);  // 20칸을 준다. 
		
		btn1 = new JButton("next insert");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	JOptionPane.showMessageDialog(null, "next insert");  확인용
				if(!textField.getText().equals("")) {
					String msg = textField.getText() + "\n";
					textArea.append(msg);
					
					textField.setText("");
				}
			}
		});
		
		btn2 = new JButton("prev insert");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				try {
					textArea.insert(textField.getText()+"\n", textArea.getLineStartOffset(0));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		botpan.add(textField);
		botpan.add(btn1);
		botpan.add(btn2);
		
		Container contentPane = getContentPane();
		
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(botpan, BorderLayout.SOUTH);
		
		setBounds(0, 0, 640, 480);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
  ```
  글을 쓸수 있는 텍스트 상자와 게시판같은 넓은 범위의 글쓰기 기능을 불러온다.
  
  # CheckBox
  ```java
  import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class WindowTest extends JFrame implements ItemListener{

	Checkbox cb1, cb2, cb3, cb4,cb5,cb6;
	Label label1, label2;
	
	JRadioButton radioBtn[];
	String radio[] = {"1점","2점","3점","4점","5점"};
	
	public WindowTest() {
		super("체크 박스");
		/*-----------------------------------------------------------
		  check box (취미 등 여러개를 다중 선택할때 사용)
		  Radio Button (싱글선택)
		  
		  AWT : radio -> check box
		  swing : JRadioButton
		 */-----------------------------------------------------------
		
		CheckboxGroup cbg1 = new CheckboxGroup();
		//cb1,cb2,cb3
		cb1 = new Checkbox("사과", cbg1, true);
		cb2 = new Checkbox("배", cbg1, false);
		cb3 = new Checkbox("수박", cbg1, false);
		
		CheckboxGroup cbg2 = new CheckboxGroup();
		// cb4, cb5
		cb4 = new Checkbox("빨강", cbg2, false);
		cb5 = new Checkbox("파랑", cbg2, true);
		
		cb6 = new Checkbox("exit");
		
		label1 = new Label("label1");
		label2 = new Label("label2");
		
		add(cb1);
		add(cb2);
		add(cb3);
		add(cb4);
		add(cb5);
		add(cb6);
		
		cb1.addItemListener(this);
		cb2.addItemListener(this);
		cb3.addItemListener(this);
		cb4.addItemListener(this);
		cb5.addItemListener(this);
		cb6.addItemListener(this);
		
		add(label1);
		add(label2);
		
		radioBtn = new JRadioButton[5];
		ButtonGroup btng = new ButtonGroup();
		
		for (int i = 0; i < radio.length; i++) {
			radioBtn[i] = new JRadioButton(radio[i]);
			btng.add(radioBtn[i]);
		}
		
		radioBtn[0].setSelected(true);
		radioBtn[1].setSelected(false);
		
		for (int i = 0; i < radio.length; i++) {
			add(radioBtn[i]);
		}
		
		setLayout(new FlowLayout());
		
		setBounds(0, 0, 640, 480);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
//		JOptionPane.showMessageDialog(null, "Click");
		
		Object obj = e.getSource();
		
		if(obj == cb6) {
			Checkbox cb = (Checkbox)obj;
			if(cb.getState()) {	//체크되었다.
				System.exit(0);
			}else {		//체크 안되었음
			}
		}
		else if(obj == cb1 || obj == cb2 || obj == cb3) {
			Checkbox cb = (Checkbox)obj;
			if(cb.getState()) {
				label1.setText(cb.getLabel());
			}
		}
		else if(obj == cb4 || obj == cb5) {
			Checkbox cb = (Checkbox)obj;
			if(cb.getState()) {
				label2.setText(cb.getLabel());
			}
		}
		else if(obj == radioBtn[0] || obj == radioBtn[1]|| obj == radioBtn[2]|| obj == radioBtn[3]|| obj == radioBtn[4]) {
			JRadioButton rb = (JRadioButton)obj;
			
			label1.setText(rb.getLabel());
		}
	}
}
```
체크박스로 취미 선택 이나 여러개를 선택 할 때 사용 할 수 있다.
# Choice (생년월일 선택 할 때나오는 박스)
```java
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowTest extends Frame implements WindowListener{

	
	Choice choice;		//select option   swing -> JComboBox
	Label label;
	
	public WindowTest() {
		
		setLayout(new FlowLayout());
		
		choice = new Choice();
		choice.add("사과");
		choice.add("배");
		choice.add("수박");
		choice.add("참외");
		choice.add("포도");
		
		choice.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				label.setText(choice.getSelectedItem());
			}
		});
		
		add(choice);
		
		label = new Label("---");
		add(label);
		setBounds(0, 0, 640, 480);
		setVisible(true);
		
		addWindowListener(this);
	}
  ```
  swing 의 경우 JComboBox 라는 이름을 사용한다.
  
  # Window 창 닫고 키기
  ```java
  import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windowone extends Frame {

	public Windowone() {
		
		setLayout(null);
		Button btn = new Button("move Window");
		btn.setBounds(100, 100, 100, 30);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // 현재의 window만 닫는다.
				new Windowtwo();
			}
		});
		add(btn);

		setBounds(0, 0, 640, 480);
		setVisible(true);
		setBackground(Color.red);
	
	}
}
```
우선 첫번째 윈도우 창을 키고 버튼을 누르면 닫고 새로운 윈도우 창을 열리게 하려한다.

Over ride 된 부분에 dispose 함수르 이용하여 현재 window창을 닫고 새로 만들어 놓은 windowtwo 클래스를 생성하여 열어준다.
```java
import java.awt.Color;
import java.awt.Frame;

public class Windowtwo extends Frame {

	public Windowtwo() {
	
		setBounds(0, 0, 800, 600);
		setVisible(true);
		setBackground(Color.green);
	
	}
}
```
