# 값을 전송
## 총 3가지 방법의 데이터를 java로 넘겨주는 방식이 있다.
### 1. <a href=""
```html
<a href="NewFile.jsp?name=홍길동&age=24">NewFile.jsp로 이동</a>
```
지정된 값을 jsp로 넘겨줄 수 있다.
### 2. <form
```html
<form action="NewFile.jsp">
<br>
이름:<input type="text" name="name">
<br>
나이:<input type="text" name="age">
<br>
<input type="submit" value="이동">
</form>
<br>

```
앞서 form을 다루었을때처럼 동일한 방식으로 넘겨 줄 수 있다.(jsp 내부 내용은 맨 아래 있다)

### 3. javaScript
```html
이름:<input type="text" id="name">
<br>
나이:<input type="text" id="age">
<br>
<button type="button" onclick="btnclick()">이동</button>

<script type="text/javascript">
function btnclick() {
	let name = document.getElementById('name').value;
	let age = document.getElementById("age").value;
	
	location.href = "NewFile.jsp?name="+name+"&age="+age;
}

</script>
```
값을 받은 다음 메소드내에 location.href의 함수를 통해 jsp 파일로 넘겨준다. (jsp 내부 내용은 맨 아래 있다)

### jsp
```jsp
<%
String name = request.getParameter("name");
System.out.println("name: "+ name);

int age = Integer.parseInt(request.getParameter("age"));
System.out.println("age: "+age);
%>   
```
