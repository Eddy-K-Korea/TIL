# 오픈 채팅 (웹 소켓 이용)

### Web socket server를 이용한 채팅만들기

### 1. web socket을 사용하기 위해 라이브러리 다운 (pom.xml)
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-websocket</artifactId>
    <version>5.2.7.RELEASE</version>
</dependency>


<dependency>
    <groupId>org.glassfish</groupId>
    <artifactId>javax.json</artifactId>
    <version>1.0.4</version>
</dependency>
```  
### 2. web socket의 메소드를 이용하기 위해 handler를 생성해야한다. 그래서 java 패키지와 클래스를 생성하고 상속과 오버라이드를 한다
```java
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
```
### 3. 해당 패키지(핸들러)를 읽기 위해 spring 폴더에 설정 xml을 구축한다 (websocket-context.xml)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:websocket="http://www.springframework.org/schema/websocket"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/websocket http://www.springframework.org/schema/websocket/spring-websocket-4.3.xsd">

	<websocket:handlers>
		<websocket:mapping handler="myHandler" path="/echo.do"/>	<!-- myhandler 와 echo.do는 사용자 지정 -->
		<websocket:handshake-interceptors>
			<bean class="org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor"/>		<!-- 지정 (이미 설정되어있는 클래스) -->
		</websocket:handshake-interceptors>
	</websocket:handlers>

	<bean id="myHandler" class="bit.com.spring.websock.WebSocket"/>	<!-- 웹 서버 소켓을 지정 -->
</beans>
```
### 4. 위의 설정을 웹시작때 읽게 하기위해 web.xml에 설정을 해준다.
```xml
<init-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>
  			/WEB-INF/spring/servlet-context.xml <!-- 우리가 사용할 view의 확장자와 경로를 셋팅해주는 것 -->
  			/WEB-INF/spring/aop-context.xml <!-- aop-context 읽게 했음 -->
  			/WEB-INF/spring/websocket-context.xml <!-- 웹 소켓 서버를 생성하고 경로를 지정한 설정 xml을 읽게함 -->
  		</param-value>
  	</init-param>
  	
  	<load-on-startup>1</load-on-startup> <!-- 이것부터 읽어라 라는 표시  -->
  	<async-supported>true</async-supported>	<!-- 웹소켓을 만들 때 추가해주어야 하는 설정 -->
    
    ... (밑에 코드 더있음)
```   
### 5. 채팅을 할 수 있는 뷰를 만들어준다. 채팅 페이지로 이동을 위해 컨트럴러를 만들어준다.

- 1. 컨트럴러
```java
package bit.com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 AOP와 다른 Package에 사용 권장
 */
@Controller
public class bWebSocket {

	@RequestMapping(value = "chatting.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String chatting(Model model) {
		model.addAttribute("doc_title", "채팅");
		return "chatting.tiles";
	}
}
```
- 2. 뷰
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table class="list_table" style="width: 85%">
<colgroup>
	<col style="width: 200px"><col style="width: 500px">
</colgroup>

<tr>
	<th>채팅명</th>
	<td style="text-align: left;">
		<input type="text" id="name">
		<input type="button" id="enterBtn" value="입장" onclick="connect()">
		<input type="button" id="exitBtn" value="나가기" onclick="disconnect()"> 
	</td>
</tr>

<tr>
	<th>아이디</th>
	<td style="text-align: left;">
		<input type="text" id="id" value="${login.id }">
	</td>
</tr>

<tr>
	<td colspan="2">
		<textarea rows="10" cols="70" id="charMessageArea"></textarea>
	</td>
</tr>

<tr>
	<td colspan="2">
		<input type="text" id="message" size="40">
		<input type="button" id="sendBtn" value="전송" onclick="send()">
	</td>
</tr>

</table>

<script type="text/javascript">
var wsocket;

	//Web Socket 생성 ----------------
	// 서버에 접속 
//	if($("#name").val()=="bbb"){
//		wsocket = new WebSocket("ws://localhost:8090/springSampleAll/echo.do"); //아까 xml에 생성하였던 웹 소켓 서버의 경로를 이렇게 적어준다.
//	}
//	else{
		wsocket = new WebSocket("ws://현재 아이피 주소:8090/springSampleAll/echo.do");	//외부에서 접근할 경우
//	}

	//alert("wsocket:" + wsocket);
	wsocket.onopen = onOpen; //함수명을 정해준다. 초기화 (onOpen은 함수다)
	wsocket.onmessage = onMessage; 
	wsocket.close = onClose;
}

