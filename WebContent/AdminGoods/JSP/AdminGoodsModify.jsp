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
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../AdminGoods/CSS/AdminGoodsWrite.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/AdminHeader.jsp" %>
	<div id="goodsModify">
		<form action="AdminGoodsModifyOk.do?no=${goodsDto.no}" method="post">
			<h1>S.D.M 업체수정</h1>
			<table id="AdminWritrTable">
				<tr>
					<td>종류</td>
					<td>
						<select name="goodsSDM">
							<option id="goodsSDM">${goodsDto.goodsSDM}</option>
							<option value="studio">STUDIO</option>
							<option value="dress">DRESS</option>
							<option value="makeup">MAKE_UP</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label for="goodsName">업체이름</label></td>
					<td><input type="text" name="goodsName" id="goodsName" value="${goodsDto.goodsName}" required></td>
				</tr>
				<tr>
					<td><label for="goodsPosition" value="${goodsDto.goodsPosition}">업체지역</label></td>
					<td>
					<select name="goodsPosition" required>
						<option value="지역" id="goodsPosition" >${goodsDto.goodsPosition}</option>
						<option value="서울">서울특별시</option>
						<option value="경기">경기도</option>
						<option value="강원도">강원도</option>
						<option value="충남">충남</option>
						<option value="충북">충북</option>
						<option value="경남">경남</option>
						<option value="경북">경북</option>
						<option value="전남">전남</option>
						<option value="전북">전북</option>
						<option value="제주도">제주특별자치도</option>
						<option value="인천">인천광역시</option>
						<option value="대전">대전광역시</option>
						<option value="세종">세종특별자치시</option>
						<option value="부산">부산광역시</option>
						<option value="울산">울산광역시</option>
						<option value="대구">대구광역시</option>
						<option value="광주">광주광역시</option>
					</select>	
					</td>
				</tr>
				<tr>
					<td><label for="goodsPromotion">혜택 / 프로모션</label></td>
					<td><textarea name="goodsPromotion" id="goodsPromotion">${goodsDto.goodsPromotion}</textarea></td>
				</tr>
				<tr>
					<td><label for="goodsInfo">상품정보</label></td>
					<td><textarea name="goodsInfo" id="goodsInfo">${goodsDto.goodsInfo}</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><h3>업체정보</h3></td>
				</tr>
				<tr>
					<td><label for="goodsBusinessHours">영업시간</label></td>
					<td><input type="text" name="goodsBusinessHours" id="goodsBusinessHours" value="${goodsDto.goodsBusinessHours}" required></td>
				</tr>
				<tr>
					<td><label for="goodsBusinessTel">연락처</label></td>
					<td><input type="text" name="goodsBusinessTel" id="goodsBusinessTel" value="${goodsDto.goodsBusinessTel}" required></td>
				</tr>
				<tr>
					<td><label for="goodsBusinessAdd">영체주소</label></td>
					<td><input type="text" name="goodsBusinessAdd" id="goodsBusinessAdd" value="${goodsDto.goodsBusinessAdd}" required></td>
				</tr>
				<tr>
					<td><label for="goodsHomepageAdd">홈페이지</label></td>
					<td><input type="text" name="goodsHomepageAdd" id="goodsHomepageAdd" value="${goodsDto.goodsHomepageAdd}" required></td>
				</tr>
				<tr>
					<td colspan="2"><h3>1장 이상의 사진을 등록해주세요.</h3></td>
				</tr>
				<tr>
					<td><label for="goodsFile1">사진1</label></td>
					<td><input type="file" name="goodsFile1" id="goodsFile1" value="goodsFile1" required></td>
				</tr>
				<tr>
					<td><label for="goodsFile2">사진2</label></td>
					<td><input type="file" name="goodsFile2" id="goodsFile2" value="goodsFile2" ></td>
				</tr>
				<tr>
					<td><label for="goodsFile3">사진3</label></td>
					<td><input type="file" name="goodsFile3" id="goodsFile3" value="goodsFile3" ></td>
				</tr>
				<tr>
					<td><label for="goodsFile4">사진4</label></td>
					<td><input type="file" name="goodsFile4" id="goodsFile4" value="goodsFile4" ></td>
				</tr>
				<tr>
					<td><label for="goodsFile5">사진5</label></td>
					<td><input type="file" name="goodsFile5" id="goodsFile5" value="goodsFile5" ></td>
				</tr>
			</table>
			<input type="submit" value="수정" class="btn">
		</form>
	</div>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>





