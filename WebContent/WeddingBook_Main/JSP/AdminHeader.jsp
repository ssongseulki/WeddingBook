<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Main/CSS/AdminHeader.css" >
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
</head>
<body>
	<div id="mainTitle">
		<header id="header">
			<nav id="nav_gnb">
				<ul>
					<c:choose>
						<c:when test="${memberDto.id == 'adminssong'}">
							<li><p>관리자입니다</p></li>
							<li><a href="../../WeddingBook_Main/JSP/Main.jsp">WeddingBook</a></li>
							<li><a href="../../WeddingBook_Membership/JSP/Logout.do" onclick="logout();">로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="../../WeddingBook_Main/JSP/AdminLogin.do">로그인</a></li>
						</c:otherwise>
					</c:choose>				
				</ul>
			</nav>
			<nav id="nav_lnb">
				<ul>
					<li><a href="../../WeddingBook_Main/JSP/AdminMain.jsp"><h2>WeddingBook관리자</h2></a></li>
					<li class="dropdown">
						<a href="#" class="dropbtn">회원관리</a>
						<div class="dropdown-contents">
							<a href="../../AdminMember/JSP/AdminMemberList.do">회원정보</a>
							<a href="../../AdminMember/JSP/AdminReservationList.do">예약관리</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="#" class="dropbtn">업체관리</a>
						<div class="dropdown-contents">
							<a href="../../AdminGoods/JSP/AdminBusinessList.do">업체정보</a>
							<a href="../../AdminGoods/JSP/AdminGoodsWrite.jsp">업체등록</a>
						</div>
					</li>
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