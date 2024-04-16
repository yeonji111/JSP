<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<style>
	label{
		display:block;
	}
</style>
</head>
<body>
	<h1>회원 정보</h1>
		<form action = "/member/update" method ="post">
		
			<label>ID: 
				<input type="text" name="id" readonly="readonly" value ="${member.id}">
			</label>
			
			<label>이름: 
				<input type="text" name="name" value ="${member.name}">
			</label>
			<label>비밀번호: 
				<input type="password" name="password" value ="${member.password}">
			</label>
			<label>이메일: 
				<input type="email" name="email"  value ="${member.email}">
			</label>
			<button type = "submit">저장</button>
			<button type = "button">취소</button>
			<button type = "submit">수정</button>
		</form>
</body>
</html>