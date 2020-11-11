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
<title>업체정보</title>
<link rel="stylesheet" type="text/css" href="../../AdminGoods/CSS/AdminBusinessList.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/AdminHeader.jsp" %>
	<form action="DeleteReservation.do?no=${reserDto.no}">
		<table id="businessList">
			<caption>업체정보</caption>
			<tr>
				<td>등록번호</td><td>구분</td><td>업체이름</td><td>지역</td><td>전화번호</td><td></td>
			</tr>
			<c:forEach var="goodsDto" items="${busiList}">
				<tr>
					<td id="listNo">${goodsDto.no}</td>
					<td>${goodsDto.goodsSDM}</td>
					<td><a href="../../AdminGoods/JSP/AdminBusinessView.do?no=${goodsDto.no}">${goodsDto.goodsName}</a></td>
					<td>${goodsDto.goodsPosition}</td>
					<td>${goodsDto.goodsBusinessTel}</td>
					<td>
						<input type="hidden" name="no" value="${reserDto.no}">
						<input type="submit" value="삭제">
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>













