<%@page import="dao.CustUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String delck[] = request.getParameterValues("delck"); // 여러개의 체크된 박스가 배열로 넘어옴
CustUserDao dao = CustUserDao.getInstance();
boolean isS= true;
 if(delck != null){
	/* for(int i = 0; i < delck.length; i++){
		System.out.println(delck[i]);
		dao.deleteCustUser(delck[i]); //DB에서 삭제
	} dao에서의 쿼리문이 한개의 데이터를 삭제하도록 설정시 for문으로 여러개 처리*/
	 isS = dao.deleteCustUsers(delck);	// dao의 쿼리문 자체가 배열로 받아 한꺼번에 처리 (dao DB쿼리 확인 必)
} 
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>muldel.jsp</title>
</head>
<body>
<%
if(isS == true){
%>	
	<script type="text/javascript">
	alert('삭제 되었습니다.');
	location.href = "custuserlist.jsp";
	</script>
<%
}else{
	%>
<script type="text/javascript">
	alert('삭제 실패');
	location.href = "custuserlist.jsp";
	</script>
<%
}
%>

</body>
</html>
