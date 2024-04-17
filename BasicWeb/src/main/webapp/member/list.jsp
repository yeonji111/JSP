
<%@page import="board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원 목록" name="title"/>
</jsp:include>
<%
	BoardVO board1 = new BoardVO();
	board1.setTitle("첫번째 글");
%>
<!-- jsp:useBean을 사용하여 new BoardVO처럼 새 BoardVO 객체를 만들고 setProperty를 사용여 값을 넣기 -->
	<jsp:useBean id="board" class="board.BoardVO" scope="request"></jsp:useBean>
	<jsp:setProperty property="title" name="board" value="첫번째 글"/>
	<h1>회원 목록</h1>
	<a href="/member/add"> 신규 회원 </a>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>가입일자</th>
		</tr>

		<c:forEach var="member" items="${members}">
			<tr>
				<td><a href="/member/view?id=${member.id}">${member.id}</a></td>
				<td><a href="/member/view?id=${member.id}">${member.name}</a></td>
				<td>${member.email}</td>
				<td>${member.createDate}</td>
			</tr>
		</c:forEach>
	</table>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>