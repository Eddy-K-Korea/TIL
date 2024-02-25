package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread {

	Socket clientsocket;
	List<Socket> list;
	
	public ServerThread(Socket clientsocket,List<Socket> list) {
		this.clientsocket = clientsocket; 
		this.list = list; 
	}

	@Override
	public void run() {
		super.run();
		
		try {
		
			while(true) {
			
				//recv (받는다)
				BufferedReader reader = 
						new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
				String packetStr = reader.readLine(); //실제 전송
				
				System.out.println("클라이언로부터 받은 Packet: "+packetStr);
				
				
				//send (보내다)
				for (int i = 0; i < list.size(); i++) {
					
				Socket soc = list.get(i);
					if(clientsocket != soc) { //자기 자신 이외의 소켓에 전부 전송
						PrintWriter pw = new PrintWriter (soc.getOutputStream());
						pw.println(packetStr);
						pw.flush();
					}
				}
				Thread.sleep(300);  //어느정도 템포를 준다.
			}	
			
		}catch(Exception e) {
			System.out.println("연결이 끊긴 IP: "+ clientsocket.getInetAddress());
			//담당 소켓의(끊긴) IP 출력
			list.remove(clientsocket); //Object로 리스트에서 지워 준다.
			
			// 남은 client 확인
			for (Socket s : list) {
				System.out.println("접속되어 있는 IP: "+ s.getInetAddress() + " port: "+s.getPort());
			}
			try {
				clientsocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
