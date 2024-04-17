<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판 목록" name="title"/>
</jsp:include>
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
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>