package main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import net.ReadThread;
import view.ClientFrame;
import view.Id;

public class mainClass {

	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("192.168.7.66", 9000);
			System.out.println("Connection Success!");
			
			ClientFrame cf = new ClientFrame(socket);
			
			new ReadThread(socket, cf).start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}







