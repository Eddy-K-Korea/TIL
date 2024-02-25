package bit.com.spring.websock;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//import org.springframework.stereotype.Component;

//@Component 이 클래스를 자동 생성해서 동작을 하게한다 . 이방법이 있고 context.xml에 생성을 하게 하는 방법이 있다.

// web socket server
public class WebSocket extends TextWebSocketHandler{
	
	//클라이언트들을 저장하고 관리를 할 수 있도록 해주어야 한다.
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	public WebSocket() {
		System.out.println("EchoHandler 생성됨" + new Date());
	}

	
	//연결된 다음 실행되는 메소드 accept 부분에 해당 (오버라이드)
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//연결 확인(연결 후 실행)
		System.out.println("연결됨 " + session.getId() + " " + new Date());
		users.put(session.getId(), session); //session에 들어오는 값이 계속 달라진다
	}


	//연결이 종료 되었을때 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//연결 종료
		System.out.println("연결종료" + session.getId());
		users.remove(session.getId());	//연결 종류 된 후 저장되어있던 세션을 지워 준다.
	}


	//수신되는 부분을 체크하여 다시 보내주는역할
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//메시지 수신
		
		System.out.println("메세지 수신: "+ message.getPayload() + " "+ new Date());
		
		//송신 (send)
		for(WebSocketSession s: users.values()) {		//하나하나의 소켓 세션을 꺼낼 수 있다
			s.sendMessage(message);
		}
	}

	
	//예외가 발생되었을때 실행되는 메소드
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
	//예외 발생
		System.out.println(session.getId() + "Exception 발생" + new Date());
	}
}
