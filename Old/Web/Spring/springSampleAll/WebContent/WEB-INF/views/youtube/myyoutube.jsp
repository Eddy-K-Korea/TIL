<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     

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
		<th>번호</th><th>제목</th><th>유뷰트 고유 번호</th><th>삭제</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${myVideos }" var="you" varStatus="vs">
		<tr class="_hover_tr">
			<td>${vs.count }</td><td><span class="viedo_title" vurl="${you.url }">${you.title }</span></td><td>${you.url }</td>
			<td>
				<img alt="" src="image/del.png" class="ck_seq" id="${you.seq }">
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

$(".viedo_title").click(function(){
	let url = $(this).attr("vurl");
	//alert(url);
	$("#_youtube_").show();

	$("#_youtube").attr("src","http://www.youtube.com/embed/"+url);
});

$(".ck_seq").click(function(){
	let seq = $(this).attr("id");

	$.ajax({
		url:"./removevideo.do",
		type:"post",
		data:{"seq":seq},
		success:function(msg){
			alert(msg);
			location.href="myyoutube.do";
		},
		error:function(){
			alert("error");
		}
		
	});
});

</script>