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
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Community/board_CSS/BoardList.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/Header.jsp"%>
	<!-- 사이드 메뉴 -->
	<nav id="articleCommunityMenu">
		<h1>결혼준비</h1>
		<ul>
			<li><a href="../../WeddingBook_Community/usedGoods_JSP/UsedGoodsList.do">중고거래</a></li>
		</ul>
	</nav>
	
	<!-- 결혼준비 게시판 리스트 -->
	<a href="BoardWrite.do" id="writeBtn">글작성</a>
	<div id="whole">
		<c:forEach var="boardDto" items="${boardList}">
			<table id="list_table">
				<tr>
					<td id="no" colspan="2">No.${boardDto.no}</td>
					<td id="wTime" colspan="2">${boardDto.wTime}</td>
				</tr>
				<tr>
					<td id="nickName" colspan="2">${boardDto.nickName}</td>
					<td id="weddingDate" colspan="2">${boardDto.weddingDate}결혼</td>
				</tr>
				<tr>
					<td colspan="4"><textarea name="contents" disabled>${boardDto.contents}</textarea></td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="hidden" name="no" value="${boardDto.no}" />
						<a href="#" onclick="window.open('CommentPopup.do?no=${boardDto.no}', 
							'Comment', 'toolbar=yes, scrollbars=yes, resizable=yes, location=no, top=30, left=600, width=800, height=940')">
							${commentDto.commentCnt}<span class="material-icons">chat_bubble_outline</span>
						</a>
					</td>	
				</tr>
			</table>
		</c:forEach>
	</div>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>





