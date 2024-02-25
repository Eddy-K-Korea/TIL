<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="frm">
<input type="hidden" value="${bbs.seq }" name="seq">
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
	<th>제목</th><td colspan="5"><input type="text"value="${bbs.title }" style="width:762px" id="_title" name="title"></td>
</tr>
<tr>
	<th>내용</th><td colspan="5"><textarea rows="10" cols="100" style="width: 762px" id="_content" name="content">${bbs.content }</textarea></td>
</tr>

</table>
</form>
<span class="button blue" style="margin-left: auto; margin-right: auto"><button type="button" id="bbsupdate_btn">수정완료</button></span>

<script>
$(document).ready(function(){
	$("#_title").focus();
});
$("#bbsupdate_btn").click(function(){
	$("#frm").attr("action","bbsupdateAf.do").submit();
});
</script>