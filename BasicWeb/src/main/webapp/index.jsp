<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!-- Scriptlet 스크립틀릿 <% %> -->
<%
	LocalDateTime now = LocalDateTime.now();
%>
<!-- 표현식(Expression)-->
현재시각:<%=now%> <br>
현재시각:<%= LocalDateTime.now()%> 


</body>
</html>