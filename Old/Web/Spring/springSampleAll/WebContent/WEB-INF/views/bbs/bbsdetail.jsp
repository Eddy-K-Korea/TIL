<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<table class="list_table" style="width: 85%">
<colgroup>
	<col style="width:70px">	
	<col style="width:auto">
	<col style="width: 60px">
	<col style="width: 90px">
	<col style="width: 60px">
	<col style="width: 30px">
</colgroup>

<tr>
	<th>작성자</th><td><input type="text" id="_id" name="id" value="${login.id }" readonly="readonly" style="width:522px"></td><th>작성일</th><td>${bbs.wdate }</td><th>조회수</th><td align="center">${bbs.readcount }</td>
</tr>
<tr>
	<th>제목</th><td colspan="5"><input type="text" readonly="readonly" value="${bbs.title }" style="width:762px"></td>
</tr>
<tr>
	<th>내용</th><td colspan="5"><textarea rows="10" cols="100" style="width: 762px" readonly="readonly">${bbs.content }</textarea></td>
</tr>

</table>

<c:if test="${login.id eq bbs.id }">
<div style="margin-top: 20px; margin-bottom: 10px">
	<table style="margin-left: auto; margin-right: auto; margin-top: 3px; margin-bottom: 3px">
	<tr>
		<td style="padding-left: 5px"><span class="button blue" style="margin-left: auto; margin-right: auto"><button type="button" id="bbsupdate_btn">수정</button></span></td>
		<td style="padding-left: 5px"><span class="button blue" style="margin-left: auto; margin-right: auto"><button type="button" id="bbsremove_btn">삭제</button></span></td>
	</tr>
	</table>
</div>
</c:if>

<form id="replyForm">
<table class="list_table" style="width: 40%"> 
<colgroup>
	<col style="width:70px">
	<col style="width:300px">
</colgroup>
	<tr>
		<th colspan="2">댓글 작성<input type="hidden" value="${login.id }" name="id"><input type="hidden" value="${bbs.seq }" name="seq"> </th>
	</tr>
	<tr>
		<th>제목</th><td><input type="text" style="width:300px" id="replyTitle" name="title"></td>
	</tr>
	<tr>
		<th>내용</th><td><textarea rows="10" cols="" style="width: 300px" id="replyContent" name="content"></textarea> </td>
	</tr>

</table>
</form>
<div style="margin-top: 20px; margin-bottom: 10px">
	<span class="button blue" style="margin-left: auto; margin-right: auto"><button type="button" id="bbsreply_btn">댓글 작성</button></span>
</div>

<script>
$("#bbsreply_btn").click(function(){
	$("#replyForm").attr("action","bbsreply.do").submit();
});

$("#bbsupdate_btn").click(function(){
	location.href="bbsupdate.do?seq=${bbs.seq}";
});

$("#bbsremove_btn").click(function(){
	location.href="bbsdelete.do?seq=${bbs.seq}";
});
</script>