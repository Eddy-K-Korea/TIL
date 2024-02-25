<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberDao dao = MemberDao.getInstance();

String id = request.getParameter("id");
//System.out.println(id);
String check = "";
boolean findId = dao.getId(id);
if(!findId){
	check = "YES";	
}
else{
	check = "NO";
}

out.println(check);



%>