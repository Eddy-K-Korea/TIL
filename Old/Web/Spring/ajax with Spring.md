# ajax 스프링에서 구현

### ajax 구현시 필요한 라이브러리 pom.xml에서 다운
```xml
 <dependencies>
  	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-webmvc</artifactId>
	    <version>5.2.7.RELEASE</version>
	</dependency>
  	<dependency>
	    <groupId>log4j</groupId>
	    <artifactId>log4j</artifactId>
	    <version>1.2.17</version>
	</dependency>
  	<dependency>
	    <groupId>org.slf4j</groupId>
	    <artifactId>slf4j-simple</artifactId>
	    <version>1.7.25</version>
	</dependency>
	
		<!-- Java용 json 라이브러리(XML/YAM/CSV) data-processing 툴  스프링버전에 맡게 해야한다-->
  	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-core</artifactId>
	    <version>2.10.1</version>
	</dependency>
	
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-databind</artifactId>
	    <version>2.10.1</version>
	</dependency>
  </dependencies>
```
### web.xml은 디비가 없는 경우이기 때문에 servlet-context.xml 경로만 잡아주기만하면 된다.<br>
### 그리고 servlet-context.xml은 views와 확장자설정 잘해주면 된다. (공통 패키지도 설정)

### Ajax는 비동기 통신이기 때문에 view와 controller의 통신이 중요하다

### 1. controller
```java
package bit.com.a;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.Human;
import bit.com.a.dto.MyClass;

@Controller
public class HelloController {
	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	//1번 객체 단순 
	@RequestMapping(value = "hello.do", method = RequestMethod.GET)
	public String hello(Model model) {
		logger.info("hello" + new Date());
		
		MyClass cls = new MyClass(1001, "홍길동");
		model.addAttribute("mycls",cls);
		
		return "hello";
	}
	//2번
	@RequestMapping(value = "move.do", method = RequestMethod.GET)
	public String move() {
		logger.info("move" + new Date());
		
		return "redirect:/hello.do";  // == sendRedirect 컨트롤로 이동가능 그냥 "hello.do"를 또사용하면 컨트럴에서 컨트럴은 못간다
	//	return "forward:/hello.do";//forward 값을 가지고 이동 가능
	}
	
	
	//3번
	
	@ResponseBody  // ajax사용시 반드시 추가 (이동하는게 아니라 데이터만 넘겨주는거다)
	@RequestMapping(value = "idcheck.do", method = RequestMethod.GET, produces = "application/String;charset=utf-8")
	public String idcheck(String id) {
		logger.info("idcheck" + new Date());
		logger.info("id:" + id);
		
		String str = "오케이";
		return str;
	}
	
	//4번  파라미터로 받을때 객체로 설정하게되면 받은 값들로 객체를 자동생성하게된다.
	@ResponseBody
	@RequestMapping(value = "account.do", method = RequestMethod.POST)
	public Map<String, Object> account(Human my){
		logger.info("account" + new Date());
		logger.info(my.toString());
		
		//DB 접근 위치
		
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("message", "내가 보낸 메세지 입니다.");
		map.put("name", my.getName());
		
		
		return map; // map으로 여러개의 값들을 넘겨줄 
	}
	
	//5번
	@ResponseBody
	@RequestMapping(value = "updateUser.do", method = RequestMethod.POST)
	public Map<String, Object> updateUser(@RequestBody Map<String, Object> map){	//@RequestBody를 붙여줘야 map으로 받아들일 수 있다
		logger.info("updateUser"+ new Date());
		logger.info(map.get("name")+"");		//object로 넘어와서 문자열로 만들어줘야한다.
		logger.info(map.get("tel")+"");
		logger.info(map.get("email")+"");
		logger.info(map.get("birth")+"");
		
		
		Map<String , Object> rmap = map;
		rmap.put("message", "내가 보낸 메세지 입니다.");
		rmap.put("name", map.get("name"));
		
		
		return rmap;
	}
	
	//연습
	@ResponseBody
	@RequestMapping(value = "test.do", method = RequestMethod.POST)
	public List<MyClass> test(MyClass cls){
		logger.info("test"+new Date());
		logger.info(cls.toString());
		
		List<MyClass> list = new ArrayList<MyClass>();
		list.add(new MyClass(1, "홍길동"));
		list.add(new MyClass(2, "일지매"));
		list.add(new MyClass(3, "성춘향"));
		list.add(new MyClass(cls.getNumber(), cls.getName()));
		return list;
	}
}
```

