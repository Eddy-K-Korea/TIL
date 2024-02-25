<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
	<td style="text-align: left;">${pds.title }</td>
</tr>

<tr>
	<th>다운로드</th>
	<td style="text-align: left;">
		<input type="button" name="btnDown" value="다운로드"
			onclick="filedowns('${pds.filename}', '${pds.seq }')">
	</td>
</tr>

<tr>
	<th>조회수</th>
	<td style="text-align: left;">${pds.readcount }</td>
</tr>

<tr>
	<th>다운수</th>
	<td style="text-align: left;">${pds.downcount }</td>
</tr>

<tr>
	<th>파일명</th>
	<td style="text-align: left;">${pds.filename }</td>
</tr>

<tr>
	<th>등록일</th>
	<td style="text-align: left;">${pds.regdate }</td>
</tr>

<tr>
	<th>내용</th>
	<td style="text-align: left;">
		<textarea rows="10" cols="50">${pds.content }</textarea>
	</td>
</tr>
</table>

<!-- 수정하기 -->
<c:if test="${login.id eq pds.id }">
<div id="buttons_wrap">
	<span class="button blue">
		<button type="button" id="_btnUpdate">수정하기</button>
	</span>
</div>
</c:if>

<!-- seq만 필요하므로 -->
<form name="frmForm" id="_frmForm" action="pdsupdate.do" method="post">
	<input type="hidden" name="seq" value="${pds.seq }">
</form>


<script>
function filedowns(filename, seq){
	location.href = "fileDownload.do?filename=" + filename + "&seq=" + seq;	
}	

$("#_btnUpdate").click(function () {
	$("#_frmForm").submit();
});

</script> 
