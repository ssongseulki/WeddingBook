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
<title>WeddingBook 커뮤니티</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Community/usedGoods_CSS/UsedGoodsView.css">
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
	
	<!-- UsedGoods View -->
	<form action="UsedGoodsList.do" method="post">
		<table id="viewTable">
			<tr>
				<td class="view1">번호</td>
				<td class="view1In">${usedDto.no}</td>
				<td class="view1">조회수</td>
				<td class="view1In">${usedDto.hit}</td>
			</tr>
			<tr>
				<td class="view">작성자</td>
				<td class="viewIn" colspan="3"><input type="text" name="nickName" value="${usedDto.nickName}" readonly></td>
			</tr>
			<tr>
				<td class="view">제목</td>
				<td class="viewIn" colspan="3"><input type="text" name="title" value="${usedDto.title}" readonly></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="contents" cols="100" rows="15" readonly>${usedDto.contents}</textarea></td>
			</tr>
		</table>
		<input type="submit" value="목록" class="btn">
	</form>
	<c:if test="${memberDto.nickName != null && usedDto.nickName == memberDto.nickName}">
		<form action="UsedGoodsModify.do?no=${usedDto.no}&nickName=${usedDto.nickName}" method="post">
			<input type="hidden" name="no" value="${usedDto.no}">
			<input type="hidden" name="nickName" value="${usedDto.nickName}">
			<input type="submit" class="btn" value="수정">
		</form>
		<form action="UsedGoodsDelete.do?no=${usedDto.no}" onsubmit="usedDeleteChk()">
			<input type="hidden" name="no" value="${usedDto.no}">
			<input type="submit" class="btn" value="삭제">
		</form>
	</c:if>
	<form action="UsedGoodsReply.do?no=${usedDto.no}">
		<input type="hidden" name="no" value="${usedDto.no}">
		<input type="submit" class="btn" value="답변">
	</form>
	
	<script>
		function usedDeleteChk() {
			if(confirm("글을 삭제하시겠습니까?"))
			{
				return true;
				alert("삭제되었습니다.")
			}
			else
			{
				return false;
			}
		}
	</script>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>