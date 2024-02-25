package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import thread.ServerThread;

public class Main {

	public static void main(String[] args) {
		
		Socket clientsocket = null;
		List<Socket> list = new ArrayList<Socket>();  
		
		try {
			ServerSocket serSocket = new ServerSocket(6000);
      
			while(true) { 
				System.out.println("접속 대기중..."); 
				clientsocket = serSocket.accept();  
													
				list.add(clientsocket);
				System.out.println("client IP: "+clientsocket.getInetAddress()
											+" Port: "+clientsocket.getPort());
				
			
				new ServerThread(clientsocket, list).start(); 
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
