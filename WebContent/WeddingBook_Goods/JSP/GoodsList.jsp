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
<title>WeddingBook S.D.M</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Goods/CSS/GoodsList.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/Header.jsp"%>
	<section id="sectionGoodsList">
		<article id="articleGoodsSearch">
			<form action="searchGoods.do" method="post">
				<table>
					<caption>Studio. Dress. Make_up Goods</caption>
					<tr>
						<td><label for="goodsSDM">분류</label> </td>
						<td><select name="goodsSDM">
							<option value="goodsSDM" id="goodsSDM">전체</option>
							<option value="studio" name="studio">studio</option>
							<option value="dress" name="dress">dress</option>
							<option value="makeUp" name="makeUp">makeUp</option>
						</select></td>
						<td>
							<input type="submit" value="검색">
						</td>
					</tr>
				</table>
			</form>
		</article>
		
		<article id="articleGoodsInfo">
			<c:forEach var="goodsDto" items="${goodsList}">
				<ul>
					<li><a href="GoodsView.do?no=${goodsDto.no}">
						<img alt="이미지를 찾지 못했습니다." src="${goodsDto.goodsFile1}">
						<h2 id="goodsName">${goodsDto.goodsName}</h2>
						<h4 id="goodsSDM">${goodsDto.goodsSDM} ${goodsDto.goodsPosition}</h4>
					</a></li>
				</ul>
			</c:forEach>
		</article>	
	</section>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>