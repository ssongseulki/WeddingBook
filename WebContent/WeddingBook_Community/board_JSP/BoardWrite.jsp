<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WeddingBook 커뮤니티</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Community/board_CSS/BoardWrite.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/Header.jsp"%>
	<!-- 사이드 메뉴 -->
	<nav id="articleCommunityMenu">
		<h1>결혼준비</h1>
		<ul>
			<li><a href="#">중고거래</a></li>
		</ul>
	</nav>
	
	<!-- 글쓰기 -->
	<form action="BoardWriteOk.do" method="post">
		<h1>글쓰기</h1>
		<table id="writeTable">
			<tr>
				<td class="write">이름</td>
				<td><input type="text" class="writeIn" name="nickName" value="${memberDto.nickName}" readonly/></td>
				<td class="write">예식일</td>
				<td><input type="text" class="writeIn" id="weddingDate" name="weddingDate" value="${memberDto.weddingDate}" readonly/>결혼</td>
			</tr>
			<tr>
				<td colspan="4"><textarea rows="15" cols="100" name="contents" placeholder="내용을 입력하세요."></textarea></td>
			</tr>
		</table>
		<input type="submit" value="등록">
		<a href="BoardList.do">목록</a>
	</form>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>