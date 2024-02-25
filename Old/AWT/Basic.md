# AWT
## AWT 란?
- Abstract Window Toolkit 의 약자로 user Interface와 연결을 해준다.
- 안드로이드 스튜디오와 비슷한 문법으로 어플리케이션을 만들때 사용한다.

### 사용

```java
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowTest extends Frame implements WindowListener{

	public WindowTest() {
    super("Label");       //setTitle() 과 동일
    setLayout(null);      // 레이아웃을 개발자 설정으로 하기위해 null 값으로 
		setSize(640, 480);    //해상도    윈도우 폭, 높이
		setLocation(0, 0);    // 윈도우 실행 위치 (0,0) 일때 좌측 상단
    ---------------------------------------------------------------------------------
    → setBounds(0, 0, 640, 480); 위의 두 소스코드를 한줄로 표현 할 수 있다.
    ---------------------------------------------------------------------------------
		setVisible(true);     // 윈도우 활성화 (시각화)
		
		
		//Listener 
		addWindowListener(this);
    
    JOptionPane.showMessageDialog(null, "WindowTest() 실행");
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowActivated");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowClosed");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowClosing");
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {	// 비활성화
		// TODO Auto-generated method stub
		System.out.println("windowDeactivated");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowDeiconified");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowIconified");
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowOpened");
	}
}
```
1. Frame Class을 import하여 불러온다. 

2. Window 창에 추가 밑 반응하기 위해서는 WindowListener 인터페이스를 불러온다.

3. super() 안에 들어가는 문자열은 window창의 맨 위 창틀에 기재가 된다.

4. JOptionpane의 showMessege를 통해 별도의 메세지 창을 띄울 수 있다.

5. 레이아웃을 지정해 줄 수 있지만 null값으로 넣어 놓고 panel과 Label, button을 생성하고 setBounds를 통해 위치를 지정할 수 있다.

6. Panel은 종이위에 종이로 Frame 위에 Frame을 설정하는 것으로 볼 수 있다.

### Panel 
1. 패널 클래스를 만든다.

```java
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class MyPanel extends Panel{

	
	public MyPanel() {

		setLayout(new GridLayout(3, 1));
		setBackground(Color.red);
		
		Label label1 = new Label("MyPanel label 1");
		add(label1);
		Label label2 = new Label("MyPanel label 2");
		add(label2);
		Label label3 = new Label("MyPanel label 3");
		add(label3);
		
	
	}
}
```
2. WindowTest 클래스에 패널클래스의 인스턴스를 생성하고 레이아웃 및 window창 설정을 한다.
```java
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowTest extends Frame implements WindowListener {

	public WindowTest() {

		super("Panel");
		
		setLayout(new GridLayout(2, 1));
		//panel : Frame(Window) 위에 Frame
		//         종이위에 종이 
//		Label label = new Label("label");
//		add(label);
		
    // 위의 MyPanel 클래스의 객체로 add하여 window에 추가하였다.
		MyPanel mypanel = new MyPanel();
		add(mypanel);
    
		//panel  
    
		Panel panel = new Panel();
		panel.setBackground(Color.green);
		panel.setLayout(new GridLayout(1, 2));
		add(panel);
		
		Label label1 = new Label("label 1");
		panel.add(label1);
		
		Button button = new Button("Button");
		panel.add(button);
		
		
		setBounds(0, 0, 800, 600);
		setVisible(true);
		
		
	
	
		addWindowListener(this);
	}
	
	
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
```
### Button

- 버튼을 이용해 Label의 상태를 변경 할 수 있다.

```java
public class WindowTest extends Frame implements WindowListener, ActionListener{

	

	Label label;
	Button button1, button2;
	
	public WindowTest() {
		setLayout(null);
		/*
				resource(자원)  : button, label, textfield -> Handle(번호)
		*/
		
		label = new Label("label");
		label.setBounds(100, 100, 300, 30);
		label.setBackground(Color.yellow);
		add(label);
		
		button1 = new Button();
		button1.setLabel("button1");
		button1.setBounds(100, 160, 150, 30);
		button1.addActionListener(this);
		add(button1);
		
		button2 = new Button("button2");
		button2.setBounds(300, 160, 150, 30);
		button2.addActionListener(this);
		add(button2);
		
		
		setBounds(0, 0, 640, 480);
		setVisible(true);
		
		
		
		addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Button btn = (Button)e.getSource();
		String btnTitle = btn.getLabel();
		
		if(btnTitle.equals("button1")) {
			
			label.setText("1 button click");
		}
		else if(btnTitle.equals("button2")) {
			label.setText("2 button click");
		}
	}
 ```
 
 위 코드는 button1을 누르면 Label에 "1 button click" 의 글이 표시가 되고 button2를 누르면 "2 button click" 가 표시가 된다.
