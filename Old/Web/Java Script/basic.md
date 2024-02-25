# Java Script 

## 기본 
- 자바 스크립트는 자바기반으로 만들어졌지만 자바와 완전히 동일한 언어는 아니다.
### - 목적 
1. 웹 브라우저의 Tag를 접근하기 위한 스크립트.
2. 보조 프로그래밍적인 요소이다.
3. 화면을 제어하기 위한 요소이다.
   > - 예) 회원가입 -> 빈칸을 조사, ID글자수를 조사,패스워드 숫자/글자 포함 체크
4. 컴파일을 하지 않는다. -> 가볍다 -> 속도가 빠르다 (단 버그나 에러 잡기가 어렵다)
   > Java Script 경량화 -> JQuery
   

5. CSS에 대해서 제어 , 구현
6. Timer를 설정해서 특수 효과처리가 가능하다.
   
### - 접근하기 위한 속성(Attribute)  -  Porperty
- id -> 1개	JS
- class -> 다수 CSS JS
- name -> 다수  JS

### <b>주의점</b>
- Java Script <- Server OK
- Java Script -> Server No		==> 그래서 등장한게 Node JS
- JQuery(Ajax) <- Server OK

## 기본 문법
```html
alert("Java Script 실행");

console.log("Java Script 실행 되었습니다.");
```
alert = 기입한 문자열로 알림창이 뜬다. / console = 웹브라우저 개발자 모드 console 창에 값이 뜬다.

## 변수 선언
### 1. 변수 형태
1. var -- Integer, Double, String, Object, Character, class    
2. let -- Integer, Double, String, Object, Character, class(var의 경우 제한이 없이 같은 변수명이여도 그대로 사용가능하여 에러를 잡아내기가 어려움)
따라서 최근에는 let을 사용한다.

```JavaScript
var num = 123;
var pi = 3.141592;
var neme = "홍길동";
var answer = true;

human = "홍길동";
number = 234 + 1;
console.log(human);
console.log(number);
var human; // 먼저 선언하지 않고 나중에 선언을 해도 오류가 나지 않는다. (엄격하지 않아 오히려 사용시 헷갈릴 수 있다)
	
number = "일지매";
console.log(number);
	
	
man = "이몽룡";
console.log(man);
let man;        // let은 반드시 값을 대입하는 순서보다 먼저 선언을 해야한다. 만약 그러지 않으면 에러가 난다.
  // let은 같은 변수명일때 오류를 잡아내고 , var은 잡아내지 못한다.
```

### 2. 배열
```javascript
//Array
	let cars = ["Saab","Volvo","BMW"]; // 배열 선언 (자바와의 차이점은 선언시 괄호 차이)
	console.log(cars[1]); // volvo 출력
	
	// int Arr[] = new int[3];
 	cars = new Array(3);
 	cars[0] = '사브';
 	cars[1] = '볼보';
 	cars[2] = '비엠';
 	console.log(cars[0]); 
 	
 	for(i=0; i <cars.length; i++){
 		console.log(cars[i]);
 	}
 	
 	for(i in cars){  //JS for each 문
 		console.log(cars[i]);
 	}
```

### 3. Object (객체)
```javascript
//Object = Json(kye:value),class
 	//const == final
 	
 	let obj = {
 		firstname:"길동",
 		lastname:'홍',
 		age:24,
 		func:function(){
 			console.log("func() 호출");
 			console.log("lastname: "+ this.lastname);
 				
 		}
 	}
 	
 	console.log(obj.firstname);
 	console.log(obj.lastname);
 	obj.func();
 	
 	class Person{
 		constructor(name, age){
			this.name = name; //this 로 받으면 자동으로 멤버변수로 선언하게 된다.
			this.age = age;
		}
 		print(){
 	//		console.log(this.name+ " "+ this.age);
 			console.log(`${this.name}:hello`)
 		}
 	}
 	
 	let per = new Person('일지매', 21);
 	console.log(per.name);
 	console.log(per.age);
 	per.print();
```
객체의 경우 2가지 종류가 있으며 Json은 key 와 value 로 : 을 통해 구분하여 생성한다. 또한 class의 경우 자바와 동일하게 생성하며 클래스 이름없이 생성자만을 통해
생성하고 멤버변수 초기화 없이 this를 통해 생성자에서 삽입하면 자동 생성된다.

### 4.메서드
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="myScript.js"></script>


<script type="text/javascript">
function func() {
	alert("function() 호출");
}

</script>

</head>
<body>

<p id="demo" onclick="func()">여기가 p태그</p>

</body>
</html>
```
<script type="text/javascript" src="myScript.js"></script> 을 통해 소스파일을 생성하고 외부에서 메서드를 불러오는 경우도 있다. 간단한 메서드의 경우 head나 body에 선언하여 
사용 할 수 있다.

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<p id="demo">p tag id demo</p>

<input type = "button" value="input버튼" onclick="btnFunc1(1)">
<br><br>
<button type="button" onclick="btnFunc1(2)">button버튼</button>
<br><br>
<button type = "button" onclick="btnclick()">버튼</button>

<script type="text/javascript">

function btnFunc1(num) {
	let name;
	if(num == 1){
		name = 'input tag button';
	}else{
		name = 'button tag';
	}
	alert(name);
	
	document.getElementById('demo').innerHTML = name;
	
}

function sum(x,y) {
	return x+y;
}

function btnclick() {
	document.getElementById('demo').innerHTML = sum(2,3);
}

</script>

</body>
</html>
```
메서드의 파라미터를 통해 그에 맞는 값을 불러올 수 있고 document.getElementById('demo').innerHTML 의 함수를 통해 id가 demo인 텍스트를 name의 값으로 변환 할 수 있다.
