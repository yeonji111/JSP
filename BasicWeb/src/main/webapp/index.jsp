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
	<!-- Scriptlet ��ũ��Ʋ�� <% %> -->
<%
	LocalDateTime now = LocalDateTime.now();
%>
<!-- ǥ����(Expression)-->
����ð�:<%=now%> <br>
����ð�:<%= LocalDateTime.now()%> 


</body>
</html>