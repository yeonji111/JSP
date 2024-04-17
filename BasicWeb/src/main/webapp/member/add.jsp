<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원 추가" name="title"/>
</jsp:include>
	<h1>회원 정보</h1>
		<form action = "/member/add" method ="post">
			<label>ID: 
				<input type="text" name="id">
			</label>
			
			<label>이름: 
				<input type="text" name="name">
			</label>
			<label>비밀번호: 
				<input type="password" name="password">
			</label>
			<label>이메일: 
				<input type="email" name="email">
			</label>
			<button type = "submit">저장</button>
			<button type = "button">취소</button>
		</form>
</body>
</html>