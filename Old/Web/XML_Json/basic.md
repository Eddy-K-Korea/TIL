# XML

## XML : eXtensible Markup Language
- file로 요청한 데이터만 넘겨우고 입력받아 출력하게하는 언어


### 목적
- Data를 공유하기 위한 목적.
- setup을 위한 코드. 
- 예) 교통정보, 노선의 정보

### Parsing : 원하는 데이터를 취득. -> Parser(파싱을 하는 프로그램)
```
이름: 홍길동
나이: 24
주소:ㅣ 서울시
	
홍길동-24-서울시 이것도 파싱이다.
```  
- 사용자 테그를 사용하여 보통 아래 예시와 같이 파싱
```
	<선수들> root tag
		<선수>
			<이름>홍길동</이름>
			<나이>24</나이>
			<주소>서울시</주소>
		</선수>
		<선수>
			<이름>홍길동</이름>
			<나이>24</나이>
			<주소>서울시</주소>
		</선수>
		<선수>
			<이름>홍길동</이름>
			<나이>24</나이>
			<주소>서울시</주소>
		</선수>
	</선수들>
```  

### Bigdata에 사용
	1. data 수집
	2. 분석 (파이썬, R)
	3. 시각화(차트)   web, app 으로 시각화
  
- file = java, javascript(web에서 표현), jquery  함수가 전부 다르다.  (보통 자바 스크립트에서 많이 사용)(호환문제)

### 파일 읽기 (Test)
같은 폴더에 텍스트 파일 생성
```txt
안녕하세요
hello
```
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p id="demo">p tag</p>

<button type="button" onclick="loadXMLDoc()">내용변경</button> // 버튼을 눌러 데이터를 읽어 들임


<script type="text/javascript">

let xhttp = new XMLHttpRequest(); // XML file을 읽어오기 위해서 객체 생성

function loadXMLDoc() {
	
	xhttp.onreadystatechange = function () {
//		console.log(this.responseText);  // xhttp를 읽어드림 , 읽게 되면 (안녕하세요 hello)가 콘솔로 출력
//		console.log(this.readyState); // 먼저 호출 -> open -> send -> 다시 닫힘
		console.log(this.status);
	}
	xhttp.open("GET", "test.txt", true); // get , post방식 두가지 request 가 있다.  맨마지막 비동기를 true로
	console.log("xhttp.open");
	xhttp.send();
	console.log("xhttp.sen");	
}

</script>

</body>
</html>
```
### readyState (파일 읽어들임 순서)
```
  readyState : 진행상태를 볼 수 있음
	0  -> open() 메소드를 수행하기 전
	1  -> loading 중.... 상태
	2  -> loading 완료
	3  -> Server 처리중
	4  -> Server 처리 완료
```
### Status (파일 읽음 성공 여부)
```
	status
	200 = 성공
	403 = 접근 금지
	404 = 없음
	500 = 구문 에러
```  
