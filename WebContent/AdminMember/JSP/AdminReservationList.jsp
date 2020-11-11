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
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../AdminMember/CSS/AdminReservation.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/AdminHeader.jsp" %>
	<form action="DeleteReservation.do?no=${reserDto.no}">
		<table id="reservationList">
			<caption>회원 예약현황</caption>
			<tr>
				<td>예약번호</td><td>회원ID</td><td>예약자</td><td>예약업체</td><td>업체 전화번호</td><td>예약일자 및 시간</td><td>작성일</td><td>삭제</td>
			</tr>
			<c:forEach var="reserDto" items="${reserList}">
				<tr>
					<td id="listNo">${reserDto.no}</td>
					<td>${reserDto.memberId}</td>
					<td>${reserDto.reservationName}</td>
					<td>${reserDto.goodsName}</td>
					<td>${reserDto.goodsBusinessTel}</td>
					<td>${reserDto.reservationDate}</td>
					<td>${reserDto.reservationHours}</td>
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