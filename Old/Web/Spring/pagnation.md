# 페이징 (플러그인 사용)
- 이미 만들어져 있는 플러그인을 사용하여 페이징을 쉽게 할 수 있다.
- 깃허브 twbs-pagination-master를 알집을 다운 받는다 (url:https://github.com/josecebe/twbs-pagination)
- 안에 파일들 중 jquery.twbsPagination.min.js 를 끌어와서 사용하면 된다.
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">	<!-- 부트스트랩 css -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>				<!-- 부트스트랩 js -->
<script type="text/javascript" src="./jquery/jquery.twbsPagination.min.js"></script>
</head>
<body>

<!-- https://github.com/josecebe/twbs-pagination -->

<br><br>

<div class="container">
	<nav aria-label="Page navigation">
		<ul class = "pagination" id="pagination">
		</ul>
	</nav>
</div>


<script type="text/javascript">
let totalCount = 51; // 글의 총 수
let pageSize = 10;// 페이지 크기 [1] ~ [10] 아니면 [1] ~[5]
let nowPage = 1;//현재 페이지

let _totalPages = totalCount / pageSize;
if(totalCount % pageSize > 0){
	_totalPages++; // 나머지가 있으면 1페이지 추가
}

$("#pagination").twbsPagination({
	startPage: 1,
	totalPages: _totalPages,
	visiblePages: 10,
	first:'<span aria-hidden="true">«</span>',
	prev:"이전",
	next:"다음",
	last:'<span aria-hidden="true">»</span>',
	onPageClick: function(event,page){
		nowPage = page;
		alert("nowPage:" + nowPage);
	}
});

</script>
</body>
</html>
```

- 아래 처럼 만들어서 넣어놓고 링크로 불러온다<br>
![image](https://user-images.githubusercontent.com/65350890/92581448-ab54d300-f2ca-11ea-968f-907976ed1a94.png)

- 아래는 해당 자바스크립트의 함수에 대한 더 자세한 설명이다
```
$('#pagination').twbsPagination({
    totalPages: 35,	// 총 페이지 번호 수
    visiblePages: 7,	// 하단에서 한번에 보여지는 페이지 번호 수
    startPage : 1, // 시작시 표시되는 현재 페이지
    initiateStartPageClick: false,	// 플러그인이 시작시 페이지 버튼 클릭 여부 (default : true)
    first : "첫 페이지",	// 페이지네이션 버튼중 처음으로 돌아가는 버튼에 쓰여 있는 텍스트
    prev : "이전 페이지",	// 이전 페이지 버튼에 쓰여있는 텍스트
    next : "다음 페이지",	// 다음 페이지 버튼에 쓰여있는 텍스트
    last : "마지막 페이지",	// 페이지네이션 버튼중 마지막으로 가는 버튼에 쓰여있는 텍스트
    nextClass : "page-item next",	// 이전 페이지 CSS class
    prevClass : "page-item prev",	// 다음 페이지 CSS class
    lastClass : "page-item last",	// 마지막 페이지 CSS calss
    firstClass : "page-item first",	// 첫 페이지 CSS class
    pageClass : "page-item",	// 페이지 버튼의 CSS class
    activeClass : "active",	// 클릭된 페이지 버튼의 CSS class
    disabledClass : "disabled",	// 클릭 안된 페이지 버튼의 CSS class
    anchorClass : "page-link",	//버튼 안의 앵커에 대한 CSS class
    
    onPageClick: function (event, page) {
    	//클릭 이벤트
		console.log("클릭");
    }
});
```
