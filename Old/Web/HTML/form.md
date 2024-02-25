# Form
## resource(자원)
- textfield, button, textarea,checkbox,radiobutton

### attribute
- 목적: 접근하기 위한 요소(element)
>  - id : 현재 페이지에서 1개만 적용된다. Java Script
>  - class : 다중으로 사용이 가능하다. CSS, Java Script
>  - name : 값을 전달(Server에 전달하기 위한 목적). 1개만 사용  Java Script
### EX
```html
<p id="aaa" class="ddd" name="a1">hello</p>
<p id="bbb" class="ddd" name="b1">hello</p>   -- 만약 여기에 id를 "aaa"라고 해도 첫번째 만 접근이 가능하다 , class는 다중 접근 가능
<p id="ccc" class="ddd" name="c1">hello</p>
```

### NewFile.jsp로 가는 방법
1. 앵커로 링크를 걸어 접속
```html
<a href = "NewFile.jsp">NewFile로 이동</a>
```
2. Form을 이용하여 접근
```html
<form action="NewFile.jsp">
ID:<input type="text" name="id" value="" title="여기에 id 기입"> 
<br>
Password:<input type="password" name="pwd" value="">
<br>
<input type="submit" value="로그인"> 
<br>
<input type="reset" value="초기화">
</form>
```
- 여기서 <input type = "text">는 텍스트 필드, <input type = "submit"> 은 버튼, <input type="reset">초기화 버튼이다.
- 로그인 버튼을 누르게 되면 NewFile.jsp로 접근하게된다.

### Java와 연동 및 다양한 resource
웹페이지에서 받은 값을 java로 넘겨주기 위해 name과 jsp를 사용한다.
```html
<form action="NewFile1.jsp">
number:<input type="number" name="num" max="5" min="1">  -- 조건의 범위로 숫자를 입력가능한 텍스트 칸
<br>
date:<input type="date" name="date"> -- 달력에 날짜를 찍을 수 있게 해줌
<br>
color:<input type="color" name="color" value="#ff0000"> -- 색깔을 선택할 수 있게 해줌
<br> 
range:<input type="range" name="range" max="100" min="0"> -- 마치 볼륨내리고 올리는 막대 모양이 출력
<br>
search:<input type="search" name="search"> 일반 텍스트필드보다 약간 업그레이드 버전

<input type="submit" value="버튼">
</form>
```
```jsp
<%
String number = request.getParameter("num");
String date = request.getParameter("date");
String color = request.getParameter("color");
String range = request.getParameter("range");
String search = request.getParameter("search");
System.out.println(number+" "+date+" "+color+" "+range+" "+search);
%>
```
jsp 페이지에서 자바와 같이 변수값에 위에 input하였던 name값을 받는다.(request.getParameter()) 를 이용 하여 값을 받을 수 있다.
- 내가 html으로 생성된 웹 페이지에서 위에 값들을 입력하고 버튼을 누르게 되면 값들이 jsp로 넘어가고 콘솔창에 내가 입력한 값들을 확인 할 수 있다.


## 체크박스&라디어버튼
```html
<form action="NewFile1.jsp">
<input type="checkbox" name="hobby" value="패션">패션<br>
<input type="checkbox" name="hobby" value="음악">음악<br>
<input type="checkbox" name="hobby" value="게임">게임<br>
<br>
<input type="radio" name="car" value="벤츠" checked>벤츠<br>   -- checked 를 통해 젤 처음 값을 넣어준다.
<input type="radio" name="car" value="아우디">아우디<br>
<input type="radio" name="car" value="bmw">bmw<br>
<br>
<input type="submit" value="취미">
</form>
```
체크박스에서는 복수의 값을 받아야 하기 때문에 name이 동일하다. 라디어버튼도 값을 어차피 하나만 받기 때문에 name값이 동일하다.

```jsp
<%
String ho[] = request.getParameterValues("hobby");
if(ho != null && ho.length > 0 ){
	for(int i = 0; i < ho.length; i++){
		System.out.println(ho[i]);
	}
}

String car = request.getParameter("car");
System.out.println("car:"+car);
%>
```
체크박스의 경우 복수의 값이기 때문에 request.getParameterValues 으로 배열로 받고 반목분을 통해 값을 출력 할 수 있다.

## 선택 박스
```html
<form action="NewFile3.jsp">
<select name="fname" multiple="multiple">
	<option value="사과">Apple</option>
	<option value="배">Pear</option>
	<option value="바나나">Banana</option>
	<option value="포도">Grape</option>

</select>
<input type="submit" value="과일">
</form>
```
현재 위의 코드는 multiple로 되어있어 여러개를 선택할 수 있게 해놓았다. 이럴 경우 jsp의 배열로 받으면 된다.
- 한개일 경우 request.getParameter 로 값을 받으면된다.

## EX

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<div align="center">
<form action="NewFile4.jsp">
<h2>설문 조사</h2>
이름: <input type="text" name="name" value="">
<br><br>
[질문1]<br>
영어로 대화를 할 수 있습니까?<br>
<input type="radio" name="q1" value="예" checked>예
<input type="radio" name="q1" value="모르겠습니다">모르겠습니다
<input type="radio" name="q1" value="아니오">아니오
<br><br>
[질문2]<br>
영어외 관심있는 언어가 있으면 선택(복수선택 가능)<br>
<input type="checkbox" name = "q2" value="독일어">독일어
<input type="checkbox" name = "q2" value="중국어">중국어
<input type="checkbox" name = "q2" value="프랑스어">프랑스어
<br><br>
[질문3]<br>
영어수업에대한 의견이 있으면 기재해 주세요.<br>
<textarea rows="10" cols="50" name = q3 wrap="hard"></textarea>
<br><br>
[질문4]<br>
당신의 연령을 선택해 주십시오
<select name="age">
	<option value="10">10대</option>
	<option value="20">20대</option>
	<option value="30">30대</option>
	<option value="40">40대</option>
	<option value="50">50대</option>
</select><br><br>
<input type="submit" value="제출">
</form>
</div>
</body>
</html>
```
![설문조사](https://user-images.githubusercontent.com/65350890/86755385-92d2fd00-c07c-11ea-85ac-e810bfe3812c.png)
