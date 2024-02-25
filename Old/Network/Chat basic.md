# Chatting program Basic
### 서버와 쓰레드를 이용하여 채팅 프로그램을 구현 할 수 있다.
### 먼저 서버를 구축하는 예제를 보자.

## 1. Server
```java
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
	List<Socket> list = new ArrayList<Socket>();    ★ 클라이언트의 소켓정보를 저장
	
  try {
			ServerSocket serSocket = new ServerSocket(9000);
      
			while(true) {                           ★ accept 상태는 유지하면서 send , recv를 해야함
				System.out.println("접속 대기중...");  ★ client를 기다림
				clientsocket = serSocket.accept();   ★ client가 들어오면 담당 socket
													                   ★ 접속된 client 의 정보(IP,port) 담고 있음
				list.add(clientsocket);              ★ 소켓의 정보들을 list에 저장해준다.
				System.out.println("client IP: "+clientsocket.getInetAddress()
											+" Port: "+clientsocket.getPort());
				
			
				new ServerThread(clientsocket, list).start();  ★ 멈춰줄 필요가 없기 때문에 인스턴스 필요 없다. 
        
	} catch (IOException e) {
	    e.printStackTrace();
	} 
 }
}
```
## 2. Server Thread

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread {

	Socket clientsocket;
	List<Socket> list;
	
	public ServerThread(Socket clientsocket,List<Socket> list) { ★소켓과 list를 파라미터로 받는다.
		this.clientsocket = clientsocket;   ★현재 자신의 소켓
		this.list = list;                   ★ 소켓의 묶음 (다른 IP로 보내주려고)
	}

	@Override
	public void run() {
		super.run();
		
		  try {
		
			while(true) {
			
				//recv (받는다)
				BufferedReader reader = 
						new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
				String packetStr = reader.readLine(); ★실제 전송
				
				System.out.println("클라이언로부터 받은 Packet: "+packetStr);
				
				
				//send (보내다)
				for (int i = 0; i < list.size(); i++) {
					
				Socket soc = list.get(i);
					if(clientsocket != soc) {     ★ 자기 자신 이외의 소켓에 전부 전송
						PrintWriter pw = new PrintWriter (soc.getOutputStream());
						pw.println(packetStr);
						pw.flush();
					}
				}
				Thread.sleep(300);    ★ 쓰레드의 어느정도 템포를 준다.
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
```
서버의 경우 소켓의 정보를 받는 accept은 항상 유지를 해야한다.

recv와 send의 경우 Thread를 통해 한번씩 체크하여 데이터를 송/수신하면 효율적으로 메모리 운영도 가능하며 처리할 수 있다.

## 3.Client 
```java
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import thread.ReadThread;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
    InetSocketAddress sockAddr = new InetSocketAddress("192.168.7.72", 9000);
		
		Socket socket = new Socket();
		
		try {
			socket.connect(sockAddr, 10000);  ★ 뒤의 timeout 은 밀리세컨으로 1000이 1초 
			
			InetAddress inetAddr; // 접속 확인용
			if((inetAddr = socket.getInetAddress()) != null){
				System.out.println("Server connect success: "+ inetAddr);
				
			}else {
				System.out.println("Server connect fail");
			}
			new ReadThread(socket).start();   ★ while 문 안에서 계속해서 Thread를 생성해줄 필요 없이 recv만 돌려주면 되어 while 밖에서 실행
			
			while(true) {
				
				System.out.print("전송할 텍스트 입력: ");
				String pecket = sc.next();
        
				// send (보내다)  ★ 
				PrintWriter writer = new PrintWriter(socket.getOutputStream()); 
				writer.println(pecket);
				writer.flush();   //  마무리 할때 무조건 기재해 주어야한다.
        }
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
```
채팅시 클라이언트는 먼저 메세지를 받아야한다. 그래서 우선 Thread를 통해 데이터 수신을체크하고 송신은 

언제할지 모르기 때문에 항상 유지를 해야한다. 그래서 while 문안에 배치하여 구현한다.

### 4. client Thread (readThread)
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {
	
	Socket socket;
	
	public ReadThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		super.run();
		
		// recv (받는다)
		BufferedReader reader;
		try {
			while(true) {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String str = reader.readLine();
				System.out.println("Server로 부터 받은 데이터: "+str);
				Thread.sleep(300);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
```			
