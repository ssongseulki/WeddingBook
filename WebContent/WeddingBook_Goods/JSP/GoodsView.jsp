<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WeddingBook S.D.M</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Goods/CSS/GoodsView.css">
</head>
<body>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
	<%@include file="../../WeddingBook_Main/JSP/Header.jsp"%>
	<section id="sectionGoodsView">
		<aside id="asideReservation">
			<form action="ReservationOk.do" method="post">
				<c:if test="${memberDto.id == null}">
					<script>
						alert("로그인 후 이용 가능합니다.")
						location.href="../../WeddingBook_Membership/JSP/LoginMain.do";
					</script>
				</c:if>
				<table id="Reservation">
					<tr>
						<td>방문업체</td>
					</tr>
					<tr>
						<td><input type="text" name="goodsName" value="${goodsDto.goodsName}" readonly></td>
					</tr>
					<tr>
						<td>방문업체 연락처</td>
					</tr>
					<tr>
						<td><input type="text" name="goodsBusinessTel" value="${goodsDto.goodsBusinessTel}" readonly>
					</tr>
					<tr>
						<td>예약자 성함</td>
					</tr>
					<tr>
						<td><input type="text" name="reservationName" required></td>
					</tr>
					<tr>
						<td><label for="reservationDate">방문날짜</label>
					</tr>
					<tr>
						<td><input type="date" id="reservationDate" name="reservationDate" placeholder="방문 날짜를 입력하세요." required></td>
					</tr>
					<tr>
						<td><label for="reservationHours">방문시간</label>
					</tr>
					<tr>
						<td>
							<select name="reservationHours" required>
							<option value="10:00" id="reservationHours">10:00</option>
							<option value="11:00">11:00</option>
							<option value="12:00">12:00</option>
							<option value="13:00">13:00</option>
							<option value="14:00">14:00</option>
							<option value="15:00">15:00</option>
							<option value="16:00">16:00</option>
							<option value="17:00">17:00</option>
							<option value="18:00">18:00</option>
							<option value="19:00">19:00</option>
						</select>
						</td>
					</tr>
				</table>
				<input type="hidden" name="memberId" value="${memberDto.id}">
				<input type="submit" id="reserBtn" value="방문예약">
			</form>
		</aside>
		<article id="goodsView">
			<form action="GoodsList.do" method="post">
				<h1>${goodsDto.goodsName}</h1>
				<h4>${goodsDto.goodsSDM} ${goodsDto.goodsPosition}</h4>
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<c:if test="${goodsDto.goodsFile1 != null}"><div class="swiper-slide"><img src="${goodsDto.goodsFile1}"></div></c:if>
						<c:if test="${goodsDto.goodsFile2 != null}"><div class="swiper-slide"><img src="${goodsDto.goodsFile2}"></div></c:if>
						<c:if test="${goodsDto.goodsFile3 != null}"><div class="swiper-slide"><img src="${goodsDto.goodsFile3}"></div></c:if>
						<c:if test="${goodsDto.goodsFile4 != null}"><div class="swiper-slide"><img src="${goodsDto.goodsFile4}"></div></c:if>
						<c:if test="${goodsDto.goodsFile5 != null}"><div class="swiper-slide"><img src="${goodsDto.goodsFile5}"></div></c:if>
					</div>
					<div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
					<div class="swiper-button-prev"></div><!-- 이전 버튼 -->	
				</div>
				<ul>
					<li><h3>혜택 / 프로모션</h3></li>
					<li><textarea name="goodsPromotion" id="goodsPromotion" readonly>${goodsDto.goodsPromotion}</textarea></li>
				</ul>
				<ul>
					<li><h3>상품정보</h3></li>
					<li><textarea name="goodsInfo" id="goodsInfo" readonly>${goodsDto.goodsInfo}</textarea></li>
				</ul>
				<table>
					<caption>업체정보</caption>
					<tr>
						<td class="busiInfo">업체이름 : </td>
						<td><input type="text" class="busiInput" name="goodsName" value="${goodsDto.goodsName}" readonly></td>
					</tr>
					<tr>
						<td class="busiInfo">영업시간 : </td>
						<td><input type="text" class="busiInput" name="goodsBusinessHours" value="${goodsDto.goodsBusinessHours}" readonly></td>
					</tr>
					<tr>
						<td class="busiInfo">연락처 : </td>
						<td><input type="text" class="busiInput" name="goodsBusinessTel" value="${goodsDto.goodsBusinessTel}" readonly></td>
					</tr>
					<tr>
						<td class="busiInfo">주소 : </td>
						<td><input type="text" class="busiInput" name="goodsBusinessAdd" value="${goodsDto.goodsBusinessAdd}" readonly></td>
					</tr>
					<tr>
						<td class="busiInfo">홈페이지 : </td>
						<td><input type="text" class="busiInput" name="goodsHomepageAdd" value="${goodsDto.goodsHomepageAdd}" readonly></td>
					</tr>
				</table>
				<input type="submit" value="돌아가기" id="btn">
			</form>
		</article>
	</section>
	
	<script>
		function deleteOk() 
		{
			alert("삭제되었습니다.")	
		}
		
		new Swiper('.swiper-container',{navigation :
				{
					nextEl : '.swiper-button-next',
					prevEl : '.swiper-button-prev'
				},
		});
		
	</script>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>
					




