# Network
## Server
### 종류
- TCP (Transmission Control Protocol  전송제어규약)
- DB(oracle)
- Web(tomcat)

  + 종단 시스템 : host 라고 하며 네트워크상에서 더이상 연장되지 않은 기기 (PC, smart Phone, scanner, printer)
  
  + Router(라우터) : host 간의 상호 데이터를 교환할 수 있도록 해주는 장치
  
##### www : world wide web 

##### URL : Uniform Resource Locator 보통 IP주소로 연결하지만 우리가 보는 웹페이지 주소는 URL로 되어있다.

### TCP
1. 신사적인 Protocol.
2. 전화 -> 상대방 -> 연결 -> 통신
3. 동기화 send(보낸다) -> recv(받는다) 처리 순서가 일치해야 한다.
4. 데이터의 경계가 없다. Data의 용량의 한계가 없다.
5. 1 대 1 통신만 가능.
6. 채팅, String통신, Object를 보낼 수 있는 것이 있음

### UDP
1. 비 연결형 Protocol
2. 편지, 지상파방송
3. 데이터의 경계가 있다. Data 용량의 한계가 있다.
4. 1대 1 (unicast)
5. 1대 다수(broadcast)
6. 다수 대 다수 (multicast)

### 7 Layer
1계층 - physical Layer 물리계층 

2계층 - DataLink Layer 주소(IP)를 헤더에 첨부

3계층 - Network Layer  네트워크  IP -> address

4계층 - Transport Layer 네크워크 Port

5계층 - Session Layer  저장공간 세션을 동기화

6계층 - Presentation Layer 보안, 압축, 확장

7계층 - Application Layer 프로그램

### 용어
1. Pecket(묶음)
   * 제어 정보, 데이터들이 결합된 형태로 전송이 되는 실제의 데이터
	 	IP, Port, String, Dto(Object)
    
2. IP : Internet Protocol -> address v4 v6 컴퓨터마다의 주소 
	 	
	 	IPv4 : 0 ~ 255  xxx.xxx.xxx.xxx 
	 	IPv6 : 0 ~      xxx.xxx.xxx.xxx.xxx.xxx    
3. Port Number
- 한 IP에서 어떤 프로그램으로 송 수신 해야하는지 구분 짓는 번호

	IP 주소는 internet상에 존재하는 host(PC) 를 식별할 수 있으나
    
	최종 주체인 process(프로그램)을 식별하지 못하기 때문에 
    
	프로세스를 구별하기 위해서 지정된 수치(0 ~ 1024 : system)제외하고 수치를 지정한다.
4. Socket
- 유닉스(File)

	send recv 또는  write, read
  
	통신의 주체, 통신을 하기위한 Object
  
	IP, Port, TCP/UDP
  
5. DNS (Domain Name System) Server
- IP -> String  (아이티 주소를 문자열로 바꾸어줌)
		 xxx.xxx.xxx.xxx -> www.naver.com
  
### 통신 순서
```
	Server	                                       Client
		 	
1. Socket 버전 확인	                            1.  Socket 버전확인
2. Binding : IP, Port 설정		
3. Listen  
4. Accept	                <---- 통신 방향 --- 2.  Connect
		 
5. recv(받는다), read                 <------       3.  send(보내다), write
6. send	                          --------> 	    4.  recv(받는다)
```

### Server String
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Socket cs = null;       ★ 클라이언트 담당 소켓 생성
		
		try {
			ServerSocket svs = new ServerSocket(1234);   ★ 서버 소켓 생성
			
			System.out.println("접속 대기중");
			
			cs = svs.accept();
			
			System.out.println("IP:"+ cs.getInetAddress()+"port: "+cs.getPort());
			
      // recv
      
			BufferedReader reader = new BufferedReader(new InputStreamReader(cs.getInputStream()));  ★ 담당 소켓의 스트림으로부터 데이터 읽어들임
			
			String pecket = reader.readLine();  ★ 한줄씩 문자열 읽어옴
			
			System.out.println("client로 부터 받은 데이터: "+pecket );
			
      // send (서버의 보내기는 받은 내용을 다른 host로 보내는 기능을 한다.)
      
			PrintWriter pw = new PrintWriter (cs.getOutputStream());  ★ cs(클라이언트소켓)의 스트림에서 출력
			pw.println(pecket); 
			pw.flush();  ★ 반드시 flush를 해줘야 정상 작동 한다.
			
			cs.close();
			svs.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
### Client String
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

	
		Scanner sc = new Scanner(System.in);
		InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 1234); ★ 127.0.0.1 의 부분에 접속할 서버 IP 입력 , 포트넘버 입력
		Socket clientsc = new Socket();  ★ 클라이언트 소켓 생성
		
		try {
			clientsc.connect(sockAddr);  ★ connect을 통해 서버 접속
			
			InetAddress ia; // 확인용
			if((ia = clientsc.getInetAddress())!=null) {
				System.out.println("connect success" + ia);
			}
			else {
				System.out.println("connect fail");
			}
			
			System.out.print("전송할 텍스트 입력: ");   
			String pecket = sc.next();
			
      //send
      
			PrintWriter pw = new PrintWriter(clientsc.getOutputStream());   ★소켓을 통해 위에서 입력 받은 텍스트 출력
			pw.println(pecket);		//실제 송신 
			pw.flush();

			//recv
      
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientsc.getInputStream())); ★소켓을 통해 들어온 문자열 입력
			String str = reader.readLine(); ★ 한줄씩 인식
			System.out.println("Server로 부터 받은 데이터: "+str);
			
			clientsc.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
