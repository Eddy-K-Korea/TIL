package net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import view.ClientFrame;
import view.Id;

public class WriteClass {
	
	Socket socket;
	ClientFrame cf;
	
	String id;
	String msg;	

	public WriteClass(ClientFrame cf) {
		this.cf = cf;
		this.socket = cf.socket;
	}
	
	public void sendMessage() {
		
		try {
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			
			// 첫번째 전송 -> id
			if(cf.isFirst == true) {
				InetAddress iaddr = socket.getLocalAddress();
				String ip = iaddr.getHostAddress();		// xxx.xxx.xxx.xxx
				id = Id.tf.getText();
				
				msg = "[" + id + "]님 로그인(" + ip + ")";
				
				cf.setTitle("접속자 ID:" + id);	// 타이틀 바에 ID 출력					
			}			
			// 그 다음부터 전송
			else {
				msg = "[" + id + "]" + cf.textF.getText();				
			}
			pw.println( msg );
			pw.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}





