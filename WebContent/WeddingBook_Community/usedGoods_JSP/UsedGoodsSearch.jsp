<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Community/usedGoods_CSS/UsedGoodsSearch.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/Header.jsp"%>
	<!-- 사이드 메뉴 -->
	<div id="divWhole">
		<nav id="articleCommunityMenu">
			<h1>중고거래</h1>
			<ul>
				<li><a href="../../WeddingBook_Community/board_JSP/BoardList.do">결혼준비</a></li>
			</ul>
		</nav>
		
	<!-- 중고거래 게시판 리스트 -->
		<div id="search">
			<form action="UsedGoodsSearch.do" method="post">
				<p>검색</p>
				<select name="kindOfSearch">
					<option value="searchTitle">제목</option>
					<option value="searchTitleAndContents">제목 + 내용</option>
					<option value="searchName">작성자</option>
				</select>
				<input type="text" name="searchKeyword">
				<input type="submit" value="검색">
			</form>
		</div>
		<table id="listTable">
			<tr>
				<td class="listSize1">번호</td><td class="listSize2">작성자</td><td class="listSize3">제목</td><td class="listSize1">조회수</td>
			</tr>
			<c:forEach var="usedDto" items="${usedList}">
				<tr>
					<td class="listSize1">${usedDto.no}</td>
					<td class="listSize2">${usedDto.nickName}</td>
					<td class="listSize3">
						<a href="UsedGoodsView.do?no=${usedDto.no}">
							<c:forEach var="i" begin="1" end="${usedDto.indentNum}" step="1">-</c:forEach>
							${usedDto.title}
						</a>
					</td>
					<td class="listSize1">${usedDto.hit}</td>	
				</tr>
			</c:forEach>
		</table>
		<a href="UsedGoodsWrite.do" id="writeBtn">글작성</a>	
	</div>
	<c:set var="curPage" value="${usedDto.curPage}"></c:set>
	<c:if test="${curPage eq null}">
		<c:set var="curPage" value="0"></c:set>
	</c:if>
	<c:forEach var="i" begin="0" end="${totalPage}" step="1">
		<c:if test="${i eq curPage}">${i+1}</c:if>
		<c:if test="${i ne curPage}"><a href="UsedGoodsList.do?curPage=${i}">${i+1}</a></c:if>
	</c:forEach>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>






