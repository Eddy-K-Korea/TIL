# model 1
### ajax를 사용하여 jsp파일을 통해 db에서 json을 불러와 출력
- JSP
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// MVC model1

// DB 접근
String userName = "홍길동";
int userNumber = 1001;

String json = "{\"num\":"+userNumber + ", \"name\":\""+userName+"\"}"; //따옴표를 쓰려고 \ 을 사용
System.out.println(json);

out.println(json);
%>
```
- HTML
```html
<p id='demo'></p>
<br>

<button type="button">click</button>

<script type="text/javascript">
$(function() {
	$('button').click(function() {
		$.ajax({
			/////////////////////////send 
			url:"data.jsp",
			type:"get",
			datatype:"json",	// 데이터를 json 형태로 보낼기 위해 데이터 타입을 잡아줌 
			////////////////////////	data를 통해 보내주지만 현재는 안보내줌
			success:function(obj){ 
			//	alert('success');
			//	alert(obj);	문자열로 넘어온다 , data.jsp 에서 문자열
				let json = JSON.parse(obj); // 문자열을 json으로 변경
			//	alert(json);
				alert(json.name); // 이름 접근
			},
			error:function(){
				alert('error');
			}
		});
	});
});
</script>
```

# model 2
### ajax를 사용하여 servlet을 통해 db에서 json을 불러와 출력
- HTML
```html
<p id='demo'></p>
<br>
<input type="text" id="id">
<button type="button">click</button>

<script type="text/javascript">
$(function() {
	$("button").click(function() {
		$.ajax({
			url:"custuser",
			type:"get",
			datatype:"json",
			//data:"id=ABC&pwd=123",
			data:{id:$("#id").val(),pwd:"123"},
			success:function(json){
			//	alert("success");
			//	alert(json.str);
			//	alert(json.map.title);	// 제이손 -> 맵 -> 키값
			
				let custlist = json.map.custlist; // 리스트를 받아준다
				alert(custlist[0].id);
				alert(custlist[0].name);
			},
			error:function(){
				alert("error");
			}
		});
	});
});
</script>
```
- Servlet
- *Servlet에서 Ajax으로 데이터를 보내기 위해서는 jar파일을 lib에 넣어주어야한다*

![image](https://user-images.githubusercontent.com/65350890/88362639-4136ac00-cdb8-11ea-8c5a-eb9b0e2f6ea5.png)


```java
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CustUserDto;
import net.sf.json.JSONObject;

@WebServlet("/custuser")
public class CustUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("CustUserServlet doGet");
		String id = req.getParameter("id");   // Ajax에서 보내준 데이터를 받을 수 있다
		String pwd = req.getParameter("pwd");
	//	System.out.println(id+" "+pwd);
		
		// 전송 data
		JSONObject jobj = new JSONObject();	//json으로 변환
		//jobj.put("str", "Hello");	//String 전송
		
		// hashmap을 통한 데이터 전달
		/*HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", "사과");
		
		jobj.put("map", map);*/ // HashMap 전송
		
		
		// list 를 통한 데이터 전달
		List<CustUserDto> list = new ArrayList<CustUserDto>();
		list.add(new CustUserDto("aaa", "홍길동", "서울시"));
		list.add(new CustUserDto("bbb", "일지매", "수원시"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("custlist", list);	//데이터를 묶어놓은 list를 map에 넣어주고
		
		jobj.put("map", map);	// 맵을 다시 json에 넣어준다
		
		
		resp.setContentType("application/x-json; charset=UTF-8");//한글이 깨진다 그래서 인코딩
		resp.getWriter().print(jobj);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CustUserServlet doPost");
	
	}

}
```
## 모델 2 다른 방법
-html
```html
<p id='demo'></p>
<br>
<!-- <input type="text" id="id"> -->
<button type="button">click</button>

