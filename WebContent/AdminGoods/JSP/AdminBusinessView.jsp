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
<link rel="stylesheet" type="text/css" href="../../AdminGoods/CSS/AdminBusinessView.css">
</head>
<body>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
	<%@include file="../../WeddingBook_Main/JSP/AdminHeader.jsp" %>
	<section id="SectionGoodsView">
		<article id="goodsView">
			<form action="AdminBusinessList.do" method="post">
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
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
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
						<td class="busiInfo">업체 이름 : </td>
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
				<div id="btnAll"> 
					<input type="submit" value="돌아가기" id="btn">
					<a href="../../AdminGoods/JSP/AdminGoodsModify.do?no=${goodsDto.no}">업체수정</a>
					<a href="GoodsDelete.do?no=${goodsDto.no}" onclick="deleteOk()">삭제</a>
				</div>
			</form>		
		</article>
	</section>
	<script>
		function deleteOk() {
			alert("삭제되었습니다.")
		}
		new Swiper('.swiper-container',{
			navigation : {
				nextE1 : '.swiper-button-next',
				prevE1 : '.swiper-button-prev',
			},
		});	
	</script>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>




