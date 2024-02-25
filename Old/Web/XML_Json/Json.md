# Json

## Java Script Object Notation
- Array -> Json
- 한쌍(pair) key:value == HashMap(java)

### 형태
```
[
		{name: "홍길동", age:24, addr:"서울시" },
		{name:"성춘향", age:21, addr:"남원시" }
]	
```
```
web(client)				  Java(Server)		<---- > DB 
Json			------- >  List, HashMap, Object, String 으로 받을 수 있다.		
key.변수명				
	
Ajax를 제일 많이 사용	
```  

### Json 값 얻기
```html
let jsonData = [
{
"name":"홍길동",
"age":24
},
{
"name":"일지매",
"age":21
},
{
"name":"성춘향",
"age":16
}
];
```        
```html
// ★ 문자열을 json으로 바꾸는 단계 , (통계형자료를 문자열로 만들어놓고 json으로 변형하여 값 추출)
  	let arrText = '{"name":"홍길동", "age":24, "주소":"서울시"}';	// json이 아니다. 단순 String
//	let arrText = {"name":"홍길동", "age":24, "주소":"서울시"}; //이거는 동작한다
	let jsonObj = JSON.parse(arrText);	// String -> Json 으로 변경
	alert(jsonObj);//object로 받은 데이터
	let str = JSON.stringify(jsonObj); // json을 다시 문자열로 변형
	alert(str);
document.getElementById('demo').innerHTML = jsonObj.name+" "+jsonObj.주소;
```
