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
<title>WeddingBook 글작성</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Community/usedGoods_CSS/UsedGoodsWrite.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/Header.jsp"%>
	<!-- 사이드 메뉴 -->
	<nav id="articleCommunityMenu">
		<h1>중고거래</h1>
		<ul>
			<li><a href="../../WeddingBook_Community/board_JSP/BoardList.do">결혼준비</a></li>
		</ul>
	</nav>
	
	<!-- 글쓰기 -->
	<c:if test="${memberDto.id == null}">
		<script>
			alert("글을 작성하려면 로그인이 필요합니다.")
			location.href="../../WeddingBook_Membership/JSP/LoginMain.do";
		</script>
	</c:if>
	<form action="UsedGoodsWriteOk.do" method="post">
		<h1>글쓰기</h1>
		<table	id="writeTable">
			<tr>
				<td class="write">이름</td>
				<td><input type="text" class="writeIn" name="nickName" value="${memberDto.nickName}" readonly></td>
			</tr>
			<tr>
				<td class="write"><label for="title">제목</label></td>
				<td><input type="text" name="title" id="title" class="writeIn" required></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="contents" id="contents" required></textarea></td>
			</tr>
		</table>
		<input type="submit" value="입력">
	</form>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>






