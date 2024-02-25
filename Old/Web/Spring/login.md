# 로그인 및 회원가입 페이지 간단 구성 
###  pom.xml과 web.xml / servlet-context.xml / applicationContext.xml 의 설정과 경로는 mybatis예제와 동일하게 진행

### 첫 indext.jsp
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
response.sendRedirect("loginpage.do");
%>

</body>
</html>
```
### 첫 실행시 loginpage.do 의 Controller 로 이동
```java
package bit.com.spring;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.MemberService;


@Controller
public class MemberController {

	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "loginpage.do", method = RequestMethod.GET)
	public String gologin() {
		logger.info("gologin"+new Date());
		return "login";
	}
}  
```  
- login.jsp로 이동을 하게된다.

### 로그인 페이지로 이동  
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="wrapper">
	<div class="login_content">
			<form id="frm">
			<h1>Login Page </h1>
			<div class="login_input">
				<div class="_id"><input type="text" class="log_info" id="id" name="id" placeholder="Uername" required="required"></div>
				<div class="_saveId"><input type="checkbox" value="" id="chk_save_id">Save id</div>
				<div class="_pw"><input type="Password" class="log_info" id="pwd" name="pwd" placeholder="Password" required="required"></div>
				<button type="button" id="_btnLogin" class="btnclass">로그인</button>
			</div>
				<div class="pw_hint">
					<p class="hint-text"><a href="#">Forgot Password?</a></p>
				</div>
			<div class="create_id">지금 부터 우리와 함께하기 <a href="./signup.do"><i>회원가입</i></a></div>
			</form>
	</div>
</div>

<script type="text/javascript">
$("#_btnLogin").click(function(){
	if($("#id").val()==""||$("#pwd").val()==""){
			alert("아이디 / 비밀번호를 입력해 주세요");
	}else{
		let memberInfo = {
				id:$("#id").val(),
				pwd:$("#pwd").val()
				};

		$.ajax({
			url:"./login.do",
			type:"post",
			datatype:"json",
			data:memberInfo,
			async:true,
			success:function(data){
				if(data=="success"){
					location.href="./bbslist.do";
				}else{
					alert(data);
				}
			},
			error:function(){
				alert("error");
			}

		});
	}
});

</script>
</body>
</html>
```
- 회원가입을 하기위해 ```<a href="./signup.do"><i>회원가입</i></a></div>```해당 경로의 controller 이동
### 회원가입페이지로 이동을 위해 컨트롤러 이동
```java
@RequestMapping(value = "signup.do", method = RequestMethod.GET)
public String gosignup() {
  logger.info("gosignup"+new Date());
  return "signup";
}
```  
### 회원가입 페이지 이동
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="wrapper">
	<div class="contents">
		
			<div class="_id">
				<h3>아이디</h3>
				<input type="text" id="id" name="id" maxlength="20">
				<button type="button"  class ="check_btn" id="check_id" >중복 확인</button>
				<p id="checkedId" style="height: 10px"></p>
			</div>		
			<div class="_pw">
				<h3>비밀번호</h3>
				<input type="password" id="pwd" maxlength="20">
			</div>
			
			<div class="_name">
				<h3>이름</h3>
				<input type="text" id="name" maxlength="20">
			</div>
			
			<div class="checkEmail">
				<h3>이메일</h3>
				<input type="text" id="email" placeholder="선택입력" maxlength="100">
			</div>
			<br>
			
	</div>
	<div class='btn'>
				<button type="button" id="suBtn" class="signUpBtn">가입하기</button>
	</div>
	
</div>
<script type="text/javascript">
$("#suBtn").click(function(){ //버튼을 누르면 회원가입 진행 ajax 설명은 ajax설명 편 참고

	let member={
		id:$("#id").val(),
		pwd:$("#pwd").val(),
		name:$("#name").val(),
		email:$("#email").val()
			};
	
	$.ajax({
		url:"./addMem.do",
		type:"post",
		data:member,
		datatype:"json",
		async:true,
		success:function(data){
			alert(data);
			location.href="./loginpage.do"
		},
		error:function(){
			alert('error');
		}
	});
});



$("#check_id").click(function(){  //버튼 클릭시 아이디 중복 확인
	$.ajax({
		url:"./checkid.do",
		type:"get",
		data:{id:$("#id").val()},
		success:function(data){
			$("#checkedId").text(data);
		},
		error:function(){
			alert('error');
		}	
	});
});

