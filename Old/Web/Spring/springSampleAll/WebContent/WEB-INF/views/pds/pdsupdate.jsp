<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<form name="frmForm" id="_frmForm" action="pdsupdateAf.do" method="post" enctype="multipart/form-data">

<input type="hidden" name="seq" value="${pds.seq }">

<table class="list_table" style="width: 85%">
<colgroup>
	<col width="200"><col width="500">
</colgroup>
<tr>
	<th>아이디</th>
	<td style="text-align: left;">${pds.id }</td> 
</tr>
<tr>
	<th>제목</th>
	<td style="text-align: left;">
		<input type="text" name="title" size="50" value="${pds.title }">
	</td>
</tr>

<tr>
	<th>파일 업로드</th>
	<td style="text-align: left;">
		<!-- 기존의 파일 -->
		<input type="text" name="namefile" value="${pds.filename }" size="50" readonly="readonly">
		<!-- 수정할 파일 -->
		<input type="file" name="fileload" style="width: 400px">
	</td>
</tr>
<tr>
	<th>내용</th>
	<td style="text-align: left;">
		<textarea rows="10" cols="50" name="content" id="_content">${pds.content }</textarea>
	</td>
</tr>
<tr>
	<td colspan="2" style="height: 50px; text-align: center;">
		<span class="button blue"><a href="#none" id="btnupdate" title="수정완료">수정하기</a></span>
	</td>
</tr>


</table>


</form>

<script>
$("#btnupdate").click(function(){
	$("#_frmForm").submit();
});

</script>