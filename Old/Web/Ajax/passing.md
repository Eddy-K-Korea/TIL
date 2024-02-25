# Json Passing

### Json 파일을 ajax를 사용하여 html에 출력
- Json
```json
[
	{
		"name":"홍길동",
		"age":24,
		"address":"서울시",
		"phone":"123"	
	},
	{
		"name":"일지매",
		"age":28,
		"address":"수원시",
		"phone":"456"	
	},
	{
		"name":"성춘향",
		"age":20,
		"address":"남원시",
		"phone":"789"	
	}
]
```
- html
```html
<p id='demo'></p>
<br>

<button type="button">click</button>

<script type="text/javascript">
$(function() {
	$("button").click(function() {
		
		//String -> json	: JSON.parse      이거 두개는 꼭 기억해야한다
		// json -> string	: JSON.stringify
		$.ajax({
			url:"data.json",
			type:"get",
			datatype:"json",	//타입을 설정해야 json 타입으로 보낸다.
			success:function(json){	//매개변수는 아무명이나 가능하다
			//	alert('success');
			//	alert(json); 오브젝트라고 메세지 뜸
				let str = JSON.stringify(json); //Json을 문자열로바꿈
				alert(str); //메세지에 Json 값들이 표시
				alert(json[0].name+" "+json[0].age); // 첫번째 Json의 이름과 나이 출력
				
				for (var i = 0; i < json.length; i++) {
					$("#demo").append(json[i].name+" ");
					$("#demo").append(json[i].age+" ");
					$("#demo").append(json[i].address+" ");
					$("#demo").append(json[i].phone+"<br>");
				}
        ///////////////////////////////////////////////
				$.each(json, function(index, item) {	//위의 for(반복문)와 같다
					$("#demo").append( index + " ");	//배열 인덱스 번호
					$("#demo").append( item.name + " ");
					$("#demo").append( item.age + " ");
					$("#demo").append( item.address + " ");
					$("#demo").append( item.phone + "<br>");
				});
				
			},
			error:function(){
				alert('error');
			}
		});
	});
});
</script>
```
<br><br>
---
# xml passing
### xml 파일을 ajax를 사용하여 html에 출력
- xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<xmldata>
	<news>
		<target>naver</target>
		<link>http://www.naver.com</link>
		<aname>네이버</aname>
	</news>
	<news>
		<target>daum</target>
		<link>http://www.daum.net</link>
		<aname>다움</aname>
	</news>
	<news>
		<target>google</target>
		<link>http://www.google.co.kr</link>
		<aname>구글</aname>
	</news>
</xmldata>
```
- html
```html
<p id='demo'></p>
<br>

<button type="button">click</button>

<script type="text/javascript">
let target = [];
let link = [];
let aname = [];

$(function() {
	$("button").click(function() {
		$.ajax({
			url:"data.xml",
			datatype:"xml",
			success:function(data){
			//	alert('success');
				let xml = $(data).find("xmldata"); //root tag 를 찾는다
				let len = xml.find('news').length;
			//	alert(len);
				for (var i = 0; i < len; i++) {
					target[i] = xml.find("news").eq(i).find("target").text(); // 1. news를 찾고 그안에서 타겟을 찾고 그 안의 text를 가져온다.
					link[i] = xml.find("news").eq(i).find("link").text();
					aname[i] = xml.find("news").eq(i).find("aname").text();
				}
				for (i = 0; i < len; i++) {
					$("#demo").append(target[i]+" "+link[i]+" "+aname[i]+"<br>");
				}
			},
			error:function(){
				alert("error");
			}
		});
	});
});

</script>
```
