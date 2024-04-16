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
	<h1>회원 정보 수정 (비밀번호) </h1>
		<form action = "/member/update/password" method ="post">
			<label>ID: 
				<input type="text" name="id" readonly="readonly" value ="${member.id}">
			</label>
			
			<label>이름: 
				<input type="text" name="name" value ="${member.name}">
			</label>
			<label>비밀번호: 
				<input type="password" name="password" value ="${member.password}">
			</label>
            <label>새비밀번호: 
				<input type="password" name="newPassword" value ="">
			</label>
            <label>비밀번호 재확인: 
				<input type="password" name="confirmPassword" value ="">
			</label>
			<label>이메일: 
				<input type="email" name="email"  value ="${member.email}">
			</label>
			<button type = "submit">변경</button>
			<a href = "/member/list"><button type = "button">취소</button></a>
		</form>
		
		
		
</body>
</html>