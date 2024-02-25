# 라면 타이머 프로그래밍
## time 제어

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="" name="frm">
	<div align="center">
	<strong>라면 타이머</strong>
	<br><br>
	<select id="selid" name="myChoice">
		<option value="180">원하는 시간을 선택해 주십시오(기본 시간 3분)</option>
		<option value="300">5분</option>
		<option value="180">3분</option>
		<option value="150">2분30초</option>
		<option value="120">2분</option>
		<option value="60">1분</option>
		<option value="30">30초</option>
	</select>
	
	<input type="button" onclick="noodle()" value="Start">
	<br><br>
	
	<span id="countdown">Time selected</span>	
	<br><br>
	
	<button type="button" onclick="window.close()">close</button>

	</div>
</form>

<script type="text/javascript">
let count = 0;
let time = 0;
let choice = 0;

function noodle() {
	clearInterval(time); //timer initialize 시간 초기화
	
	// 시간 선택 취득
	/* let value = document.getElementById("selid").value;
//	alert(value);
	
	count = value; */
	
  
  // form 을 이용
	choice = document.frm.myChoice.selectedIndex;  // 0 ~ n-1
	
	count = parseInt(document.frm.myChoice.options[choice].value);
	
	time = setInterval('myTimer()', 1000);

}
function myTimer() {
	count = count - 1;
	
	document.getElementById("countdown").innerHTML = "완료까지 <b>"+count + "</b>초 남았습니다.";
	
	if(count == 0){
		clearInterval(time);
		alert('시간이 완료 되었습니다.');
	}
}

</script>

</body>
</html>
```
