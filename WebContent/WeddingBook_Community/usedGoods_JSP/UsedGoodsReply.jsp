<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.weddingbook.www.dto.MemberDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Community/usedGoods_CSS/UsedGoodsReply.css">
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
	<!-- 답글쓰기 -->
	<c:if test="${memberDto.id == null }">
		<script>
			alert("답변을 작성하려면 로그인을 해주세요.");
			location.href="../../WeddingBook_Membership/JSP/LoginMain.do";
		</script>
	</c:if>
	<form action="UsedGoodsReplyOk.do" method="post">
		<h1>답변쓰기</h1>
		<table id="replyTable">
			<tr>
				<td class="reply">이름</td>
				<td><input type="text" name="nickName" class="replyIn" value="${memberDto.nickName}" readonly></td>
			</tr>
			<tr>
				<td class="reply"><label for="title">제목</label></td>
				<td><input type="text" name="title" id="title" class="replyIn" value="re: ${usedDto.title}"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="15" cols="100" name="contents">&#10&#13re: ${usedDto.contents}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" id="btn"><input type="submit" value="등록"><a href="UsedList.do">목록</a></td>
			</tr>
		</table>
		<input type="hidden" name="no" value="${usedDto.no}">
		<input type="hidden" name="groupNum" value="${usedDto.groupNum}">
		<input type="hidden" name="stepNum" value="${usedDto.stepNum}">
		<input type="hidden" name="indentNum" value="${usedDto.indentNum}">
	</form>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>







