<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>

<table>
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일자</th>
		<th>조회수</th>
	</tr>
	
	<c:forEach var="board" items="${boards}">
	<tr>
		<th>${board.no}</th>
		<th>${board.title}</th>
		<th>${board.writer}</th>
		<th>${board.createDate}</th>
		<th>${board.hits}</th>
	</tr>
	</c:forEach>
</table>
</body>
</html>