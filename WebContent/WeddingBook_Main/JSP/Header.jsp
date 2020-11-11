<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Wedding Book</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Main/CSS/Header.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
</head>
<body>
	<div id="mainTitle">
		<header id="header">
			<nav id="nav_gnb">
				<ul>
					<c:choose>
						<c:when test="${memberDto.id == null }">
							<li><a href="../../WeddingBook_Membership/JSP/MemberRegister.do">회원가입</a></li>
							<li><a href="../../WeddingBook_Membership/JSP/LoginMain.do">로그인</a></li>
						</c:when>
						<c:when test="${memberDto.id == 'adminssong'}">
							<li><p>관리자입니다.</p></li>
							<li><a href="../../WeddingBook_Main/JSP/AdminMain.jsp">관리자페이지</a></li>
							<li><a href="../../WeddingBook_Membership/JSP/Logout.do" onclick="logout();">로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li><p>${memberDto.nickName}님 환영합니다.</p></li>
							<li><a href="../../WeddingBook_Membership/JSP/MemberModify.do">회원정보수정</a></li>
							<li><a href="../../WeddingBook_Goods/JSP/GoodsReservationList.do?memberId=${reserDto.memberId}">나의 예약 현황</a></li>
							<li><a href="../../WeddingBook_Membership/JSP/Logout.do" onclick="logout();">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>		
			<nav id="nav_lnb">
				<ul>
					<li><a href="../../WeddingBook_Main/JSP/Main.jsp"><h2>WeddingBook</h2></a></li>
					<li><a href="../../WeddingBook_Goods/JSP/GoodsList.do">S.D.M Goods</a></li>
					<li><a href="../../WeddingBook_Community/board_JSP/BoardList.do">Community</a></li>
				</ul>
			</nav>
		</header>
	</div>
	<script>
		function logout() 
		{
			alert("로그아웃 되었습니다.")	
		}
	</script>
</body>
</html>