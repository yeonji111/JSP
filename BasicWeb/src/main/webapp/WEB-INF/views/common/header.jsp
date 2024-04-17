<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title}</title>
<style>
	label{
		display:block;
	}
	header form {
	text-align: right;
	padding-right: 100px;
}
</style>
</head>
<body>
<header>
<c:choose>
	<c:when test="${not empty sessionScope.member}">			<!-- when = if // not empty member 와 (member != null),(member ne null) 과 동일 -->
		<form action="/logout" method="get" id = "welcome">
			<span id="loginName">${sessionScope.member.name}님 환영합니다 </span>
			<button type="submit">로그아웃</button>
		</form>
	</c:when>
	
	<c:otherwise>	<!-- otherwise = else -->
		<form action="/login" method="get" id = "login">
			<button type="submit">로그인</button>
		</form>
	</c:otherwise>
</c:choose>
</header>
