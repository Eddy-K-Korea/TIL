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

function connect(){
	if(wsocket != undefined && wsocket.readyState != WebSocket.CLOSED){
	//이미 소켓이 생성된 경우. = 서버에 접속한 경우
		alert("이미 입장하셨습니다.");
		return;
	}

	//Web Socket 생성 ----------------
	// 서버에 접속 
//	if($("#name").val()=="bbb"){
//		wsocket = new WebSocket("ws://localhost:8090/springSampleAll/echo.do"); //아까 xml에 생성하였던 웹 소켓 서버의 경로를 이렇게 적어준다.
//	}
//	else{
		wsocket = new WebSocket("ws://192.168.219.103:8090/springSampleAll/echo.do");	//외부에서 접근할 경우
//	}

	//alert("wsocket:" + wsocket);
	wsocket.onopen = onOpen; //함수명을 정해준다. 초기화 (onOpen은 함수다)
	wsocket.onmessage = onMessage; 
	wsocket.close = onClose;
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








