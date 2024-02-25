<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table class="list_table" style="width: 70%">
<col width="50"><col width="300">
<tr>
	<th>작성자</th><th style="background: white; color: black">${caldto.id }</th>
</tr>
<tr>
	<th>일정 시간</th><th style="background: white; color: black">${year}년${month}월 ${day}일 ${hour}시 ${min}분</th>
</tr>
<tr>
	<th>일정 제목</th><td align="center">${caldto.title }</td>
</tr>	
<tr>
	<th colspan="2">내용</th>
</tr>
<tr>
	<td colspan="2">${caldto.content }</td>
</tr>
<tr>
	<td colspan="2" align="center">
	<span class="button blue" style="margin-top:15px; margin-left: auto; margin-right: auto">
	<button type="button" id="update_cal" >수정</button>
	</span>
	<span class="button blue" style="margin-top:15px; margin-left: auto; margin-right: auto">
	<button type="button" id="delete_cal">삭제</button>
	</span>
	</td>

</tr>
</table>