<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ID중복체크</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Membership/CSS/IdCheck.css">
</head>
<body>
	<h3>아이디 중복 체크</h3>
	<form action="IdCheckOk.jsp" method="post" onsubmit="return blankAdminCheck(this)">
		<span>아이디 : </span>
		<input type="text" name="id" maxlength="20" autofocus>
		<input type="submit" value="중복확인">
	</form>
	
	<script>
		function blankAdminCheck(f) 
		{
			var id = f.id.value;
			id = id.trim();
			if(id.length < 5)
			{
				alert("아이디는 5자 이상 입력하세요.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>