# 값을 가져오는 load 함수

### 비동기 데이터 송수신

```jsp
<p id="demo"></p>

<br>
<button type="button">click</button>

<script type="text/javascript">
$(function() {
	$("button").click(function() {
		$("#demo").load("data.html"); //html의 내용을 가져온다
		$("#demo").load("data.html #data1");  //html의 id가 data1의 값을 가져온다.
		$("#demo").load("data.jsp", "t1=abc&t2=가나다"); // 데이터를 넘겨주고 전체 데이터를 다시 가지고옴
		$("#demo").load("data.jsp", { t1: "ABC", t2:"라마바사"}); //Json형태로 key값에 해당하는 value값을 넘겨주고 data.jsp전체 를 불러온다.
		$("#demo").load(
					"data.jsp",	//행선지
					{ t1: "DEF", t2:"아자차"},//행선지에 가져갈 값
					function(data, status, xhr){
					//	alert("success"); // 행선지에 데이터 값을 전달하고 돌아와서 함수 실행
					//	alert(data);
					
						$("#demo").append('data = '+data+"<br>"); //안의 데이터를 전부 추가한다.
						$("#demo").append("status="+status+"<br>"); // 통신의 상태를 추가해준다
						$("#demo").append("xhr="+xhr+"<br>"); // 어떤 객체가 넘어왔는지 추가(출력) 해준다.
					}
		);
		
	});
});

</script>
```

### data.html
```html
<body>

<p id="data1">사과</p>
<h3 id="data2">바나나</h3>

</body>
```

### data.jsp
```jsp
<body>

t1=<%=request.getParameter("t1") %>

t2=<%=request.getParameter("t2") %>

</body>
```
