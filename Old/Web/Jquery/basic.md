# Jquery

### Front-end
- Java Script  
```
client(front)
화면의 resource와 값을 결정, 취득하는 부분을 처리.
DOM(getElementById, nodelist)
vue, react
```
				 
- CSS(Cascading Style Sheet) 
```
디자인, front
```

- JQuery
```
Java Script 경량화, 간략화
Ajax(비동기 통신)
```
- JQuery-UI  
```
button, textField, slide 확장
```

## 기본 설정
 - Jquery를 실행 및 적용하기 위해서는 설치가 필요하다.
 ```
 <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
 ```
 해당 스크립트 소스를 <head> 부분에 추가하거나 <body>의 <script 부분에 위에 추가한다. 일반적으로 헤더에 추가하는것 보다 바디에 추가하는게 더 빠르다고 한다.
 
## 형태
```
$(태그 || id || class || name ).핸들러 함수(매개변수)
 
getter, setter 형식이 있다. 
 	
html() h3 tag -> innerHTML 	<b>world</b>
text()
val()	input text -> value
```

### 예제
```html
<p>여기가 p 태크 입니다.</p>

<p id="demo">p tag id demo</p>

<p class="cls">p tag class cls</p>

<h3 class="cls">h3 tag class cls</h3>
```
```jquery
$(document).ready( function(){
	//setter
	 $("p").html("jquery change name");// p tag에 html 영역 값을 변경(setter)해라 -> 웹의 모든 p 태그가 변경된다.
	 $("p").html("<b>hello p tag </b>");
//	 document.getElementsByTagName("p").innerHTML = "<b>hello p tag </b>" 위 코드와 동일
	 
	 $("p").text("jquery change name");
	 $("p").text("<b>jquery change name</b>"); // 위의 html과 다른점은 text는 볼드처리 되지 않고 <b>jquery change name</b> 그대로 출력
	 
	 
	 
	 //getter
	 let value = $("p").text(); // 매개 변수를 안넣어주면 getter가 된다. / 제일 마지막 p 태그 구문 value값으로 대입
	 alert(value);
	 //id -> # class -> .
	 $("#demo").text("change text");  //css 의 물러오는 형식과 비슷
	 
	 console.log($("#demo").html());
	 
	 $(".cls").html("<b>hello cls class</b>");
 });
```
 
