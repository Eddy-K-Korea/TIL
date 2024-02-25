# DOM

### DOM : Document Object Model 각 Tag를 접근하기 위한 Object 그 Object를 접근 하기 위한 함수

자식 노드의 값을 불러 올 수 있다.
```html
<!-- childNodes -->
	
	<h3 id="intro">h3 tag id intro</h3>
	
	<p id='demo'>p tag id demo</p>
	
	<button type="button" onclick="func()">버튼</button>
	
	<script type="text/javascript">
	function func() {
//		let text = document.getElementById('intro').childNodes[0].nodeValue;
		let text = document.getElementById('demo').childNodes[0].nodeValue;     
		alert(text);      
	}
```  
Select의 옵션들을 배열로 carname에 넣고 인덱스로 불러올 수 있다.
```html
<select id='car'>
		<option>Benz</option>
		<option>BMW</option>
		<option>Volvo</option>
	</select>
	
	<button type="button" onclick="myfunc()">선택</button>
	
	<script type="text/javascript">
	function myfunc() {
		let carname = document.getElementById('car').childNodes;
		alert(carname[3].text);	//bmw
	}
	
	</script>
```
### createElement, appendChild, removeChild
```html
<!-- appendChild(뒤에 추가), insertChild(앞에 추가) -->
	
	<div id="div1">
		<p id="p1">첫번째 p태그</p>
		<p id="p2">두번째 p태그</p>
	</div>

	<button type="button" onclick="appendfunc()">뒤에 추가</button>
	<button type="button" onclick="insertfunc()">앞에 추가</button>
	<button type="button" onclick="deletefunc()">삭제</button>
	
	<script type="text/javascript">
	function appendfunc() {
		let ptag = document.createElement('p');		//<p></p>
		let textNode = document.createTextNode('새로운 태그');	//문자열
		
		ptag.appendChild(textNode);	// <p>새로운 태그</p>
		
		let element = document.getElementById("div1");
		element.appendChild(ptag);
	}
	function insertfunc() {
		let h3tag = document.createElement("h3");
		let textNode = document.createTextNode('새로운 h3 태그');
		
		h3tag.appendChild(textNode);
		
		let element = document.getElementById('div1');
		let eleBefore = document.getElementById("p2");
		
		element.insertBefore(h3tag,eleBefore);
	}
	function deletefunc() {
		let element = document.getElementById('div1');
		let removeEle = document.getElementById("p2");
		element.removeChild(removeEle);
	}
	</script>
```
노드에 추가 및 삭제를 할 수 있다.
### 예제
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="func1()">

<h2>단사 X 수량 알람표</h2>
<table border="2" id="tb1">
	<tr >
		<th>개수</th><th>제품A</th><th>제품B</th><th>상품C</th>
	</tr>

</table>

<script type="text/javascript">

// 만약 값이 일정하지 않으면 file로 read
let aArr = [23,45,89,1,2];
let bArr = [99,5,11,84,76];
let cArr = [2,35,14,95,3];

function func1() {
	for (var i = 0; i < aArr.length; i++) {
		let trtag = document.createElement('tr');
		
		let thtag = document.createElement('th');
		let thtext = document.createTextNode(i);
		thtag.appendChild(thtext);
		
		let tdtag1 = document.createElement('td');
		let tdtext1 = document.createTextNode(aArr[i]);
		tdtag1.appendChild(tdtext1);
		
		let tdtag2 = document.createElement('td');
		let tdtext2 = document.createTextNode(bArr[i]);
		tdtag2.appendChild(tdtext2);
		
		let tdtag3 = document.createElement('td');
		let tdtext3 = document.createTextNode(cArr[i]);
		tdtag3.appendChild(tdtext3);
		
		let ele = document.getElementById('tb1');
		ele.appendChild(trtag);
		ele.appendChild(thtag);
		ele.appendChild(tdtag1);
		ele.appendChild(tdtag2);
		ele.appendChild(tdtag3);
	}
	
}
</script>
</body>
</html>
```
위의 예제는 자료가 적어서 직접 입력할 수 도 있지만 만약 대용량의 파일을 받아서 테이블로 넣으려고 하면 노드에 추가하여 처리 할 수 있다.

### nodelist
```html
<!-- NodeList -->
	
	<p>Hello</p>
	
	<p>World</p>
	<p>I can do it</p>
	<p>Never Change My Mind</p>
	
	<button type="button" onclick="listfunc()">NodeList</button>
	
	<script type="text/javascript">
	function listfunc() {
		let nodelist = document.getElementsByTagName("p");
		//alert(nodelist.length);
		nodelist[3].innerHTML = "안녕하세요";
		
		for (var i = 0; i < nodelist.length; i++) {
			nodelist[i].style.backgroundColor = "#00ff00";
		}
	}
	
	</script>
```
리스트에 자료를 넣어서 인덱스로 불러올 수 있다.