<script type="text/javascript">
$(function() {
	$("button").click(function() {
		$.ajax({
			url:"./hello",	//경로지정
			type:"post",
			datatype:"json",
			success:function(json){
		//		alert('success');
			//	alert(json);
			//	alert(json[0].id);
			//	alert(json[0].name);
			//	alert(json[0].address);
				$.each(json, function(i,val){
					$("#demo").append("i:"+i+" id:"+ val.id+" name:"+val.name+" address:"+val.address+"<br>");  //i는 인덱스 번호, val.불러올 키값
				});
			},
			error:function(){
				alert("error");
			}
		});
	});
});
</script>
```
### - 컨트롤러
- 여기서 사용하는 jar는 gson-2.8.5.jar 이다.

![image](https://user-images.githubusercontent.com/65350890/88376611-b87c3800-cdd8-11ea-9d38-ae401b55bb34.png)

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* 예시
		String str = "hello";
		// google json
		String gson = new Gson().toJson(str);	//json형태로 변환
		resp.getWriter().write(gson);	//데이터를 보내준다
		*/
		
		List<CustUserDto> list = new ArrayList<CustUserDto>();
		list.add(new CustUserDto("eee", "홍길동", "평양시"));
		list.add(new CustUserDto("ttt", "일지매", "베이징시"));
		
		resp.setContentType("application/json"); //json 으로 변환
		resp.setCharacterEncoding("UTF-8"); //한글깨짐 방지
		
		//gson-2.8.5.jar 추가해야함
		String gson = new Gson().toJson(list);	//리스트를 담는다.
		
		resp.getWriter().write(gson); //return 함수
		
	}
}
```

---
# 모델 2 Ajax 변경

### HTML
```html
<tr class="titleAf">
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>

<!-- 여기에 추가 된다 -->

<tr>
	<td align="center" height="1" bgcolor="#c0c0c0" colspan="3">		
		<input type="submit" value="고객정보 삭제">
	</td>
</tr>
...

<script type="text/javascript">
//$(document).ready(function () {
$(function () {
	$.ajax({
		url:"./custData",
		type:"get",
		data:"work=data",	//work 값을 넘겨준다.
		success:function(datas){
			let custlist = datas.map.custlist;	//해쉬맵의 리스트를 넘겨준다
			
			$.each(custlist, function (i, val) {			
				let app = "<tr bgcolor='#f6f6f6'>" 
						+	"<td align='center' bgcolor='yellow'>" 
						+		"<input type='checkbox' name='delck' value='" + val.id + "'>"	//값에 아이디를 넣어줌(전체삭제시 필요)
						+	"</td>"		
						+	"<td>" + val.id + "</td>"	// 아이디 표시를 위해 값을 대입
						+	"<td>"
						+		"<a href='custcontroller?work=detail&id=" + val.id + "'>" + val.name + "</a>" 
											//컨트롤러에게 work값과 아이디 값을준다. 그리고 이름 값 대입
						+	"</td>"
						+ "</tr>"
						+ "<tr>"
						+ 	"<td height='2' bgcolor='#0000ff' colspan='3'></td>"
						+ "</tr>";
				
				$(".titleAf").eq(-1).after(app);	// class titleAf 뒤에 추가 eq(음수)는 선택된 (클래스)의 만 마지막인덱스
			});				
			
		},
		error:function(){
			alert('error');
		}
	});
	
});
</script>
```
### Servlet
#### 1. CustUserData
```java
@WebServlet("/custData")
public class CustUserData extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String work = req.getParameter("work");
		
		if(work.equals("data")) {
			CustUserDao dao = CustUserDao.getInstance();			
			List<CustUserDto> list = dao.getCustUserList();
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("custlist", list);
			
			// 전송할 데이터를 추가
			JSONObject jobj = new JSONObject();
			jobj.put("map", map);			
			
			resp.setContentType("application/x-json; charset=UTF-8");
			resp.getWriter().print(jobj);
		}
	}

}
```
#### 2. CustUserController
```java
@WebServlet("/custcontroller")
public class CustUserController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String work = req.getParameter("work");	
		
		if(work.equals("move")) {	
			forward("custuserlist.html", req, resp);	// 제일 처음 index의 링크의 work='move'(현재 깃허브에 안올림)
		}		
		else if(work.equals("detail")) {	//이름을 누르면 work의 값이 넘어와 이쪽 조건으로 들어오게된다.
			String id = req.getParameter("id");
			System.out.println("id:" + id);
			
			CustUserDao dao = CustUserDao.getInstance();
			CustUserDto custdto = dao.getCustuser(id);			
			// 보내줄 데이터
			req.setAttribute("cust", custdto);			
			// 이동
			forward("custuserdetail.jsp", req, resp);
		}
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher(link);
		dis.forward(req, resp);		
	}
}
```
