# Core tag

- jstl.jar, standard.jar 코어태그 사용시 필요 jar (mavenrepository)에서 다운로드 가능

- Core: 제어문(for, if) + El tag
```
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

태그 사용을 위해 상위 문구 추가 필요

 ```
### 1. 기본 형태
 ```html
 <c:set var="cdata" value="core JSTL입니다" />
${ cdata } <-- core JSTL입니다 출력 -->
```
### 2. out
- session에도 저장 가능
- <c:out 을 통해 출력 가능
```html
<%
	session.setAttribute("sessionData", "저장된 데이터");
%>
<c:set var="sdata" value="${ sessionData }"/>

<c:out value="${sdata}"/>
```
### 3. if
- JSP 에서 if 문 사용할 경우
```html
<%
	request.setAttribute("count", "10");
%>

<%
String scount = (String)request.getAttribute("count");
int count = Integer.parseInt(scount);
if(count >= 10){
%>
	<p>count:<%=count %></p>
<%	
}
%>
```
- core tag 를 사용
```html
<c:if test="${ count >= 10 }">
	<p> core count:<c:out value="${count }"></c:out></p>
</c:if>
```
- 예제
```html
<%
	request.setAttribute("name", "홍길동");
%>

<c:if test="${ name eq'홍길동' }"> <-- 홍길동과 이름이 같을경우 출력 -->
	<p>이름은 홍길동 입니다</p>
</c:if>
<%
	request.setAttribute("name", "일지매");
%>

<c:if test="${name == '일지매' }" var="flg"/>  <!-- 조건문 결과 true/false 를 변수에 대입 -->

<c:if test="${flg }">
	<p>이름은 일지매 입니다.</p>
</c:if>
```
### 4. for
- JSP 에서 for 문 사용할 경우
```html
<%
	for(int i = 0; i < 10; i++){
		%>
		<%=i %>
		<%
	}
%>
```
- core tage 사용
```html
<c:forEach begin="0" end="9" step="1" varStatus="i">
	<c:out value="${i.index}"></c:out>
</c:forEach>
```
### 5. list
- JSP에서 사용
```html
<%
	List<MemberDto> list = new ArrayList<>();

	MemberDto mem = new MemberDto();
	mem.setMessage("안녕하세요");
	list.add(mem);
	
	mem = new MemberDto();
	mem.setMessage("건강하세요");
	list.add(mem);
	
	mem = new MemberDto();
	mem.setMessage("집에 가고싶다");
	list.add(mem);
	
	request.setAttribute("list", list);
%>
<%
	for(int i = 0; i < list.size(); i++){
		MemberDto m = list.get(i);
	}
%>
```
- core tag 사용
```html
<c:forEach begin="0" end="2" var="m" items="${list}" varStatus="i">
	<p>index:<c:out value="${i.index}"/> </p>
	<p>data:<c:out value="${m.message}"/> </p>
</c:forEach>
```

### 6. Map
- core tag 사용
```html
<%
	Map<String, String> map = new HashMap<>();

	map.put("apple", "사과");
	map.put("pear", "배");
	map.put("banana", "바나나");
	
	request.setAttribute("map", map);
%>
<-- key, value를 사용하여 그 값을 얻을 수 있음 -->
<c:forEach var="obj" items="${map}">
	key:<c:out value="${obj.key}"/> 
	value:<c:out value="${obj.value }"/>
</c:forEach>
```
