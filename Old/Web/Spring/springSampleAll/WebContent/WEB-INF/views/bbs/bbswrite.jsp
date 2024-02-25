<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form id="_frmForm">
<table class="list_table" style="width: 85%">
<colgroup>
	<col style="width:70px">	
	<col style="width:auto">	
</colgroup>

<tr>
	<th>작성자</th><td style="text-align: left;"><input type="text" id="_id" name="id" value="${login.id }" readonly="readonly"style="margin: 0px; width: 758px"></td>
</tr>
<tr>
	<th>제목</th><td><input type="text" id="_title" name="title" style="margin: 0px; width: 758px"></td>
</tr>
<tr>
	<th>내용</th><td><textarea rows="15" cols="100px" id="_content" name="content" style="margin: 0px; width: 758px; height: 231px;"></textarea> </td>
</tr>

</table>
</form>
<div style="margin-top: 20px; margin-bottom: 10px">
	<span class="button blue" style="margin-left: auto; margin-right: auto">
	<button type="button" id="bbswrite_btn">등록</button>
	</span>
</div>

<script>
$(document).ready(function(){
	$("#bbswrite_btn").click(function(){
		$("#_frmForm").attr("action","bbswriteAf.do").submit();
	});
});

</script>
