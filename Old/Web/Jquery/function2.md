# 함수 기능2
### 포커스 기능
```html
이름:<input type="text" name="firstname"><br><br>
이메일:<input type="text" name="email"><br><br>
```
두개의 텍스트 생성
```html
<script type="text/javascript">
$(function() {
	$('input').focus(function () {
		$(this).css('background-color','#00ff00'); // 포커스가 들어왔을때 색깔 입힘
	});
	 
	$('input').blur(function() {
		$(this).css('background-color','#ffffff'); // 포커스가 나갔을때 색깔 입힘
	});
});
</script>
```
### hide,show,toggle, 애니메이션, 추가 마우스 기능
```html
<div align="center">
	<div class="test" style="background-color: green; width: 50%; height: 100px; text-align: center;">
		I can do it	
	</div>
	<button type="button" id="hideBtn">hide</button>
	<button type="button" id="showBtn">show</button>
	<button type="button" id="toggleBtn">switch</button>
</div>
```
jquery
```html
<script type="text/javascript">
$(function() {
	$('#hideBtn').click(function() {
		$('.test').hide(1000); // 안에 매개 변수를 넣으면 밀리초 만큼 보여준다. 그리고 사라짐
	});
	$('#showBtn').click(function() { // 사라졌던 내용이 보여짐
		$('.test').show(2000);
	});
	$('#toggleBtn').click(function() {
		$('.test').toggle(1500);	// 누르면 사라지고 또 누르면 생긴다.
	});
	
	$('.test').mousedown(function() {	//마우스 클릭했을때
		alert('test mousedown');
	}); 
	$('.test').mouseup(function() {	//마우스 클릭을 땠을때
		alert('test mouseup');
	});
	$('.test').dblclick(function() {	// 더블클릭했을때 실행
		alert('test double click');
	});
	
	$('.test').mouseenter(function() { // test 안에 마우스가 있으면 메세지
		alert('test mouseenter');
	});
	$('.test').mouseleave(function() {	// test 밖으로 나가면 메세지
		alert('test mouseleave');
	});
});
</script>
```
### 불러온 값 넣기
```html
<body>
<h1>수영시합</h1>

<button type="button" id="woman">여성부</button>
<button type="button" id="man">남성부</button>
<br><br>

<table border="1" id="result">
<tr>
	<th>title</th><th>name</th><th>time</th>
</tr>
<tr>
	<th>우승</th><td></td><td></td>
</tr>
<tr>
	<th>2위</th><td></td><td></td>
</tr>
<tr>
	<th>3위</th><td></td><td></td>
</tr>
</table>
</body>
```
![image](https://user-images.githubusercontent.com/65350890/87527136-a4dd1d00-c6c6-11ea-8a1b-92ba214674f7.png)

```html
<script type="text/javascript">
let woman = [   // 원래 아래 2차원 배열은 파일로 보통 값을 불러온다.
	["",""],
	["김영희","01:05:11"],
	["최말숙","01:23:31"],
	["이말년","01:32:20"]
];

let man = [
	["",""],
	["홍길동","01:00:12"],
	["일지매","01:07:11"],
	["이순신","01:01:01"]
];

$(function() {
	$("#woman").click(function() {  //버튼을 클릭했을때 작동
		for(i=0;i<4;i++){ //행을 돌려주고
			for(j=0;j<2;j++){ //열를 돌려준다 
				$("tr:eq(" + i +") td:eq("+ j+ ")").html(woman[i][j]);
			}
		}
	});
	$("#man").click(function() {
		for(i=0;i<4;i++){
			for(j=0;j<2;j++){
				$("tr:eq(" + i +") td:eq("+ j+ ")").html(man[i][j]);
			}
		}
	});
});

</script>
```
### jquery 코드 추가 
#### $("tr:eq(2) td:eq(0)").html("데이터"); == tr 3번째, td 의 첫번째의 칸에 '데이터'를 넣게 된다
![image](https://user-images.githubusercontent.com/65350890/87527406-0dc49500-c6c7-11ea-81d8-154e41adbb24.png)

### 파일 불러오기
```html
<body>

<div id="news">로딩중...</div>

<script type="text/javascript">
$(document).ready(function() {
	$("#news").load("news.txt", function(txt, status) { // 파일을 읽어들여온다. 매개변수 두개가 있으며(내용,상태)순이다.
	//	alert(status);	sucess라는 문구가 뜨고 웹에 파일을 읽어 온다
		alert(txt);
	});
});

</script>

</body>
```
- 단 news.txt라는 파일이 webcontents에 있어야한다.