</script>
</body>
</html>
```
### 만약 아이디 중복을 누르면 컨트럴로 이동
```java
@ResponseBody
@RequestMapping(value = "checkid.do", method = RequestMethod.GET, produces = "application/String;charset=utf-8")
public String checkid(String id) {
  logger.info("checkid"+new Date());
  logger.info("id:"+id);
  int result = memberService.checkid(id);
  String message= "";
  if(result>0) {
    message = "이미 있는 아이디 입니다.";
  }else {
    message = "사용 가능한 ID입니다.";
  }
  return message;
}
```  
- 처음 컨트롤 이동시 @Autowire를 통해 memberService를 생성하고 checkid에 매개변수 String id 를 통해 받은 아이디를 서비스로 넘겨준다.

### 서비스로 이동하여 dao를 생성하고 dao의 메소드를 생성한다. (아래는 서비스 인터페이스)
```java
package bit.com.spring.service;

import bit.com.spring.dto.MemberDto;

public interface MemberService {

	String addMember(MemberDto dto);
	int checkid(String id);
	MemberDto login(MemberDto dto);
}
```
### 서비스 실제 객체 checkid(String id)에서 dao  (인터페이스 상속)
```java
package bit.com.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.MemberDao;
import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.MemberService;

@Service
public class MemberServiceimpl implements MemberService{
	
	@Autowired
	MemberDao memberDao;
	@Override
	public String addMember(MemberDto dto) {
		String msg="";
		boolean isS = memberDao.addMember(dto);
		if(isS) {
			msg="회원가입 성공";
		}else {
			msg="회원가입 실패";
		}
		
		return msg;
	}
	@Override
	public int checkid(String id) {
		int result = Integer.parseInt(memberDao.checkId(id));
		return result;
	}
	@Override
	public MemberDto login(MemberDto dto) {
		MemberDto member = memberDao.login(dto);
		return member;
	}
}
```
### dao의 메소드를 불러 오기 위해 dao 인터페이스 생성과 상속을 해준다. checkId(String id)의 메소드를 통해 쿼리문 실행
```java
package bit.com.spring.dao;

import bit.com.spring.dto.MemberDto;

public interface MemberDao {
	boolean addMember(MemberDto dto);
	String checkId(String id);
	MemberDto login(MemberDto dto);
}
```
- dao 인터페이스 상속받은 클래스
```java
package bit.com.spring.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dao.MemberDao;
import bit.com.spring.dto.MemberDto;

@Repository
public class MemberDaoimpl implements MemberDao{

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "Member.";
	
	@Override
	public String checkId(String id) {
		
		String result = sqlSession.selectOne(namespace+"checkId", id);
		return result;
	}

	@Override
	public boolean addMember(MemberDto dto) {

		int result = sqlSession.insert(namespace+"addMem", dto);
		return result>0?true:false;
	}

	@Override
	public MemberDto login(MemberDto dto) {
		
		MemberDto member = sqlSession.selectOne(namespace+"login", dto);
		return member;
	}
}
```
### Member.xml의 쿼리문을 통해 DB접근하여 아이디 중복 확인 id="checkId" 의 쿼리문 실행
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <mapper namespace="Member">
  
  	<insert id="addMem" parameterType="bit.com.spring.dto.MemberDto">
  		INSERT INTO MEMBER
  		VALUES(#{id},#{pwd},#{name},#{email},3)
  	</insert>
  	
  	<select id="checkId" parameterType="java.lang.String" resultType="java.lang.String">
  		SELECT COUNT(ID) FROM MEMBER WHERE ID = #{id}
  	</select>
  	
  	<select id="login" parameterType="bit.com.spring.dto.MemberDto" resultType="bit.com.spring.dto.MemberDto">
  		SELECT * FROM MEMBER WHERE ID = #{id} AND PWD = #{pwd}
  	</select>
  </mapper>
```
### 만약 정보를 모두 입력하고 회원가입 버튼을 누르면 아래 컨트롤러실행
```java
@ResponseBody
@RequestMapping(value = "addMem.do", method = RequestMethod.POST, produces = "application/String;charset=utf-8")
public String addMem(MemberDto member) {
  logger.info("addMem"+new Date());
  String message = memberService.addMember(member);
  return message;
}
```
### memberService 에서 addMember(MemberDto dto) 실행하고 memberDao 에서 addMember(MemberDto dto) 실행한다.
- 두개 모두 파라미터를 받을때 dto 객체로 받는다. 

