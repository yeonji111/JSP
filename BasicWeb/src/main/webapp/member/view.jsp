<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 화면</title>
</head>
<body>
<!-- id=a001&name = miso& ... 으로 queryString 구성가능 -->
<a href = "/member/update?id=${member.id}">수정</a>
<a href = "javascript:deleteMember();">삭제</a>
<a href = "/member/update/password?id=${member.id}">비밀번호 수정</a>
<div>
	ID: ${member.id}
</div>
<div>
	이름: ${member.name}
</div>
<!-- <div>패스워드: ${member.password}</div> -->
<div>
	이메일: ${member.email}
</div>

<script>
	function deleteMember(){
		if (confirm("삭제하시겠습니까?")){
			location.href = "/member/delete?id=${member.id}"
		} else {
			alet("삭제를 취소하셨습니다.");
		}
	}
</script>

</body>
</html>