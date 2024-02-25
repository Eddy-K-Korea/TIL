# 함수 (resource 기능)

```html
<body>

<p id="ptag">p tag</p>

<button type="button">button</button>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() { // 문서를 다 읽은 후에 실행
//	alert("ready");
});

$(function () {	//가장 먼저 실행
	/* $("#ptag").click(function() {	// onclick과 동일
		alert('p click');
	});
	$("button").click(function() {	// onclick과 동일
		alert('button click');
	}); */
	
	$("#ptag").on("click", function() { //id=ptag에 위의 click함수를 적용하는것과 같다.
		alert('jquery on click');
	})
	$("button").on("click", btnClick); 
});

function btnClick() {
	alert('btnClick');
}
</script>
```

### getter, setter
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>


입력:<input type="text" id="text" value=""><br>
<input type="button" id="btn" value="입력">

<script type="text/javascript">
$(document).ready(function() {
	$("#btn").click(function() {
		//getter
		let value = $("#text").val();
	//	alert(value);
		
		//setter
		$("#text").val('hello');
		
	});
});
</script>
</body>
</html>
```
입력값에 대해 getter와 setter를 이용할 수 있다.

## 접근법
```html
<p>p tag</p>

<div>
	<p>div in p tag1</p>
	
	<p>div in p tag2</p>
	
	<p class="cls">div in p tag class</p>
	
	<h3 class="cls">div in h3 tag class cls</h3>
</div>
```
아래 jquery
```html
$(document).ready(function() {
	$("p").click(function () {  // p 태그 클릭시 메세지 출력
		alert('p tag click');
	}); 
	$("div p").click(function() { // div 안의 p태그 클릭시 메세지 출력
		alert('div in p tag click');
	}); 
	$("div .cls").click(function() {   //div 안의 cls의 클래스명을 가진 태그 클릭시 메세지 출력
		alert('div in class cls click');
	});  
	$("div p.cls").click(function() {   // div 안의 p태그의 클래스 명이 cls인 태그 클릭시 메세지 출력
		alert('div in p tag class cls click');
	}); 
	$("div h3.cls").click(function() {    // div 안의 h3태그의 클래스 명이 cls인 태그 클릭시 메세지 출력
		alert('div in h3 tag class cls click');
	}); 
	$('.cls').click(function () {
	  $('.cls').css("background",'#ff0000'); // cls클래스명을 가진 태그 클릭시 배경색 변경
		$(this).css("background",'#ff0000');  // 여러개의 class를 구분할때 this로 구분할 수 있다.  object로 현재의 클릭된 태그에 css 적용
	}); 
	
	$("p").click(function () {
	//	$("p").hide(); // <->  show()
		$(this).hide(); // 클릭된 p 태그만 숨겨짐
	});
});
```
### 마우스 기능 함수
```html
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<body>

<table border="1">
<col width="50"><col width="200"><col width="100">
<tr>
	<th>번호</th><th>name</th><th>age</th>
</tr>
<tr>
	<th>1</th><td>홍길동</td><td>24</td>
</tr>
<tr>
	<th>2</th><td>성춘향</td><td>18</td>
</tr>
<tr>
	<th>3</th><td>일지매</td><td>22</td>
</tr>
</table>

<p>p tag</p>

<input type="button" id="btn" value="input">
<button type="button">버튼</button>
```
```jquery
<script type="text/javascript">
$(document).ready(function() {
	$('td').mouseover(function() {	//마우스 올린곳 색변화
		$(this).css('background','#00ff00');
	});

	$('td').mouseout(function() {	// 마우스를 벗어난 곳의 색 변화
		$(this).css('background','#ffffff');
  });
});
```
### 속성, 속성 값 변화
```html
<style type="text/css">
.mycss{
	color: #ffff00;
}
</style>
<body>
<p>p tag</p>

<input type="button" id="btn" value="input">
<button type="button">버튼</button>
</body>
```
```html
$('button').click(function() {
		
		// setter
		$('p').css('background','#0000ff');
		
		// getter
		let color = $('p').css('background'); // key(항목명) 값을 넣어주면 값을 받는다
	  //alert(color); // color의 값을 메세지 출력
		// attr
		$('p').attr("id", 'ptagid');  //현재 위에 p태그는 id 가 없으나 해당 코드를 통해 id생성 및 이름을 정해줄 수 있다.
	  $('p').attr("class", 'mycss');  //클래스속성과 클래스명 추가가 가능하다.
		
	});
	$('#btn').on("click", function() {
		$('p').removeClass("mycss");  //클래스를 삭제
		
		$('p').prop("class", "mycss"); // property 추가
	});
});
```
추가설명
```html
<p class="pcls">p tag</p>
```
jquery
```jquery
$(function () {
	let c = $('p').attr('class'); // getter  (class 이름을 얻어온다)
//	alert(c); // pcls가 메세지로 출력
	$('p').attr('class', 'newclass'); // setter (class 명을 newclass로 변경)
});
```
