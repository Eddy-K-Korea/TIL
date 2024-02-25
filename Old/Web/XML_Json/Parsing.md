# Xml (DOM 으로 파싱)

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p id="demo"></p>

<script type="text/javascript">
let xmltext = "<bookstore>" + 		//root tag
				  "<book>" +		// node
				  	"<title>탈무드</title>" +
				  	"<author>man</author>" +
				  	"<year>2001</year>" +
				  "</book>" +
				  "<book>" +		// node
				  	"<title>이솝이야기</title>" +
				  	"<author>woman</author>" +
				  	"<year>2005</year>" +
				  "</book>" +
			  "</bookstore>"
let parser, xmlDoc;
			  
parser = new DOMParser();
xmlDoc = parser.parseFromString(xmltext, "text/html"); // xmlDoc = 파싱이되어서 나오는 완성품
			  
document.getElementById("demo").innerHTML 
//	= xmlDoc.getElementsByTagName("book")[0].childNodes[0].nodeName; // title 출력
//	= xmlDoc.getElementsByTagName("book")[0].childNodes[1].nodeName; // author 출력
//	= xmlDoc.getElementsByTagName("book")[1].childNodes[0].childNodes[0].nodeValue; // 탈무드 출력
//	= xmlDoc.getElementsByTagName("book").length; // 데이터 2개 (현재 데이터 양)
	= xmlDoc.getElementsByTagName("book")[1].childNodes.length; // 3개
</script>

</body>
</html>
```

# Xml (XMLHttpRequest() 통해 추출)

## client XML file
사용자 테그를 통해서 데이터를 생성(예시용)
```xml
<?xml version="1.0" encoding="UTF-8"?>

<고객들>
	<고객>
		<번호>1</번호>
		<이름>홍길동</이름>
		<주소>서울시</주소>
		<방문>2020/01/23</방문>
	</고객>
	<고객>
		<번호>2</번호>
		<이름>일지매</이름>
		<주소>수원시</주소>
		<방문>2020/05/05</방문>
	</고객>
	<고객>
		<번호>3</번호>
		<이름>성춘향</이름>
		<주소>인천시</주소>
		<방문>2019/01/31</방문>
	</고객>
</고객들>
```
위의 client xml을 파싱
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p id="demo"></p>

<script type="text/javascript">
let xhttp = new XMLHttpRequest();   // xhttp 생성

xhttp.onreadystatechange = function (){   // 데이터를 받기위한 준비 
	if(this.readyState == 4 && this.status == 200){ // 데이터 불러오기완료 및 성공 시 아래 함수 실행
		nodeValFunc(this);
	}
}

xhttp.open("GET", 'client.xml', true);
xhttp.send();

function nodeValFunc(xml) { // 데이터를 구체적으로 불러오는 함수
	
	let num, name;
	let txt, numtxt, xmlDoc;
	
	txt = numtxt = "";
	
	xmlDoc = xml.responseXML; // object 통째로 넘겨옴
	console.log(xmlDoc);
	
	num = xmlDoc.getElementsByTagName('번호'); // num은 배열로 된다 . 배열로 넘겨준다.
	name = xmlDoc.getElementsByTagName('이름');
	console.log(num.length);
	
	for (i = 0; i < num.length; i++) {    // for문을 돌려주어 배열의 값들을 
		txt += num[i].childNodes[0].nodeValue + "<br>"; 
		numtxt += name[i].childNodes[0].nodeValue + "<br>";
	}
	
	document.getElementById('demo').innerHTML = txt + numtxt;
}
				   
function nodeNameFunc(xml) {	//노드의 이름을 불러오는 함수
	let arr,xmlDoc,txt;
	txt = "";
	xmlDoc = xml.responseXML;
	arr = xmlDoc.documentElement.childNodes; // 배열에 문서의 노드들을 넣어준다
	
	for (i = 0; i < arr.length; i++) {
		if(arr[i].nodeType == 1){		//실제 데이터가 들어가있는 타입만 
			txt += arr[i].nodeName+'<br>';	// 노드이름을 추출
		}
	}
	document.getElementById("demo").innerHTML =txt;
}
	
function childNodeFunc(xml) {		// 태그의 자식 노드를 불러오는 함수
	let arr, xmlDoc, txt;
	txt = "";
	
	xmlDoc = xml.responseXML;
	
	arr = xmlDoc.getElementsByTagName("고객")[0]; 
	let len = arr.childNodes.length;
	console.log(len);
	
	let fchild = arr.firstChild;	// '고객' 노드의 첫번째 자식 노드를 배열로 넣어줌
	
	for (i = 0; i < len; i++) {
		if(fchild.nodeType == 1){
			txt += i + " " + fchild.nodeName +"<br>";
		}
		fchild = fchild.nextSibling;
	}
	document.getElementById('demo').innerHTML = txt;
}
</script>

</body>
</html>
```
