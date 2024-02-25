<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<c:if test="${empty choice}">
	<c:set var="choice" value=""/>
</c:if>
<c:if test="${empty searchWord }">
	<c:set var="searchWord" value=""/>
</c:if>
<script>
let choice = "${choice}";
let searchWord = "${searchWord}";

$(document).ready(function(){
	$("#_choice").val(choice);
	//$("#_searchWord").val(searchWord);
	document.frmForm1.searchWord.value = searchWord;
});

</script>
<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">

<form name= "frmForm1" id="_frmFormSearch" method="get">
<table style="margin-left: auto; margin-right: auto; margin-top: 3px; margin-bottom: 3px">
<tr>
	<td>검색</td>
	<td style="padding-left: 5px">
		<select id="_choice" name="choice">
			<option value="" selected="selected">선택</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="id">작성자</option>
		</select>
	</td>
	<td style="padding-left: 5px">
		<input type="text" id="_searchWord" name="searchWord">
	</td>
	<td style="padding-left: 5px">
		<span class="button blue">
			<button type="button" id="btnSearch">검색</button>
		</span>
	</td>
</tr>
</table>
</form>
</div>

<table class="list_table" style="width: 85%">
<colgroup>
	<col width="50"><col width="100"><col width="300"><col width="60">
	<col width="50"><col width="50"><col width="100"><col width="50">
</colgroup>
<thead>
<tr>
	<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
	<th>조회수</th><th>다운수</th><th>작성일</th><th>삭제</th>
</tr>
</thead>

<tbody>

<c:forEach var="pds" items="${pdslist}" varStatus="vs">
<tr>
	<th>${vs.count }</th>
	<td>${pds.id }</td>
	<td style="text-align: left;"><a href="pdsdetail.do?seq=${pds.seq }">${pds.title }</a></td>
	<td><input type="button" name="btnDown" value="다운로드" onclick="filedown('${pds.filename}','${pds.seq }')"></td>
	<td>${pds.readcount }</td>
	<td>${pds.downcount }</td>
	<td><font size='1'>${pds.regdate }</font></td>
	<td><img alt="" src="image/del.png" data_file_seq ="${pds.seq }" class="btn_fileDelete"></td>
</tr>
</c:forEach>	

</tbody>
</table>

<!-- 추가버튼 -->
<div id="button.wrap">
	<span class="button blue">
		<button type="button" id="_btnAdd">자료추가</button>
	</span>
</div>

<!-- 다운로드 버튼을 클릭시 설정  post로 날려주기위해 한번 더 묶어준다-->
<form name="file_Down" action="fileDownload.do" method="post">
	<input type="hidden" name="filename">
	<input type="hidden" name="seq">
</form>
<!-- 삭제를 클릭 했을때 -->

<script>
$("#_btnAdd").click(function(){
	location.href="pdswrite.do";
});

function filedown(filename, seq){
let doc = document.file_Down; //위의 form을 가져온다
doc.filename.value = filename; //위의 filename을 post 방식의 form의 묶음안에 다시 장착시켜준다
doc.seq.value = seq; //post방식안에 히든에 다시 장착
doc.submit();	//post로 변경한 값들은 컨트롤러로 보내준다
	
}


$(".btn_fileDelete").click(function(){
	alert("delete");
});

$("#btnSearch").click(function(){
	$("#_frmFormSearch").attr("action","pdslist.do").submit();
});

</script>


