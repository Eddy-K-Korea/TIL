# Json 파싱 예제1
```json
[
	{
		"title":"이제부터는 오를 곳 만 오른다",
		"author":"김학렬",
		"published":"페이지2북스"
	},
	{
		"title":"돈의  속성",
		"author":"김승호",
		"published":"스노우폭스북스"
	},
	{
		"title":"코로나 이후의 세계",
		"author":"제이슨 쎙커",
		"published":"미디어숲"
	},
	{
		"title":"존리의 부자되기 습관",
		"author":"존리",
		"published":"지식노마드"
	},
	{
		"title":"파타고니아, 파도가 칠때는 서핑을",
		"author":"이본 쉬나드",
		"published":"라이팅 하우스"
	}

]
```
임의로 작성한 json 파일
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id='books'>  <-- 작성할 테이블을 묶어주고 그안에 넣어주기위해 id값지정 -->
</div>

<script type="text/javascript">

let xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function () {
	if(this.readyState == 4 && this.status == 200){
		jsonFunc(this);
	}
}

xhttp.open("GET", 'book.json', true);
xhttp.send();


function jsonFunc(resp) {
	let jsonDoc = resp.responseText; // json은 텍스트로 받아야한다
	let arr = JSON.parse(jsonDoc);
	
	let out = "<table border='1'>";   // 초기 테이블 css 설정
	out += "<col width= '50'><col width= '200'><col width= '180'><col width= '150'>"
	out +="<tr>";
	out +="<th>번호</th>";
	
	for(k in arr[0]){
		out+= "<th>"+k+"</th>"; // key 값을 취득
	}
	out +="</tr>";
	
	// data 출력
	for (i = 0; i < arr.length; i++) {
		out +="<tr>";
		out += "<th>"+(i+1)+ "</th>";
------------------------------------------------------		
		/* out += "<td>"+arr[i].title + '</td>';  
		out += "<td>"+arr[i].author + '</td>';
		out += "<td>"+arr[i].published + '</td>'; */
------------------------------------------------------
		for(key in arr[i]){
			out += "<td>"+arr[i][key]+"</td>";	//위와 아래는 동일 (key값이 많을 경우 아래 경우가 간결)
		}
		out +="</tr>";
	}
	out+= "</table>";
	
	document.getElementById('books').innerHTML = out;
	
}
</script>
</body>
</html>
```
# 공공데이터 파싱 (버스데이터)
```html
{
"DESCRIPTION" : {"EIGHTEEN_RIDE_NUM":"18시승차총승객수","SEVENTEEN_RIDE_NUM":"17시승차총승객수","EIGHTEEN_ALIGHT_NUM":"18시하차총승객수","BUS_STA_NM":"역명",...}
"DATA" : [
{"eighteen_alight_num":329,"nineteen_alight_num":328,"bsst_ars_no":"03153","five_alight_num":13,".....},
{"eighteen_ali
...
```
방대한 데이터 를 파싱
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p id='demo'></p>


<script type="text/javascript">
let xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function () {
	if(this.readyState == 4 && this.status == 200){
		jsonFunc(this);
	}
}

xhttp.open("GET", "서울시 버스노선별 정류장별 시간대별 승하차 인원 정보.json", true);
xhttp.send();

function jsonFunc(resp) {
	let txt="";
	let jsonDoc = resp.responseText; 
	let arr = JSON.parse(jsonDoc);
	

	txt += arr.DATA[0].bus_sta_nm +"<br>";  // 역명 추출

	document.getElementById('demo').innerHTML = txt;
}

</script>
</body>
</html>
```
