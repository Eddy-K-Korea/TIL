package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.WriteClass;

public class ClientFrame extends JFrame implements WindowListener, ActionListener {

	public Socket socket;
	WriteClass wc;	// 전송 클래스
	
	public JTextField textF = new JTextField(14);
	public JTextArea textA = new JTextArea();
	
	JButton btnTransfer = new JButton("send");
	JButton btnExit = new JButton("exit");
	
	JPanel panel = new JPanel();
	
	public boolean isFirst = true;	// 첫 전송을 판정 -> id
	
	public ClientFrame(Socket socket) {
		super("chatting");
		
		
		this.socket = socket;
		
		wc = new WriteClass(this);	// 전송 클래스 생성
		
		new Id(wc, this);
		
		JScrollPane scrPane = new JScrollPane(textA);
		scrPane.setPreferredSize(new Dimension(200, 120));
		
		add("Center", scrPane);
		
		panel.add(textF);
		panel.add(btnTransfer);
		panel.add(btnExit);
		
		add("South", panel);
		
		btnTransfer.addActionListener(this);
		btnExit.addActionListener(this);
		
		setBounds(200, 200, 450, 600);
		setVisible(false);	
		
		addWindowListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnTransfer) {
			
			if(textF.getText().trim().equals("")) return;
			
			String id = Id.tf.getText();
			
			// TextArea 문자열 작성해서 추가
			textA.append("[" + id + "]" + textF.getText() + "\n");
			
			// server로 전송
			wc.sendMessage();
			
			textF.setText("");
		}
		else if(obj == btnExit) {
			
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
