# Server Object
### String 이외의 Object로 데이터를 받을 수 있다. 
### 이렇게 되면 문자열 이외의 다양한 정보를 넘기고 받을 수 있게 된다.

### * Dto
```java
import java.io.Serializable;

★ Serialize(직렬화) 전송 -> 순서를 정해야 한다. 
★ Network, web 에서 송수신을 할때 반드시 적어 주어야 한다.
★ dto는 서버에도 있어야 하며 클라이언트도 있어야한다. (둘다 직렬화가 되어있어야 한다.)

public class MemberDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7159726773562029167L;
	private int number;
	private String name;
	
	public MemberDto() {
	}

	public MemberDto(int number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MemberDto [number=" + number + ", name=" + name + "]";
	}
}
```




### * Server 
```java
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dto.MemberDto;

public class Main {

	public static void main(String[] args) throws Exception{

		ServerSocket serSocket = new ServerSocket(9000);
		
		System.out.println("대기중...");
		Socket socket = serSocket.accept();
		
		System.out.println("client IP: "+socket.getInetAddress()  ★들어온 소켓의 IP 및 포트 넘버출력
									+" Port: "+socket.getPort());
		
		//recv 
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); ★ ObjectInputSrteam 인스턴스 생성 소켓으로 받은 내용 읽어들임
		//MemberDto dto = (MemberDto)ois.readObject(); ★ 멤버dto의 클래스 형 변환 
		Object obj = ois.readObject();        ★ 위의 방법 대로 형변환을 해주어서 받을 수도 있고 Object로 바로 받을 수 있다.
		
		//System.out.println("client로 부터 Dto: "+dto.toString());
		
		//send
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); 
		//oos.writeObject(dto);  ★ 변현환 된 dto는 다시 Object로 담길수 있기 때문에 바로 전송 가능
		oos.writeObject(obj);    ★ 만약 object로 받으면 그대로 넣어서 송신 가능하다.
		
		oos.flush();
		serSocket.close();
	}
}
```
### * Client 
```java
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import dto.MemberDto;

public class Main {

	public static void main(String[] args)throws Exception {

		InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 9000); ★ 수신받을 서버의 IP와 포트넘버 기입
		Socket socket = new Socket();
		
		socket.connect(sockAddr);     ★ 접속 까지는 방식이 String과 같다.
		
		InetAddress inetAddr; // 접속 확인용
		if((inetAddr = socket.getInetAddress()) != null){
			System.out.println("Server connect success: "+ inetAddr);
			
		}else {
			System.out.println("Server connect fail");
		}
		
		MemberDto sendDto = new MemberDto(111,"홍길동");     ★ 송신할 인스턴스를 생성한다.
		
		// send 부터
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendDto);
		oos.flush();
		
		//recv
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		MemberDto recvDto = (MemberDto)ois.readObject();
		
		System.out.println("Server로부터 받은 Dto: "+recvDto.toString());
		socket.close();
	}
}
```
