<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.weddingbook.www.dto.MemberDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Membership/CSS/LoginMain.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/AdminHeader.jsp" %>
	<!-- 사이드 메뉴 -->
	<div id="div_whole">
		<article id="article_login_menu">
			<h1>로그인</h1>
			<ul>
				<li><a href="MemberRegister.do">회원가입</a></li>
			</ul>
		</article>
	
	<!-- 로그인 메뉴 -->
		<c:if test="${memberDto eq null }">
			<form action="AdminLoginOk.do" method="post" onsubmit="return loginChk()" name="login">
				<table id="article_login">
					<tr>
						<td><lable for="id">아이디 : </lable></td>
						<td><input type="text" name="id" id="id"></td>
					</tr>
					<tr>
						<td><label for="password">비밀번호 : </label></td>
						<td><input type="password" name="password" id="password"><br></td>
					</tr>
					<tr>
						<td colspan="2" id="btn"><input type="submit" value="로그인"><a href="MemberRegister.jsp">회원가입</a></td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
	
	<script>
		function loginChk() 
		{
			if(document.getElementById("id").value == "")
			{
				alert("아이디를 입력하세요.")
				login.id.focus();
				return false;
			}	
			else if(document.getElementById("password").value == "")
			{
				alert("비밀번호를 입력하세요.")
				login.password.focus();
				return false;
			}
			else
			{
				return true;
			}	
		}
	</script>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>	
</body>
</html>