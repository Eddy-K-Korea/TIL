# on 함수 이용

```html
<body>

<ul id="list">
	<li>커피</li>
	<li>홍차</li>
	<li>우유</li>
	<li>쥬스</li>
	<li>녹차</li>
</ul>

<p id="len">list의 element 수</p>
선택한 항목:<input type="text" id="choice">
<br><br>

추가항목:<input type="text" id="col"><br>

<button type="button" id="btn">추가</button>
```
```html
<script type="text/javascript">
$(function() {
	//click한 항목
	$('ul#list').click(function() {     //리스트를 어느것을 누르든 메세지 출력
		alert('click');
	}); 
	$('ul#list li').click(function() {    //리스트의 항목 어느것을 누르는지 그 값을 텍스트에 출력
	  $('#choice').val($(this).text());   
	}); 
```  
```html
   $('ul#list').children().click(function() {   // 리스트의 자식(항목)의 값을 텍스트에 넘겨준다
			$('#choice').val($(this).text());
		}); 
	 $('ul#list li').mouseover(function() {   //항목에 마우스롤 올리면 색변화
		$(this).css('background-color','#f0f000');
	});
	$('ul#list li').mouseout(function() {   //항목에 마우스가 벗어나면 색변화
		$(this).css('background-color','white');
	}); 
	
	$(document).on("click","#list li",function(){   //만약 document를 하지 않고 항목에 설정하지 않으면 새롭게 추가되느 항목은 텍스트에 출력안된다.
		$("#choice").val($(this).text());
	});
	$("#btn").click(function() {
		let len = $("ul#list").children().length; 
		$("#len").text(len +"개의 요소가 있습니다.");
		
		//추가항목
		let appendObj = $("<li></li>").text($('#col').val());
		$("#list").append(appendObj);
		
	});
```


