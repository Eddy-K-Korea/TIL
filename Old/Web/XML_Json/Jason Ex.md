# 예제1 (값 얻어오기)

## Json 파일
```json
[
	{
		"name":"홍길동",
		"age":24,
		"address":"서울시",
		"tel":"123-2323-2543"
	},
	{
		"name":"일진매",
		"age":20,
		"address":"수원시",
		"tel":"123-3333-2773"
	},
	{
		"name":"성춘향",
		"age":18,
		"address":"속초시",
		"tel":"333-6888-7777"
	},
	{
		"name":"김병인",
		"age":28,
		"address":"제주시",
		"tel":"555-7773-7777"
	}

]
```
### 값 출력
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

let xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function () {	// xlm 과 파싱은 동일
	if(this.readyState == 4 && this.status == 200){
		jsonFunc(this.responseText);	// 문자열로 바로 받으면 배열로 바로 돌려서 값을 얻을 수 있다.
	}
}
  
xhttp.open("GET", "jsondata.json", true); // json 파일 불러오기
xhttp.send();

function jsonFunc(resp) {
	let arr = JSON.parse(resp);
	let txt = "";
	
/* 	let array = [11,22,33];
	for (i in array) {			//for(Object obj : Array) 이거와 다르다.
		console.log(array[i]);
	} */
	
	 /* for (var i = 0; i < arr.length; i++) {    // key 값 불러오기
		for (key in arr[i]) {
			txt += key+ ":"+arr[i];
		}
		txt += "<br>";
	}  */
	 for (var i = 0; i < arr.length; i++) {
		txt += arr[i].name + " " + 
			  arr[i].age + " " +
			  arr[i].address+ " "+
			  arr[i].tel + " " + "<br>"; 
	}
	document.getElementById('demo').innerHTML = txt;
}
</script>
</body>
</html>
```
# 예제 2
```json
let param2 = {
	    "quiz": {
	        "sport": {
	            "q1": {
	                "question": "Which one is correct team name in NBA?",
	                "options": [
	                    "New York Bulls",
	                    "Los Angeles Kings",
	                    "Golden State Warriros",
	                    "Huston Rocket"
	                ],
	                "answer": "Huston Rocket"
	            }
	        },
	        "maths": {
	            "q1": {
	                "question": "5 + 7 = ?",
	                "options": [
	                    "10",
	                    "11",
	                    "12",
	                    "13"
	                ],
	                "answer": "12"
	            },
	            "q2": {
	                "question": "12 - 8 = ?",
	                "options": [
	                    "1",
	                    "2",
	                    "3",
	                    "4"
	                ],
	                "answer": "4"
	            }
	        }
	    }//quiz
	}
```
### 값 출력
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

function jsonTest() {
	
	//question 데이터가 하나일땐 그냥 접근하고 2개 이상일때는 배열로 들어가게 된다.
	
	document.getElementById('demo').innerHTML 
	//	= param2.quiz["sport"].q1.question;
	
	//options[3] 접근
	//	= param2.quiz["sport"].q1.options[3];
	//question(math) ("5 + 7 = ?")
	//	= param2.quiz["maths"].q1.question;
	//maths options[1] (11)
		= param2.quiz["maths"].q1.options[1];
}

jsonTest(); // 함수 호출
	
</script>
</body>
</html>
