# Append

```html
<p>선 창립자는 “이 문제를 즉시 해결하고 (트위터) 계정을 정상으로 되돌리기 위해 트위터와 긴밀히 협력하고 있다”</p>

<ol>
	<li>사과</li>
	<li>배</li>
	<li>바나나</li>
</ol>
<br>

<select id="food">
	<option value="햄버거">햄버거</option>
	<option value="피자">피자</option>
	<option value="치킨">치킨</option>
</select>

<button type="button" id="append">추가(뒤)</button>
<button type="button" id="prepend">추가(앞)</button>

<script type="text/javascript">
$(function() {
	$("#append").click(function() {
		//p tag 추가
	//	$("p").append("<br>append 추가 요소 (element)입니다");		/* p tag 뒤에 추가 */
	//	$("ol").append("<li>포도</li>");						/* 리스트 뒤에 추가 */
		$("#food").append("<option value='스테이크'>스테이크</option>");
	});
	$("#prepend").click(function() {
	//	$('p').prepend("<br>prepend 추가 요소 (element)입니다")			/* p tag 앞에 추가 */
	//	$("ol").prepend("<li>오렌지</li>");						/* 리스트 앞에 추가 */
		$("#food").prepend("<option value='떡볶이'>떡볶이</option>");
	});
});
</script>
```
append 와 prepend를 통해 추가적인 내용을 문자열로 추가할 수 있다. 문자열 내 필요한 Tag를 넣으면 적용하여 입력된다.



# HTML, JS, JQuery 별 추가 방법
```html
<button type="button">Tag Append</button>

<script type="text/javascript">
$(function() {
	$("button").click(function() {
		//html(text) 추가
		let txt1 = "<p id='demo'>html p tag append</p>";
		$("body").append(txt1);
		//java Script 추가
		let txt2 = document.createElement("h3");
		txt2.innerHTML = "JS h3 tag append";
		$("body").append(txt2);
		//JQuery 추가
		let txt3 = $("<p></p>").text('JQuery p tag append');   /*$("<p>") 도 실행된다 */
		$("body").append(txt3);
	});
	
});
```
버튼을 누르면 p 태그가 나오도록 설정

## 예제
- 답글달기 
```html
<body>

<h3>Detail View</h3>
<textarea rows="2" cols="20">기본글...</textarea>

<br><br>
<h4><답글></h4>
<p id="a"></p>
<button type="button" id="answer">답글 입력</button>

<br>

<div id="answerForm">
<input type="text" id="txt" placeholder="답글 입력">
 
<button type="button" id="send">입력</button

</div>

<script type="text/javascript">
  $(function() {
	$("#answerForm").hide();
	
	$("#answer").click(function() {
		$("#answerForm").show(300);
	});
	$("#send").click(function() {
		let val = $("#txt").val();
		//alert(val);
		$("#a").append(val+"<br>");
		$("#txt").val("");
		$("#answerForm").hide();
	});
});

</script>
</body>
```
![image](https://user-images.githubusercontent.com/65350890/87642718-d87e7c80-c784-11ea-8bee-b9545a987b8e.png)
![image](https://user-images.githubusercontent.com/65350890/87642756-e46a3e80-c784-11ea-93a7-a79a0a0a157d.png)
```
- 다른 방식
```html
<h3>Detail View</h3>
<textarea rows="2" cols="20">기본글...</textarea>

<br><br>
<button type="button" id="answer">답글 입력</button>

<br>

<div id="answerForm">
<!-- 
	input text
	button -> input text -> alert으로 출력
 -->
</div>

<script type="text/javascript">
 
$(function() {
	 $("#answer").click(function() {
		let txtf = "<br>답글:<input type='text' id='answerText'>";
		$("#answerForm").append(txtf);  // 텍스트 상자 추가
		
		let btn = "<br><br><button type='button' id='answerBtn'>답글작성완료</button>";
		$("#answerForm").append(btn); // 버튼 추가
	});
	$(document).on("click", "#answerBtn",function(){		//문서를 다시 읽어서 해당 아이디에 함수를 적용해라 라는 의미
		alert($("#answerText").val());  //문서를 다시 읽어들여서 텍스트 상자의 값을 얻어와 메세지 출력
	});
});

</script>
</body>
```
