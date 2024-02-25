# EL tag

- Expression Language (자바코드대용) 
- front end 에서 Java를 사용하지 않기위해 사용

#### EL tag -> value

### 1. 원래 JSP 사용
```html
<%
	String str = "hello";
	request.setAttribute("str", str);
%>
<%
	String s = (String)request.getAttribute("str");
%>
<%
	out.println("s = "+s);
%>

request.setAttribute("data", "안녕하세요");
```
### 2. el tage 사용
```html
el s = ${str } <!--Java 표현식안에 setAttribute에 들어간 오브젝트의 지정한 name 값으로 불러옴 -->

${ data }<!-- getAttribute로 받을 필요가 없어진다. -->
```
### 3. 기본 연산 가능
```html
${2 + 3 }

${ 3>2?100:200 }

1 < 9 : ${ 1<9 } <!-- 판별식(true/false) -->

2+3 : ${ 2+ 3 } <!-- 연산식 value -->
```
### 4. Object가 비어있는지 확인
```html
object = ${ empty data }

object = ${ not empty data }
```
- 원래 확인 JSP
```html
<%
Object obj = new String("hello");
obj = null;
if(obj == null){
	//할당되어있는지 확인할때의 조건문 (보통 이렇게 확인했었다)
}
%>
```
### 5. Object 넣고 비교
- EL tag 사용하기 위해서는 Object형태로 request.setAttribute()를 해주어야한다.
```html
<%
	Integer a, b;	// 그냥 int 로 하면 에러가 난다 (object)로 넣어야해서
	a = 10;
	b = 3;
	
	request.setAttribute("a", a);
	request.setAttribute("b", b);
	
	Boolean c;
	c = false;
	
	request.setAttribute("c", c);
%>
```
- 비교
```html
a:${ a }
b:${ b }
c:${ c }

a+b:${a +b }

eq=${ a eq b }
eq:${ a == b }	<!-- 위의 eq와 동일 -->

ne:${ a ne b }
ne:${ a != b }

gt:${ a gt b }
gt:${ a > b }

lt:${ a lt b }
gt:${ a < b }

le:${a le b }<!-- <= -->
ge:${a ge b }<!-- >= -->

div:${ a div b } <!-- a/b -->
div:${ a / b }

mod:${a mod b}
mod:${a % b}


c:${ !c }

${ a == 10 && !c }
```

### 6. 객체 사용
```html
<%
MemberDto dto = new MemberDto();
dto.setMessage("Hello EL");
%>

<%=dto.getMessage() %>  <-- JSP에서 이렇게 접근 -->
```
- el tag 사용 접근
```html
<%
request.setAttribute("dto", dto);
%>
message:${dto.message }
```

### 7. 배열 사용
```html
<%
String array[] = {"hello", "EL" };
request.setAttribute("array", array);
%>

<%=array[0] %>  <-- JSP 방식 -->
<br>

${ array[0] } <-- el tag -->
```

### 8. 리스트 사용
```html
<%
	List<String> list = new ArrayList<>();
	list.add("world");
	list.add("el");
	
	request.setAttribute("list", list);
%>

<%=list.get(0) %> <-- JSP 사용방식 -->
<br>

${ list[0] }  <-- el tag --> 
<br><br>

<%
	List<MemberDto> memlist = new ArrayList<>();
	MemberDto mem = new MemberDto();
	mem.setMessage("안녕하세요");
	memlist.add(mem);
	
	mem = new MemberDto();
	mem.setMessage("건강하세요");
	memlist.add(mem);
	
	request.setAttribute("memlist", memlist);
%>

<%= memlist.get(1).getMessage()%>
<br>
${memlist[1].message }  <-- 바로 함수명으로 사용 -->
```

### 9. Map 사용
```html
<%
	HashMap<String, String> map = new HashMap<>();

	map.put("apple","사과");
	map.put("grape","포도");
	
	request.setAttribute("map", map);
%>

<%=map.get("apple") %> <-- JSP 사용방식 -->
<br>
${map.apple }<br>  <-- 바로 키 이름으로 사용 -->
${map["apple"]" }
```

