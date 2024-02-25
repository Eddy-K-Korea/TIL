# Css

## CSS : Cascading style Sheet - Dream Weaver

- 문자의 컬러, 종류, 형태지정
- 배경색 지정(bgcolor), 이미지 셋팅
- 테두리 설정
- 각종 Tag, id, class에 형태를 추가할 수 있다. (name은 안씀)

## css 적용 방식
1. inline방식
```html
<p style="font-size: 10pt">p tag 글자크기 10 포인트 입니다.</p>
```
2. internal 방식
```html
<style type="text/css">
/*
	태그명 또는 class 또는 id{
		property명: 값; (세미콜론으로 구분)
		property명: 값;
		property명: 값;
	}

*/
p{
	font-size: 14pt;
}

#p1{	/*  # == id 의미 */   
	color: red;
	font-size: 20pt;
}

.p2{ /* . == class */
	color: #0000ff;
	font-size: 24pt;
}

p.p2{	/*  p tag p2 class만 적용 */
	color: #ffff00;
}

</style>
```
3. External 방식
```html
<link rel = "stylesheet" type="text/css" href="./style.css">
```
링크를 통해 파일을 가지고 오는형식

## 글자크기 종류
```html
<style type="text/css">
.aaa{
	font-size: 1cm;
}
.bbb{
	font-size: 1mm;
}
.ccc{
	font-size: 1in;
}
.ddd{
	font-size: 1pt;
}
.eee{
	font-size: 1pc;
}
.fff{
	font-size: 24px;
}
.ggg{
	font-size: 1.2em;
}
.hhh{
	font-size: 200%;
}
</style>
```
아래 px, em, % 가 가장 많ㄹ이 쓰인다.

## 글자에 대한 추가 설정
```html
<style type="text/css">

#p1{
	text-align: center;     // 텍스트에 대한 정렬
}
.p2{
	word-spacing: 30px;   // 단어간의 간격을 설정
}
.p3{
	letter-spacing: 20px; // 글자간의 간격을 설정
}
.p4{
	line-height: 1.7em;   // 문장의 높이를 설정
}

</style>
```

## 자바스크립트를 활용한 css 적용
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="demo">Hello CSS World</div>
<br>

<button type="button" onclick="func()">적용</button>  //버튼을 누르면 css설정이 적용되도록함


<script type="text/javascript">
function func() {
	let obj = document.getElementById("demo");    //오브젝트를 obj에 대입
	
	obj.style.color = "white";  // 오브젝트 값에 style의 속성을 불러오고 적용할 수 있다.
	obj.style.backgroundColor = '#0000ff';
	obj.style.textAlign = "center";
	obj.style.borderStyle = "double";
	obj.style.borderColor = '#ff0000';
	obj.style.fontFamily = "MS PGothic";
	obj.style.fontStyle = "italic";
	obj.style.fontSize = "24pt";
}

</script>

</body>
</html>
```

## 배경에 이미지 넣기
```html
<style type="text/css">
body{ //바디 전체에 css 적용
  
	background-color: green;      //배경에 색 적용
	
	background-image: url('pic2.jpg');  //배경에 이미지 불러오기 (바둑으로 반복되어 적용된다.)
	background-repeat: no-repeat; //반복 되는것을 설정할 수 있음
	background-position: right top; // 이미지가 처음시작되는 위치를 적용

	background: #ff0000 url("pic2.jpg") no-repeat right top;    //위의 내용을 한번에 적을때는 이렇게 기재한다.
}
```
## 텍스트 및 링크 꾸미기
```html
<style type="text/css">
body{
	color: blue;  //텍스트의 색이 파랑색
	text-align: center; //가운데 정렬
}
h1{
	color: #ff0000; 
	text-decoration: underline;   // 텍스트의 아래 밑줄이 생김
}
p{
	color: #ffff00;
	
}
p.ex{
	color: rgb(0,255,0);
	text-align: right;
	text-decoration: line-through;  //텍스트 가운데 줄이 생김(보통 취소할때 쓰이는 텍스트)
}

a{
	text-decoration: none;  //링크된 텍스트에 밑줄을 없앤다.
}
a:visited {
	color: #0000ff; // 방문한 링크가 계속 같은 색이도록 설정
}

a:hover {
	color: #ffff00;   // 마우스를 위에 가져가면 설정한 색으로 글자색이 변함
	background-color: #000; // 마우스를 위에 가져가면 설정한 색으로 배경색이 변함
}
a:active {
	color: rgb(255,255,255);  // 클릭시 글자색이 설정한 색으로 변함
		
}
</style>
```

## list 관련 Css
```html
<style type="text/css">
ul.ucls{  // 순서가 상관없는 리스트
	 list-style-type: circle;       // 앞 순서 표시 (원)
	 list-style-type: square;       // 앞 순서 표시 (네모)
   list-style-image: url('icon.jpg'); //앞 순서 표시 (이미지)
	list-style: circle inside url('icon.jpg');
	
}
.ocls{  //순서가 있는 리스트
	list-style-type: lower-alpha; // 알파벳 순서로 표시
}

</style>
```

## 테이블 css설정 입히기
```html
<style type="text/css">
table{
	width: 100%;
	border-collapse: collapse;  //라인을 하나로 만들어줌
}
table,th,td{
	border: 1px solid black;  //테이블 테두리선 설정
}
th{
	height: 50px;
	text-align: left;
	background-color: green;
	color: white;
	border-width: 5px;
}
td{
	padding: 15px;  //td안에서 여백 설정
}
</style>
```