### 2. view
```html
<%@page import="bit.com.a.dto.MyClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h2>hello</h2>

<!-- 1 -->
<%
	MyClass cls = (MyClass)request.getAttribute("mycls");
%>
number:<%=cls.getNumber() %><br>
name:<%=cls.getName() %><br>


<br>
<!-- EL tag -->
number: ${mycls.number }<br>
name: ${mycls.name }<br>
<br>

<!-- 2 -->

<form action="move.do">
	<input type="submit" value="move">
</form>
<br><br>

<!-- 3 -->

아이디: <input type="text" id="checkid"><br>
<br>
<button type="button" id="_check" onclick="idcheck()">id check</button>

<script type="text/javascript">
function idcheck(){
	$.ajax({
		url:"./idcheck.do",
		type:"get",
		//data:"id="+$("#checkid").val(),
		data:{id:$("#checkid").val()},
		success:function(data){
			//alert('success');
			alert(data);
		},
		error:function(){
			alert('error');

		}
	});
	
}

</script>
<br><br><br>

<!-- 4 -->
<!-- 
		json(view) -> Object(controller)
		Map(controller) -> json(view)
 -->
이름: <input type="text" id="_name" value="홍길동"><br>
전화: <input type="text" id="_phone" value="123-456"><br>
이메일: <input type="text" id="_email" value="hgd@naver.com"><br>
생년월일: <input type="text" id="_birth" value="2001/11/23"><br>
<button type="button" id="account">회원가입</button>


<script type="text/javascript">
$("#account").click(function(){
	//alert('account');

	// web(json) -> Controller(Dto로 받을 수 있다)
	// Controller(map) -> web(json)
// 아래 json 형식으로 값들을 
	let human = {
				name:$("#_name").val(),
				tel:$("#_phone").val(),
				email:$("#_email").val(),
				birth:$("#_birth").val()
			};
	$.ajax({

		url:"./account.do",
		type:"post",
		dataType:"json",
		data:human,
		async:true,
		success:function(resp){
			//alert('success');
			alert(resp.message);
			alert(resp.name);
		},
		error:function(){
			alert("error");
		}	
	});
});

</script>

<!-- 5 -->
<!-- 
	json(view) -> Map(controller)
 -->
<br><br>
이름: <input type="text" id="name1" value="일지매"><br>
전화: <input type="text" id="phone1" value="3444-234"><br>
이메일: <input type="text" id="email1" value="lgm@naver.com"><br>
생년월일: <input type="text" id="birth1" value="2012-06-23"><br>
<button type="button" id="account1">회원가입</button>


<script type="text/javascript">
$("#account1").on("click",function(){
	//alert('account1');
	let data = {};
//data안에 json형식으로 변환하여 넣어준다.
	data["name"] = $("#name1").val();
	data['tel'] = $("#phone1").val();
	data['email'] = $("#email1").val();

	let birth = $("#birth1").val();
	data["birth"] = birth.replace(/-/g,"");			//2012-06-23  -> 20120623   해당 방식을 잘 기억하자 "-"를 없애준다
	//alert(data["birth"]);

	$.ajax({
		url:"updateUser.do",
		type:"post",
		datatype:"json",
		data:JSON.stringify(data),			//json -> String 변환   그래야 컨트럴에서 map으로 받음 :(stringify  <-> parse)
		contentType:"application/json",   //map변환시 
		success:function(resp){
			//alert('success');
			alert(resp.name);
		},
		error:function(){
			alert('error');
		}
	});
	
});

</script>


<!-- 연습 -->

<!-- 
입력: number, name (web)
json-> controller (list) 3개 담기 -> web확인 

 -->
<br><br>
숫자: <input type="text" id="num"><br>
이름: <input type="text" id="name"><br>

<button type="button" id= "btn">전송</button>

<script type="text/javascript">
$("#btn").click(function(){
	let test = {
			number:$("#num").val(),
			name:$("#name").val()

			};

	$.ajax({

		url:"./test.do",
		type:"post",
		data:test,
		datatype:"json",
		async:true,
		success:function(data){
			//alert(data);
			alert(JSON.stringify(data));
			$.each(data, function(i,val){
				$("#btn").after(val.number+"<br>"+val.name+"<br>")
			});
		},
		error:function(){

		}	
	});
	
});

</script>
</body>
</html>
```

### ajax 예제에 사용했던 Human 객체와 Myclass 객체이다
- Human
```java
package bit.com.a.dto;

import java.io.Serializable;

public class Human implements Serializable {

	private String name;
	private String tel;
	private String email;
	private String birth;
	
	public Human() {
	}

	public Human(String name, String tel, String email, String birth) {
		super();
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Human [name=" + name + ", tel=" + tel + ", email=" + email + ", birth=" + birth + "]";
	}
}
```
- Myclass
```java
package bit.com.a.dto;

import java.io.Serializable;

public class MyClass implements Serializable {

	private int number;
	private String name;
	
	public MyClass() {
	}

	public MyClass(int number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MyClass [number=" + number + ", name=" + name + "]";
	}
}
```