### 로그인 화면에서 id 와 pw를 입력하고 로그인을 하게되면 아래 컨트롤러 이동한다.
```java
@ResponseBody
@RequestMapping(value = "login.do", method = RequestMethod.POST, produces = "application/String;charset=utf-8")
public String login(MemberDto login) {
  logger.info("addMem"+new Date());
  MemberDto member = memberService.login(login);
	if(member != null && !member.getId().equals("")) {
		message = "success";
		//session 저장
		req.getSession().setAttribute("login", member);

	}else {
		message ="id/pw가 일치하지 않습니다.";
	}
  return message;
}
```  
- id와 pw 를 가지고 dto를 만들어서 넘겨준다. 그리고 쿼리문에서 값을 돌려받을때 dto로 돌려받고 입력받은 id와 돌려받은 dto의 id가 동일한지 확인
- 그리고 로그인을 하게 되면 게시판 페이지로 이동하게 된다.
### location.href = "bbslist.do"를 통해 컨트롤러 이동
```java
package bit.com.spring;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BbsController {

	private static Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@RequestMapping (value = "bbslist.do", method = RequestMethod.GET)
	public String gobbslist() {
		logger.info("gobbslist"+new Date());
		return "bbslist";
	}
}
```
### 그리고 bbslist.jsp로 이동



# 이메일 인증 구현
### pom.xml 라이브러리 추가
```xml
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>javax.mail</artifactId>
    <version>1.6.2</version>
</dependency>


<dependency>
    <groupId>javax.mail</groupId>
    <artifactId>mail</artifactId>
    <version>1.4.7</version>
</dependency>
```

### applicationContext.xml 에 객체 생성
- gamil 기준
```xml
<!-- Gmail -->
<bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
    <property name="host" value="smtp.gmail.com" />
    <property name="port" value="587" />
    <property name="username" value="아이디@gmail.com" />
    <property name="password" value="비밀번호" />
    <property name="javaMailProperties">
    <props>
      <prop key="mail.smtp.auth">true</prop>
      <prop key="mail.smtp.starttls.enable">true</prop>
    </props>
    </property>
</bean>
```

### view에 이메일 인증 버튼과 입력 칸 생성
```html
<div class="checkEmail">
	<h3>이메일</h3>
	<input type="text" id="email" placeholder="선택입력" maxlength="100">
	<button type="button" id="email_auth_btn">이메일 인증 보내기</button><br>
	<input type="text" id="email_auth_check" placeholder="인증코드 입력">
</div>


<script>
$("#email_auth_btn").click(function() {
	$.ajax({
		url:"./emailAuthCheck.do",
		type:"get",
		data:{email:$('#email').val()},
		success:function(data){
			alert("success");
		},
		error:function(){
			alert("error");
		}
	});
});
</sctipt>
```
### Controller에서 메일을 받고 메일 전송
```java
@Inject
JavaMailSender mailSender;
@ResponseBody
@RequestMapping(value = "emailAuthCheck.do", method = RequestMethod.GET, produces = "application/String;charset=utf-8")

public String emailAuthCheck(String email,HttpServletRequest req, HttpServletResponse response) throws IOException {
	logger.info(email);

	Random ran = new Random();
	int dice = ran.nextInt(456413) + 12345;	// 인증 코드 생성

	String setfrom = "bitcamp5j0@gamil.com";
	String tomail = email;
	String title = "회원 가입 인증 이메일";
	String content = "인증 코드 : " + dice;

	try {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true,"UTF-8");

		messageHelper.setFrom(setfrom); // 보낼 아이디
		messageHelper.setTo(tomail);	// 받을 아이디(인증코드를 필요로하는 사용자)
		messageHelper.setSubject(title);	//제목
		messageHelper.setText(content);		//내용

		mailSender.send(message);

	} catch (Exception e){
		System.out.println(e);
	}
	return null;
}
```
### java Mail 핸들러 클래스 생성
```java
package bit.com.spring.mail;

import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {

	private JavaMailSender mailSender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	public MailHandler(JavaMailSender mailSender) throws MessagingException {
        this.mailSender = mailSender;
        message = this.mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }
 
 
    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
        
        // 이메일 타이틀 
    }
    
    public void setText(String htmlContent) throws MessagingException {
        messageHelper.setText(htmlContent, true);
        
        //  이메일 TEXT 부분 
    }
    
    public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException {
        messageHelper.setFrom(email, name);
        // 보내는 사람 이메일 
    }
    
    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
        //받는 사람 이메일 
    }
    
    public void addInline(String contentId, DataSource dataSource) throws MessagingException {
        messageHelper.addInline(contentId, dataSource);
    }
    
    public void send() {
        try {
            mailSender.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
```

### 인증 버튼을 누르게 되면 위에 setfrom의 아이디로 로그인하여 사용자 이메일로 인증코드 메일을 발송한다 
- 주의점: 구글 개인설정 보안에서 낮은 수준의 앱 허용을 허가해 주어야 한다.
