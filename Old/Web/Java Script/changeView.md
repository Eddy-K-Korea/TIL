# 화면 바꾸기
## link를 이용하여 화면 옮기기
### 1.view 1
```html
<h1>여기는 view1.html 입니다</h1>
<a href="view2.html">view2로 이동</a>
```
### 2.view 2
```html
<h2>여기는 view2.html입니다.</h2>

<input type="button" value="이전으로" onclick="history.back()">

<input type="button" value="현재페이지 갱신" onclick="location.reload()">

<input type="button" value="진행" onclick="history.forward()">

<button type="button" onclick="gopage()">View3.html로 이동</button>

<script type="text/javascript">

function gopage() {
	location.href = "view3.html";
}
</script>
```
history.back()은 이전 페이지로 돌아가는 함수이다. location.reload()는 현재 페이지에서 새로고침 한다. history.forward()는 앞으로 가는 기능.
- 자바스크립트 <b>location.href</b>의 기능을 사용 하여 메소드를 통해 화면을 이동할 수 있다.
