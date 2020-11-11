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
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Main/CSS/Main.css">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css">

</head>
<body>
<%@include file="Header.jsp" %>
		<div class="main">
			<div class="mainBox">
				<ul>
					<li>
						<h2>STUDIO</h2>
					</li>
					<li>
						<h2>DRESS</h2>
					</li>
					<li>
						<h2>MAKE_UP</h2>
					</li>
				</ul>
			</div>
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<img alt="사진불러오기 실패" src="../../IMG/main1.jpg">
					</div>
					<div class="swiper-slide">
						<img alt="사진불러오기 실패" src="../../IMG/main2.jpg">
					</div>
					<div class="swiper-slide">
						<img alt="사진불러오기 실패" src="../../IMG/main04.jpg">
					</div>
					<div class="swiper-slide">
						<img alt="사진불러오기 실패" src="../../IMG/main05.jpg">
					</div>
				</div>		
			</div>
			<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
		</div>
	
	<script>
		var mySwiper = new Swiper('.swiper-container',
			{
				autoplay:
					{
						delay: 3000,
					},
			} // 3초마다 자동으로 슬라이드가 넘어갑니다. 1초 = 1000		
		);
	</script>
<%@include file="Footer.jsp" %>
</body>
</html>