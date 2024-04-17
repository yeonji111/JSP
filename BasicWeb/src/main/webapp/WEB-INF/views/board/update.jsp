<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<style>
	label{
		display:block;
	}
</style>
</head> 
<body>
	<c:forEach var="board" items="${board}">
<form action="/board/update" method="POST">
<!-- 글번호 -->
	글번호<input type="text" name="no" value = "${board.no}" readonly="readonly">
<!-- 작성자 -->
	작성자 <input type="text" name="writer" value="${board.writer}">
<!-- 글제목 --> 
	<label>제목 
		<input type="text" name="title" value = "${board.title}">
	</label>
<!-- 글내용 -->
	<label>내용 
		<textarea rows="10" cols="40" name="content" placeholder= "${board.content}"></textarea>
	</label>

	<div style="color:red;"> ${msg} </div>
	<button type="submit">수정</button>
	<button type="button">취소</button>
</form>
</c:forEach>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>