# 게시판 (스프링)
### 1. 로그인 이후 BbsController으로 이동
```java
package bit.com.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.spring.dto.BbsDto;
import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.BbsService;

@Controller
public class BbsController {

	private static Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	BbsService bbsService;
	@RequestMapping (value = "bbslist.do", method = RequestMethod.GET)
	public String gobbslist(Model model, HttpServletRequest req) {
		logger.info("gobbslist"+new Date());
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		list = bbsService.allBbsList();
		model.addAttribute("allBbsList",list);
		return "bbslist";
	}
}  
```  
### 2. Service 인터페이스와 클래스를 만들어준다.
```java
package bit.com.spring.service;

import java.util.List;

import bit.com.spring.dto.BbsDto;

public interface BbsService {

	List<BbsDto> allBbsList();
	boolean addbbs(BbsDto bbsdto);
	BbsDto getbbs(String seq);
	boolean addreply(BbsDto bbsdto);
	boolean updatestep(BbsDto bbsdto);
	List<BbsDto> getsearchlist(String pick,String search);
	boolean removebbs(String seq);
	boolean updatebbs(BbsDto bbs);
}
```
- 실제 클래스
```java
package bit.com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.BbsDao;
import bit.com.spring.dto.BbsDto;
import bit.com.spring.service.BbsService;

@Service
public class BbsServiceimpl implements BbsService {
	
	@Autowired
	BbsDao bbsDao;
	@Override
	public List<BbsDto> allBbsList() {
		List<BbsDto> list = bbsDao.allBbsList();
		return list;
	}
	@Override
	public boolean addbbs(BbsDto bbsdto) {
		
		boolean isS = bbsDao.addbbs(bbsdto);
		return isS;
	}
	@Override
	public BbsDto getbbs(String seq) {
		BbsDto dto = bbsDao.getbbs(seq);
		return dto;
	}
	@Override
	public boolean addreply(BbsDto bbsdto) {
		boolean isS = bbsDao.addreply(bbsdto);
		return isS;
	}
	@Override
	public boolean updatestep(BbsDto bbsdto) {
		boolean isS = bbsDao.updatestep(bbsdto);
		return isS;
	}
	@Override
	public List<BbsDto> getsearchlist(String pick,String search) {
		List<BbsDto> list = bbsDao.getsearchlist(pick, search);
		return list;
	}
	@Override
	public boolean removebbs(String seq) {
		boolean isS = bbsDao.removebbs(seq);
		return isS;
	}
	@Override
	public boolean updatebbs(BbsDto bbs) {
		boolean isS = bbsDao.updatebbs(bbs);
		return isS;
	}
}
```
### 3. 실제 쿼리문을 실행시켜줄 Dao 인터페이스와 클래스를 생성한다
```java
package bit.com.spring.dao;

import java.util.List;

import bit.com.spring.dto.BbsDto;

public interface BbsDao {
	List<BbsDto> allBbsList();
	boolean addbbs(BbsDto bbsdto);
	BbsDto getbbs(String seq);
	boolean addreply(BbsDto bbsdto);
	boolean updatestep(BbsDto bbsdto);
	List<BbsDto> getsearchlist(String pick,String search);
	boolean removebbs(String seq);
	boolean updatebbs(BbsDto bbs);
}
```
- 실제 dao 클래스
```java
package bit.com.spring.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dao.BbsDao;
import bit.com.spring.dto.BbsDto;

@Repository
public class BbsDaoimpl implements BbsDao {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "bbs.";
	
	@Override
	public List<BbsDto> allBbsList() {
		List<BbsDto>list = sqlSession.selectList(namespace+"allbbsList");
		
		return list;
	}

	@Override
	public boolean addbbs(BbsDto bbsdto) {
		
		int result = sqlSession.insert(namespace+"addbbs", bbsdto);	
		return result>0?true:false;
	}

	@Override
	public BbsDto getbbs(String seq) {
		int seqnum = Integer.parseInt(seq);
		BbsDto dto = sqlSession.selectOne(namespace+"getbbs", seqnum);
		return dto;
	}

	@Override
	public boolean addreply(BbsDto bbsdto) {
		
		int result = sqlSession.insert(namespace+"addreply", bbsdto);
		return result>0?true:false;
	}

	@Override
	public boolean updatestep(BbsDto bbsdto) {
		int result = sqlSession.update(namespace+"updatestep", bbsdto);
		return result>0?true:false;
	}
	
	@Override
	public List<BbsDto> getsearchlist(String pick,String search) {
		List<BbsDto> list = new ArrayList<BbsDto>();
		Map<String, Object> map = new HashMap<String, Object>();	//검색 카테고리 분류와 검색어가 두개 이기 때문에 hashmap에 넣어주고 paramterType으로 넘겨준다
		map.put("s_category", pick);
		map.put("keyword", search);
		list = sqlSession.selectList(namespace+"getsearchlist", map);
		
		return list;
	}

	@Override
	public boolean removebbs(String seq) {
		int result = sqlSession.update(namespace+"removebbs", seq);
		return  result>0?true:false;
	}

	@Override
	public boolean updatebbs(BbsDto bbs) {
		int result = sqlSession.update(namespace+"updatebbs", bbs);
		return result>0?true:false;
	}
}
```
### 4. 다오에서 실행하는 bbs 의 쿼리문 을 만들어준다
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="bbs">
	<select id="allbbsList" resultType="bit.com.spring.dto.BbsDto">
		SELECT*FROM BBS ORDER BY REF DESC , STEP ASC
	</select>  
	
	<insert id="addbbs" parameterType="bit.com.spring.dto.BbsDto">
		INSERT INTO BBS(SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT)
		VALUES(SEQ_BBS.NEXTVAL,#{id},(SELECT NVL(MAX(REF),0)+1 FROM BBS),0,0,#{title},#{content},SYSDATE,0,0)
	</insert>
	
	<select id="getbbs" parameterType="java.lang.Integer" resultType="bit.com.spring.dto.BbsDto">
		SELECT* FROM BBS WHERE SEQ = #{seqnum}
	</select>
	
	<update id="updatestep" parameterType="bit.com.spring.dto.BbsDto">
		UPDATE BBS
		SET STEP = STEP +1
		WHERE REF = (SELECT REF FROM BBS WHERE SEQ = #{seq}) AND STEP > (SELECT STEP FROM BBS WHERE SEQ = #{seq})
	</update>
	
	<insert id="addreply" parameterType="bit.com.spring.dto.BbsDto">
		INSERT INTO BBS(SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT)
		VALUES(SEQ_BBS.NEXTVAL,#{id},(SELECT REF FROM BBS WHERE SEQ = #{seq}),(SELECT STEP FROM BBS WHERE SEQ = #{seq})+1,(SELECT DEPTH FROM BBS WHERE SEQ = #{seq})+1,
		#{title},#{content},SYSDATE,0,0)
	</insert>
	
	<!-- hashmap을 파라미터로 받고 key 값을 이용해 조건문 실행 -->
	<select id="getsearchlist" parameterType="hashmap" resultType="bit.com.spring.dto.BbsDto">
		SELECT *
		FROM BBS
		WHERE 1=1
		<choose>	<!-- 쿼리문에서 조건문을 실행시켜준다 --> 
			<when test="s_category == 'title'">
				AND TITLE LIKE '%'||#{keyword}||'%'
			</when>
			<when test="s_category == 'content'">
				AND CONTENT LIKE '%'||#{keyword}||'%'
			</when>
			<when test="s_category == 'id'">
				AND ID = #{keyword}
			</when>
		</choose>
		ORDER BY REF DESC , STEP ASC
	</select>
	
	<update id="removebbs" parameterType="java.lang.String">
		UPDATE BBS
		SET DEL = 1
		WHERE SEQ = #{seq}
	</update>
	
	<update id="updatebbs" parameterType="bit.com.spring.dto.BbsDto">
		UPDATE BBS
		SET TITLE = #{title}, CONTENT = #{content}
		WHERE SEQ = #{seq}
	</update>
</mapper>
```
### 5. 처음 리스트를 뿌려준다
```html
<%@page import="bit.com.spring.dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<BbsDto> list = (List<BbsDto>)request.getAttribute("allBbsList");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bbs list</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
.removedbbs{
	color:red;
}
</style>
</head>
<body>
<h1>게시판</h1>
<a href="BbsWrite.do">글쓰기</a><br><br>
<div align="center">
<table border="1" >
<col width="70"><col width="600"><col width="150">
<tr>
	<th>번호</th><th>제목</th><th >작성자</th>
</tr>
<%
if(list.size()==0){
	%>
	<tr><th colspan="3">작성된 글이 없습니다</th></tr>
	<%
}else{
	for(int i = 0; i < list.size(); i++){
		String ts ="";
		for(int j = 0; j < list.get(i).getDepth(); j++){
			
			String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
			ts = ts+nbsp;
		}
		if(list.get(i).getDel()==1){
			%>
			<tr class='bbslist'>
				<th ><%=i+1%></th><td colspan="2" class="removedbbs"><%=ts %>이 글은 작성자에 의해 삭제 되었습니다.</td>
			</tr>
			<%
		}else{
		%>
		<tr class="bbslist">
			<th><%=i+1%></th><td onclick="location.href='./gobbsdetail.do?seq=<%=list.get(i).getSeq()%>'"><%=ts %><%=list.get(i).getTitle()%></td><td><%=list.get(i).getId()%></td>		
		</tr>
		<%
		}
	}
}
%>

</table>

</div><br>
<div align="center">
<select id="choice">
	<option value="sel">선택</option>
	<option value="id">작성자</option>
	<option value="title">제목</option>
	<option value="content">내용</option>
</select>
<input type="text" id="search">
<button id="searchBbs">검색</button> 
</div>

<script type="text/javascript">
let choice = "";
$("#choice").change(function() {
	choice = $("#choice").val();
	
});
$("#searchBbs").click(function() {
	let search = {
			pick:choice,
			search:$("#search").val()
			};

	$.ajax({
		url:"./searchbbs.do",
		type:"get",
		datatype:"json",
		data:search,
		success:function(data){
			let addstr = "";
			//alert("success");
			$('tr').remove(".bbslist")
			$.each(data,function(i,val){
				if(val.del==1){
				addstr += "<tr class='bbslist'><th>"+(i+1)+"</th><td colspan='2' class='removedbbs'>이 글은 작성자에 의해 삭제 되었습니다.</td></tr>";
				}else{
					let ts="";
					for(let j = 0; j < val.depth; j++){
						
						let nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
						ts = ts+nbsp;
					}
					addstr += "<tr class='bbslist'>";
					addstr +="<th>"+(i+1)+"</th><td onclick=\"location.href='./gobbsdetail.do?seq="+val.seq+"'\">"+ts+val.title+"</td><td>"+val.id+"</td>";		
					addstr +="</tr>";
				}
			});
			$('tr').eq(-1).after(addstr);
		},
		error:function(){
			alert("error");
		}
		
	});
});
</script>

</body>
</html>
```
### 6. 글쓰기 부분 누르면 html과 글 추가 컨트롤을 생성해준다
```java
<%@page import="bit.com.spring.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberDto member = (MemberDto)request.getSession().getAttribute("login");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bbs write</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div>
	<form id="frm">
		<div>
		작성자: <input type="text" name="id" readonly="readonly" value="<%=member.getId()%>">
		</div>
		<div>제목: <input type="text" name="title"></div>
		<div>
			<h4>내용:</h4>
			<textarea rows="13" cols="80" name="content"></textarea>
		</div>
	</form>
	<button type="button" id="list_btn">목록</button>
	<button type="button" id="add_btn">글 추가</button>
</div>
<script type="text/javascript">
$("#list_btn").click(function(){
	location.href = "./bbslist.do";
});
$("#add_btn").click(function() {

	if($('input[name=title]').val()==""){
		alert("제목을 입력해주세요");
	}else if($('textarea[name=content]').val()==""){
		alert("내용을 입력해주세요");
	}else{
		
		let formdata = $("#frm").serialize();
		
		$.ajax({
			url:"addbbs.do",
			type:"get",
			datatype:"json",
			data: formdata,
			success:function(data){
				if(data==true){
					alert("글이 추가 되었습니다");
					location.href="./bbslist.do";
				}else{
					alert("글 추가 실패")
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
### - 글쓰기 컨트롤 생성
```java
@RequestMapping(value = "BbsWrite.do", method = RequestMethod.GET)
public String gobbswrite() {
  logger.info("gobbswrite"+ new Date());

  return "bbswrite";
}

@ResponseBody
@RequestMapping(value = "addbbs.do", method = RequestMethod.GET)
public boolean addbbs(BbsDto bbsdto) {
  logger.info("addbbs "+ new Date());
  logger.info(bbsdto.toString());

  boolean isS = bbsService.addbbs(bbsdto);

  return isS;
}
```
### - 글 클릭시 자세하게 볼 수 있는 페이지와 컨트롤 생성
```java
@RequestMapping(value = "gobbsdetail.do", method = RequestMethod.GET)
public String gobbsdetail(String seq, Model model) {
  logger.info("gobbsdetail" + new Date());
  logger.info("seq: "+seq);

  BbsDto dto = bbsService.getbbs(seq);
  model.addAttribute("getbbs", dto);

  return "bbsDetail";
}
```
### - 글 클릭시 자세히 볼 수 있는 페이지 생성
```html
<%@page import="bit.com.spring.dto.MemberDto"%>
<%@page import="bit.com.spring.dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
BbsDto bbs = (BbsDto)request.getAttribute("getbbs");
MemberDto member = (MemberDto)request.getSession().getAttribute("login");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bbs Detail</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>작성한 글</h1>
<table border="1px solid" >
<col width="100px"><col width="300px">
<tr>
	<th colspan="2"><%=bbs.getId() %>님이 작성하신 글</th><th>작성일</th><td><%=bbs.getWdate() %></td><th>조회수</th><td><%=bbs.getReadcount()%></td>
</tr>
<tr>
	<th>정보</th><td colspan="5"><%=bbs.getRef() %>-<%=bbs.getStep() %>-<%=bbs.getDepth() %></td>
</tr>
<tr>
	<th >제목</th><td align="center" colspan="5"><%=bbs.getTitle() %></td>
</tr>
<tr>
	<th colspan="6">내용</th>
</tr>
<tr>
	<td colspan="6"><%=bbs.getContent() %></td>
</tr>
</table>
<button type="button" id="list_btn">목록</button>
<h3>댓글</h3>
<table>
	<tr>
		<th>제목</th><td><input type="text" id="replytitle" size="30"></td>
	</tr>
	<tr>
		<th>내용</th><td><textarea rows="5" cols="50" id="replycontent"></textarea></td>
	</tr>
</table>
<button type="button" id="addreply_btn">댓글 작성</button>
<br><br>
<%
if(member.getId().equals(bbs.getId())){
	%>
	<button type="button" id="updatebbs_btn">수정</button>
	<button type="button" id = "deletebbs_btn">삭제</button>
	<%
}
%>
<script type="text/javascript">
$("#list_btn").click(function(){
	location.href = "./bbslist.do";
});

$("#addreply_btn").click(function() {
	if($("#replytitle").val()==""||$("#replycontent").val()==""){
		alert("제목이나 내용을 입력해 주세요");
	}else{
		let reply = {
				seq:'<%=bbs.getSeq()%>',
				id:'<%=bbs.getId()%>',
				title:$("#replytitle").val(),
				content:$("#replycontent").val()
				};

		$.ajax({
			url:"./addreply.do",
			type:"get",
			datatype:"json",
			data:reply,
			async:true,
			success:function(data){
				if(data==true){
					alert("댓글이 추가 되었습니다");
					location.href="./bbslist.do";
				}else{
					alert("댓글 추가 실패");
				}
			},
			error:function(){
				alert("error");
			}
			
		});
	}
});

$("#deletebbs_btn").click(function(){
	$.ajax({
		url:"./remove.do",
		type:"get",
		datatype:"json",
		data:"seq="+"<%=bbs.getSeq()%>",
		success:function(data){
			if(data==true){
				alert('글이 삭제 되었습니다');
				location.href="./bbslist.do";
			}else{
				alert('삭제 실패');
			}
		},
		error:function(){
			alert("error");
		}
				
	});
});
$("#updatebbs_btn").click(function(){
	location.href="update.do?seq="+"<%=bbs.getSeq()%>";
	
});

</script>
</body>
</html>

```
### - 댓글 추가시 사용할 컨트롤 생성 ( 댓글들의 step 업데이트와 insert한 댓글 두개의 메소드생성)
```java
@ResponseBody
@RequestMapping(value = "addreply.do", method = RequestMethod.GET)
public boolean addreply(BbsDto bbs) {
  logger.info("addreply"+ new Date());
  logger.info(bbs.toString());

  bbsService.updatestep(bbs);
  boolean isS = bbsService.addreply(bbs);

  return isS;
}
```
### - 리스트에서 검색시 그에 맞는 글 찾기위한 컨트롤 생성
```java
@ResponseBody
@RequestMapping(value = "searchbbs.do", method = RequestMethod.GET)
public List<BbsDto> searchbbs(String pick, String search) {
//	logger.info("searchbbs"+ new Date());
	logger.info("pick: "+pick+" search: "+search);

	List<BbsDto> list = bbsService.getsearchlist(pick, search);

	return list;
}
```
### - bbsdetail에서 글삭제 버튼을 누르면 삭제되는 컨트롤 생성
```java
@ResponseBody
@RequestMapping(value = "remove.do", method = RequestMethod.GET)
public boolean removebbs(String seq) {

	logger.info(seq);

	boolean isS = bbsService.removebbs(seq);
	return isS;
}
```
### - 글 수정 버튼을 누르면 수정을 하기위한 페이지로 이동하는 컨트롤 생성
```java
@RequestMapping(value = "update.do", method = RequestMethod.GET)
public String goupdatebbs(String seq, Model model,  HttpServletRequest req) {
	BbsDto dto = bbsService.getbbs(seq);
	model.addAttribute("bbsdto", dto);
	return "updatebbs";
}
```
### - 글 수정 페이지 생성
- el 태그를 활용하여 생성 (컨트롤에서 BbsDto의 객체를 bbsdto 이름으로 넘겨주어서 el태그에서 사용)
- 아이디 부분의 ```${login.id }```은 이전 MemberController에서 설정한 Session의 name값을 적어준다 (MemberDto를 login으로 set해주었기 때문에 객체이름.변수명)
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bbs Update</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>수정할 글</h1>
<table border="1px solid" >
<col width="100px"><col width="300px">
<tr>
	<th colspan="2">${login.id }님이 작성하신 글</th><th>작성일</th><td>${bbsdto.wdate }</td><th>조회수</th><td>${bbsdto.readcount }</td>
</tr>
<tr>
	<th>정보</th><td colspan="5">${bbsdto.ref }-${bbsdto.step }-${bbsdto.step }</td>
</tr>
<tr>
	<th >제목</th><td align="center" colspan="5"><input type="text" id="bbstitle" value="${bbsdto.title }" size="85"></td>
</tr>
<tr>
	<th colspan="6">내용</th>
</tr>
<tr>
	<td colspan="6"><textarea rows="10" cols="100" id="bbscontent"> ${bbsdto.content }</textarea></td>
</tr>
</table>
<button type="button" id="revice_btn">수정</button>
<button type="button" id="allbbslist_btn">목록</button>

<script type="text/javascript">
$("#revice_btn").click(function(){
	let update={
			seq:${bbsdto.seq},
			title:$("#bbstitle").val(),
			content:$("#bbscontent").val()
			};
	$.ajax({
		url:"./updatebbs.do",
		type:"get",
		datatype:"json",
		data:update,
		success:function(data){
			if(data==true){
				alert('글이 수정 되었습니다');
				location.href="./bbslist.do";
			}else{
				alert('수정 실패');
			}
		},
		error:function(){
			alert("error");
		}
				
	});
});
</script>

</body>
</html>
```
### - 글 수정 페이지에서 수정 버튼을 누르면 실행되는 컨트롤 생성
```java
@ResponseBody
@RequestMapping(value = "updatebbs.do", method = RequestMethod.GET)
public boolean updatebbs(BbsDto bbs) {
	boolean isS = bbsService.updatebbs(bbs);
	return isS;
}
```


# AOP 추가
- 디버깅을 위해 깃발을 꽂는 역할로 우선 AOP를 설정
- Web.xml에서 AOP를 설정하는 xml을 읽도록 경로를 적어준다.
```xml
...

<init-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>
		/WEB-INF/spring/servlet-context.xml <!-- 우리가 사용할 view의 확장자와 경로를 셋팅해주는 것 -->
		/WEB-INF/spring/aop-context.xml <!-- aop-context 읽게 했음 --> <!-- 기존에서 추가되는 경로 -->
	</param-value>
</init-param>

...

```
- /WEB-INF/spring 에 aop-context.xml을 생성해준다. (Annotation 방식으로 진행)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd">

	<!-- AOP 설정 어노테이션 방식 -->
	<aop:aspectj-autoproxy/>
	
	<!-- AOP 해당 클래스를 생성 -->
	<bean id="myAspect" class="bit.com.spring.aop.LogAop"/> <!-- class 경로 부분의 src에 만들어둔 클래스를 적어준다 -->
</beans>
```
- AOP 방식 설정시 생성하는 클래스를 src에 생성해준다.
```java
package bit.com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect	//어노테이션으로 aop를 사용하기위해 클래스에 설정
public class LogAop {

	
	// within 안에 경로의 *(모든파일)에 AOP설정, or을 통해 여러 경로 지정
	@Around("within(bit.com.spring.*) or within(bit.com.spring.dao.impl.*)") //아까 xml의 around이며 실행할 callback함수에 붙여준다
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();
		
		try {
			System.out.println("loggerAop:"+signatureStr+" 메소드가 실행");
			Object obj = joinpoint.proceed(); //지정 클래스의 어떠한 메소드가 실행 시
			
			return obj;
		}
	}
}
```
