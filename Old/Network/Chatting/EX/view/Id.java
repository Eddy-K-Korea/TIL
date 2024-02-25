package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import net.WriteClass;

// ID를 입력하기 위한 Form
public class Id extends JFrame implements ActionListener{

	public static TextField tf = new TextField(8);
	Button btn = new Button("input");
	
	WriteClass wc;
	ClientFrame cf;
	
	public Id(WriteClass wc, ClientFrame cf) {
		
		this.wc = wc;
		this.cf = cf;
		
		setTitle("ID input");
		setLayout(null);
		
		Label label = new Label("id:");
		label.setBounds(50, 60, 30, 30);
		add(label);
		
		tf.setBounds(80, 60, 100, 30);
		add(tf);
		
		btn.setBounds(80, 110, 100, 30);
		btn.addActionListener(this);
		add(btn);
		
		setBounds(300, 300, 250, 200);
		setBackground(Color.green);
		setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		wc.sendMessage();	// ID 전송
		
		cf.isFirst = false;		// 첫번째 전송이 끝났음
		cf.setVisible(true);	// 채팅창을 활성화
		
		this.dispose();		
	}
	
	
}





