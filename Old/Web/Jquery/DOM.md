# Document Object Model

## html(),text(),css(),attr(),prop()

### setter
```html
css("background-color","#ff0000") : red 색을 입히는경우
			
css({ "background-color":"#ff0000",     2가지 특성
		"border":"3px solid" 		    }); 
```           
### getter
```			
let v = $("#id").css("background-color");
			
setter -> $(id, name, class, tag).val(값);   <- input 경우에 해당
					  $(id, name, class, tag).css("특성명","값")  <- 일반
					  
getter -> $(id, name, class, tag).val()
					  $(id, name, class, tag).css("특성명")
```
## 일반 테그

```html
<p id="demo">오바마·머스크 털린 트위터 해킹 사태 ‘일파만파’···해커 <b>현상금 12억</b> 걸렸다</p>
<button type="button" id="btnText">show text</button>
<button type="button" id="btnHtml">show html</button>


<script type="text/javascript">
$(document).ready(function() {
	$("#btnText").click(function() {
		let text = $("#demo").text();	/* 가져올때 텍스트만 가져옴 */
		alert(text);
	});
	$("#btnHtml").on("click", function() {
		let html = $("#demo").html(); 	/* 텍스트 + <b> 의 html 문법도 같이 가져옴 */
		alert(html);
	});
});
```

## 텍스트 값 가져오기
```html
<input type="text" id="text" placeholder="입력주제">
<button type="button" id="btnInput">show value</button>


<script type="text/javascript">
$(function() {
	$('#btnInput').click(function() {
		let text = $("#text").val();
		alert(text);
	});
});
</script>
```

## 링크 가져오기
```html
<p>
	<a href="http://www.naver.com" id="web">Naver Link</a>
</p>
<button type="button" id="btnAttr">Attribute(속성)</button>

<script type="text/javascript">
$(function() {
	$('#btnAttr').click(function() {
		//getter
		let val = $("#web").attr("href");
		alert(val);
		
		//setter
		$("#web").attr("href", "http://www.daum.net");    // setter로 속성값을 수정할 수 있다.
		$("#web").text("다음 홈페이지");  // 클릭시 Naver -> 다음 홈페이지로 변경
	});
	
});
</script>
```
