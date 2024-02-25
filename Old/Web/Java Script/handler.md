# 핸들러
### 1. onload
```html
<p id="p1"></p>

<script type="text/javascript">
function checkMessage() {
	document.getElementById('p1').innerHTML = 'checkMessage() 호출';
	
}
</script>
```
첫 화면을 띄울때 ```'<body onload="checkMessage()">'```를 통해 함수가 한번 실행된다.

- onload의 다른예
```html
<body onload="viewScroll()">
<h1>본머스전 후반 투입' 손흥민, 5경기째 무득점…토트넘 무승부</h1>

<pre>
손흥민은 10일(한국시각) 영국 본머스 바이탈리티 스타디움에서 열린 2019-2020시즌 프리미어리그 34라운드 원정경기에 후반 시작과 함께 교체 투입됐지만 공격 포인트를 올리지 못했다. 
0-0 무승부를 기록한 토트넘은 승점 1점 추가에 그치며 리그 9위로 한 단계 내려앉았다.조제 모리뉴 토트넘 감독은 손흥민을 대신해 최전방에 해리 케인, 에릭 라멜라, 스티븐 베르흐베인을 포진시켰다. 
그러나 전반 내내 공격이 생각대로 풀리지 않자 베르흐베인을 빼고 후반전 손흥민을 투입해 반전을 노렸다. 
손흥민은 플레이메이커 같은 역할로 패스에 집중했지만, 위협적인 장면을 연출하진 못했다. 본머스의 탄탄한 수비진을 뚫어내는 데 실패했다.
</pre>
<script type="text/javascript">
let posy = 0;
function viewScroll() {
	window.scroll(0,posy);
	posy = posy +2; // posy에 2씩 더하여준다
	if(posy == 300)posy = 0; // 300이 되면 0으로 리셋
	
	setTimeout("viewScroll()", 100);
}
</script>
</body>
```
스크롤이 자동으로 내려가 진다. 첫 화면을 띄울대 viewScroll()함수 호출.

### 2.onblur (포커스 이동)
```html
나이:<input type="text" onblur="isRegNum()" size="10" maxlength="2">세<br>

<script type="text/javascript">
function isRegNum() {
	alert('onblur 실행');
}

</script>
```
텍스트 밖의 영역을 클릭하게 되면 alert가 실행된다.

### 3.onchange 
```html
<select onchange="changeVal()">
	<option>사과</option>
	<option>배</option>
	<option>바나나</option>
	
</select>
<script type="text/javascript">

function changeVal() {
	alert('changeVal() 호출');
}

</script>
```
선택한 옵션이 바뀔때 마다 alert가 출력 보통 select에 많이 쓰임

### 4.숫자가 아닌 문자 입력시 알림
```html
우편번호 입력란
T:<input type="text" size="5" maxlength="3" onchange="isPostNum(this)">
-
<input type="text" size="5" maxlength="3" onchange="isPostNum(this)">
<br>

<script type="text/javascript">
function isPostNum(obj) {
	let str = obj.value;  // isPostNum(this)함수가  this로 받았기 때문에 오브젝트로 바로 받을 수 있다.
	if(str.match(/[^0~9]/g)){	// 숫자가 아닌 문자가 포함되어 있는 경우 true
		alert('숫자가 아닌 문자가 포함되어 있습니다.');
	}  
}
```
text에 0부터9의 숫자 이외의 입력값이 있을때 .match(/[^0~9]/g 의 함수를 써준다. true,false값을 리턴해준다.

### 5. onkeydown, onkeypress
```html
<input type="text" name="title">

<script type="text/javascript">
/*
document.getElementsByName("title")[0].onkeydown = function (event) {
	console.log("keydown keycode: "+event.keyCode);
}*/
document.getElementsByName("title")[0].onkeypress = function (event) {
	console.log("keydown keycode: "+event.keyCode);
}	
</script>
```
해당 핸들러를 사용하게되면 키보드로 누르는 값의 아스키코드값을 리턴해준다.down과 press의 차이는 down은 대.소문자 구분을 하지 않고 press는 대.소문자를 구분한다.

### 6. onMouseDown, onMouseUp, onMouseOver, onMouseOut
```html
<input type="image" src="image/san0.gif" onmousedown="mouseDown(this)" 
		onmouseup="mouseUp(this)" 
		onmouseover="mouseOver(this)"
		onmouseout="mouseOut(this)">


<script type="text/javascript">
function mouseDown(obj) {   //마우스 클릭시 변경
	obj.src = "images/san1.gif";
}
function mouseUp(obj) {     // 마우스 클릭을 떼면 변경
	obj.src = "images/san0.gif";
}
function mouseOver(obj) {   // 마우스를 올려다 놓으면 변경
	obj.src = "images/san2.gif";
}
function mouseOut(obj) {    // 마우스를 다른곳에 두면 변경
	obj.src = "images/san0.gif";
}


★ 예제 2
<a href="#" onmouseover="document.box.src='images/surprise.gif'" onmouseout="document.box.src='images/box.gif'">
	<img alt="" src="images/box.gif" name = "box"> // 링크 이동으로도 사용가능 마우스롤 올려놓으면 다른 그림으로 변경
</a>
```
