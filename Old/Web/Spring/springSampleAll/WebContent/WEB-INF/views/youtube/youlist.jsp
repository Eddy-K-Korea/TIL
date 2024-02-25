<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
    
<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">
<form id="frmForm" method="post">
<table style="margin-left: auto; margin-right: auto; margin-top: 3px; margin-bottom: 3px;">
<tr>
	<td>검색:</td>
	<td style="padding-left:5px">
		<input type="text" id="s_keyword" name="s_keyword" value="${empty s_keyword?'':s_keyword }">
	</td>

	<td style="padding-left: 5px">
		<span class="button blue">
			<button type="button" id="_btnSearch">검색</button>
		</span>
	</td>
	
</tr>

</table>
</form>
</div>

<div id="_youtube_">
	<iframe id="_youtube" width="640" height="360" src="http://www.youtube.com/embed/" allowfullscreen frameborder="0">
	
	</iframe>

</div>

<table class="list_table" style="width: 85%">
<colgroup>
	<col style="width: 70px"><col style="width: auto"><col style="width: 100px"><col style="width: 30px">
</colgroup>

<thead>
	<tr>
		<th>번호</th><th>제목</th><th>유뷰트 고유 번호</th><th>저장</th>
	</tr>

</thead>

<tbody>
	<c:if test="${empty yulist }">
	<tr>
		<td colspan="4">작성된 목록이 없습니다</tr>
	</c:if>
	
	<c:forEach items="${yulist }" var="you" varStatus="vs">
		<tr class="_hover_tr">
			<td>${vs.count }</td>
			<td style="text-align: left;">
				<div class="c_vname" vname="${you.url }">${you.title }</div>
			</td>
			<td>${you.url }</td>
			<td>
				<img alt="" src="image/save.png" class="ck_seq" id="${you.url }" loginIn="${login.id }" title="${you.title }">
			</td>
		</tr>
	</c:forEach>

</tbody>
</table>

<script>
$(document).ready(function(){
	$("#_youtube_").hide();

	$("._hover_tr").mousemove(function() {
		$(this).children().css("background-color","#f0f5ff");
	}).mouseout(function() {
		$(this).children().css("background-color","#ffffff");
	});
});

$("#_btnSearch").click(function(){
	$("#frmForm").attr('action',"youtube.do").submit();
	
});

$(".c_vname").click(function(){
	$("#_youtube_").show();

	$("#_youtube").attr("src","http://www.youtube.com/embed/"+$(this).attr("vname"));
});

$('.ck_seq').click(function(){

	let id = $(this).attr("loginIn");
	let title = $(this).attr("title");
	let url = $(this).attr("id");

	//alert(url);

	$.ajax({
		url:"./youtubesave.do",
		type:'post',
		async:true,	//비동기설정
		data:{"id":id,"title":title,"url":url},
		success:function(msg){
			alert(msg);
		},
		error:function(){
			alert("error");
		}


	});
	
});

</script>













