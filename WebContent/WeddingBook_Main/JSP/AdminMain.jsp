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
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Main/CSS/AdminMain.css" ></head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/AdminHeader.jsp" %>
	<div class="main"> 
		<a href="../../AdminMember/JSP/AdminMemberList.do" class="mainBtn">회원정보</a>
		<a href="../../AdminGoods/JSP/AdminBusinessList.do" class="mainBtn">업체정보</a>
	</div>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>