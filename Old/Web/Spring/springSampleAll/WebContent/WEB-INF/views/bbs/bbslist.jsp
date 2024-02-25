<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- <%
String choice = (String)request.getAttribute("choice");
if(choice == null){
	choice = "";
}

String searchWord = (String)request.getAttribute("searchWord");
if(searchWord == null){
	searchWord = "";
}
%> --%>

<!-- 검색을 한 후 다른 페이지로 넘어 갈때 choice와 검색어를 가지고 넘어가기 때문에 해당 값을 유지 시켜주어야한다. 따라서 컨트럴에서 넘어올때 해당 값들을 셋팅해준다 -->
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

<!-- 검색 -->
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

<!-- 추가 기입 -->
<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber }"> <!-- 처음 현재 페이지를 받아주거나 아래 페이징에 선택된 페이지값을 받는다 -->
<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage" value="${(empty recordCountPerPage)?0:recordCountPerPage }"> <!-- 한 페이지에 몇페이지를 보여줄지 값을 넘겨 받는다 -->
</form>

</div>


<!-- arrow class 생성 -->
<jsp:useBean id="ubbs" class="bit.com.spring.util.BbsArrow"/>

<table class="list_table" style="width: 85%">

<colgroup>
	<col style="width:70px">	<!-- 번호 -->
	<col style="width:auto">	
	<col style="width:100px">	<!-- 작성자 -->
</colgroup>

<thead>
	<tr>
		<th>번호</th><th>제목</th><th>작성자</th>
	</tr>
</thead>
<tbody>
	<c:if test="${empty bbslist }">
	<tr>
		<td colspan="3">작성된 글이 없습니다</td>
	</tr>
	</c:if>
	
	<c:forEach items="${bbslist }" var="bbs" varStatus="vs">
		<!-- arrow를 setting -->
		<jsp:setProperty property="depth" name="ubbs" value="${bbs.depth }"/> <!-- property는 변수명(java에 있는), name은 위에 생성한 util -->
	
		<tr class="_hover_tr">
			<td>${vs.count }</td>
			<td style="text-align: left;">
				<!-- arrow를 출력 -->
				<jsp:getProperty property="arrow" name="ubbs"/>
				
				<c:if test="${bbs.del eq 1 }">
					***이 글은 관리자에 의해서 삭제 되었습니다***
				</c:if>
				<c:if test="${bbs.del eq 0 }">
					<a href="bbsdetail.do?seq=${bbs.seq }">${bbs.title }</a>
				</c:if>
			</td>
			<td>
				${bbs.id }
			</td>
		</tr>
	</c:forEach>
</tbody>

</table>

<br>

<!-- paging -->	<!-- 만들어둔 paging.jsp 를 불러온다 / 변수 에 jsp:param을 통해 값을 넣어준다 -->
<div id="paging_wrap">
	<jsp:include page="/WEB-INF/views/bbs/paging.jsp" flush="false">
		<jsp:param name="totalRecordCount" value="${totalRecordCount }"/>
		<jsp:param name="pageNumber" value="${pageNumber }"/>
		<jsp:param name="pageCountPerScreen" value="${pageCountPerScreen }"/>
		<jsp:param name="recordCountPerPage" value="${recordCountPerPage }"/>	
	</jsp:include>
</div>

<script>
function goPage(pageNumber){	//paging.jsp의 코드중에 goPage의 JS를 생성
	$("#_pageNumber").val(pageNumber);	//현재 페이지의 값을 form 태그가 있는곳에 다시 넣어준다(현재 페이지 지정된 페이지로 값을 넘기기위해).
	$("#_frmFormSearch").attr("action","bbslist.do").submit();	//위의 현재 페이지의 값을 받은 hidden값과 몇페이지를 보여줄지 recordCountPerPage 을 넘겨준다(row넘버를 위해)
}

$("#btnSearch").click(function(){
	$("#_frmFormSearch").attr("action","bbslist.do").submit();
});

</script>