function connect(){
	if(wsocket != undefined && wsocket.readyState != WebSocket.CLOSED){
	//이미 소켓이 생성된 경우. = 서버에 접속한 경우
		alert("이미 입장하셨습니다.");
		return;
}
  
function disconnect(){

	wsocket.close();
	loaction.href='chatting.do';
}

function onOpen(evt){	//연결 되었을때  전송
	appendMessage("연결되었습니다");
}
function onMessage(evt){	//실제 메세지가 수신 되는 부분(recive)
	let data = evt.data;
	if(data.substring(0,4)=="msg:"){ //msg:안녕하세요  -> 이렇게 전체가 전송
		appendMessage(data.substring(4));
	}
}
function onClose(evt){	//끊겼을때 전송
	appendMessage("연결이 끊겼습니다");
}

function send(){	//메세지 송신(send)
	let id = $("#name").val();
	let msg = $("#message").val();

	//실제 전송되는 부분
	wsocket.send("msg:"+ id + ":"+ msg);
	$("#message").val("");
}

function appendMessage(msg){ //메세지가 수신되면 텍스트에리어에 올려주는 함수
	//메세지 추가하고 개행
	$("#charMessageArea").append(msg + "\n");

	//스크롤을 위로 올려준다.
	const top = $("#charMessageArea").prop("scrollHeight");
	$("#charMessageArea").scrollTop(top);
}
</script>
```
- 세션 값을 통해 서로 통신을 하게 되고 서버는 받은 값을 그대로 다른 세션에게 전달하게 된다. 
```
세션 1 ---> 서버 ----> 세션 2
                 ----> 세션 3
                  ----> 세션 4
```                  
# 지정된 클라이언트에게 메세지 보내기(실시간)(웹소켓 사용)
### 설정은 위와 동일하다
### 1. 백엔드
```java

@Override
public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	//연결 확인(연결 후 실행)
	System.out.println("연결됨 " + session.getId() + " " + new Date());
	String senderId = getid(session);	//아래 세션으로 저장된 멤버dto의 id를 가져옴
	System.out.println(senderId);
	users.put(senderId, session); //session에 들어오는 값이 계속 달라진다
}

private String getid(WebSocketSession session) {
	Map<String, Object> httpSession = session.getAttributes();	// 세션을 가져온다
	MemberDto loginUser = (MemberDto)httpSession.get("login");	// 세션에 저장되어있는 객체를 가져온다

	if(loginUser == null) {
		return session.getId();
	} else {
		return loginUser.getId();	// id를 return 해준다
	}
}

@Override
protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	//메시지 수신

	String msg = message.getPayload();
	System.out.println(msg);
	String str[] = msg.split(":");	// 뷰에서 보내준 id값을 추출하기 위해 스플릿한다
	System.out.println(str[1]);
	String id = str[1];

	//송신 (send)
	WebSocketSession s = users.get(id); // map에 저장되어있는 아이디를 불러온다 (value에 지정 session 저장되어있다)
	s.sendMessage(message);
}

```
### 2. 사용자 뷰
```html
<input type="text" id="id" value="${login.id }">
<input type="button" id="enterBtn" value="입장" onclick="connect()">
<input type="button" id="exitBtn" value="나가기" onclick="disconnect()">
<button type="button" id="btn" onclick="send('aaa')">보내기aaa</button>
<button type="button" id="btn" onclick="send('zzz')">보내기zzz</button>


<script type="text/javascript">
var wsocket;

function connect(){
	if(wsocket != undefined && wsocket.readyState != WebSocket.CLOSED){
		//이미 소켓이 생성된 경우. = 서버에 접속한 경우
			alert("이미 입장하셨습니다.");
			return;
	}

	wsocket = new WebSocket("ws://192.168.219.105:8090/springSample/echo.do");	//서버의 url주소와 echo.do 경로를 적어준다
	wsocket.onopen = onOpen;
	wsocket.onmessage = onMessage;
	wsocket.close = onClose;

}

function disconnect(){
	wsocket.close();
}

function onOpen(evt){	//연결 되었을때  전송
	console.log("연결 성공");
}

function onMessage(evt){
	let data = evt.data;
	alert(data);
}

function onClose(evt){	//끊겼을때 전송
	console.log("연결 끊김");
}

function send(eciver){
	let id = reciver;
	wsocket.send("id:"+id+":like");
}

</script>
```
