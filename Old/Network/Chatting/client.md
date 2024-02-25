# WindowUI
```java
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.ClientSocket;

public class WindowUI extends JFrame implements ActionListener {

	public JTextArea jtextA;      ★ client 소켓에서도 받아야 하기 때문에 public
	public JTextField jtextF;     
	JButton button;
	Container contentPane;
	ClientSocket client;
	public Socket socket;         ★ Client Main 에서 socket 정보를 받아 멤버변수로 선언
	
	public WindowUI(Socket socket) {      ★ 파라미터로 socket을 받
		super("Chatting");
		this.socket = socket;
		
		client = new ClientSocket(this);
		
		JPanel panel = new JPanel();
		jtextA = new JTextArea();
		jtextA.setLineWrap(true);
		
		JScrollPane scrPane = new JScrollPane(jtextA);
		scrPane.setPreferredSize(new Dimension(400, 300));
		panel.add(scrPane);
		
		JPanel botpanel = new JPanel();
		jtextF = new JTextField(20);
		
		button = new JButton("Send");
		button.addActionListener(this);
		botpanel.add(jtextF);
		botpanel.add(button);
		
		contentPane = getContentPane();
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(botpanel, BorderLayout.SOUTH);
		
		setBounds(0, 0, 640, 480);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == button) {
			if(!jtextF.getText().equals("")) {
				String msg = jtextF.getText()+"\n";
				jtextA.append(msg);    ★ 자신이 쓴 문자열 textArea에 표시
				client.send();          ★ client 의 send 메소드로 textField에 있는 문자열 서버로 전송
				jtextF.setText("");
			}
		}
	}
}
```
# client send
```java
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import main.WindowUI;

public class ClientSocket {

	Socket socket;
	WindowUI wu;   ★ UI클래스의 객체를 생성하여 textField의 문자를 받을 수 있게 함.
	
	public ClientSocket(WindowUI wu) {    ★ 생성자에 WindowUI를 파라미터로 받음
		this.wu = wu;
		this.socket = wu.socket;
	}	
	
	public void send() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(socket.getOutputStream());
			String str = wu.jtextF.getText();   ★ 문자열 인식
			pw.println(str);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
# recv (Thread)
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import main.WindowUI;

public class ReadThread extends Thread {
	WindowUI wu;
	Socket socket;
	public ReadThread(Socket socket,WindowUI wu) {  ★ 소켓과 UI를 동시에 파라미터로 받음
		this.socket = socket;
		this.wu = wu;
		
	}

	@Override
	public void run() {
		super.run();
		try {
			while(true) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = reader.readLine();
				wu.jtextA.append(msg+"\n");  ★ 서버로 부터 송신한 문자열을 textArea에 
				Thread.sleep(300);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```
