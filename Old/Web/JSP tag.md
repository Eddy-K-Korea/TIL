# JSP tag

- forward(전진), include(불러오기)

```html
<h3>index.jsp start</h3>
<jsp:include page="NewFile.jsp" flush="false"></jsp:include>
<h3>index.jsp end</h3>
```
- NewFile.jsp 파일에 <h3>NewFile.jsp</h3>가 있다.

```html
<jsp:forward page="NewFile.jsp"></jsp:forward>
```
- NewFile.jsp로 이동하게 된다.

### Object 생성
- 기존 JSP 으로 생성 방법
```html
<%
	MemberDto dto = new MemberDto();
	dto.setMessage("안녕하세요");
	String msg = dto.getMessage();
	request.setAttribute("mem", dto);
%>

${mem.message} 
```
- jsp tag로 생성
```html
<jsp:useBean id="dto" class="dto.MemberDto"></jsp:useBean>
<jsp:setProperty property="message" name="dto" value="ㄱㄱㄱ"/>
<jsp:getProperty property="message" name="dto"/>

${ dto.message }  <!-- jsp태그로 객체를 생성하면 el태그를사용하여 바로 출력 가능하다 -->
```
