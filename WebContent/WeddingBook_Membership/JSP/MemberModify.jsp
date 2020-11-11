<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.weddingbook.www.dto.MemberDto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WeddingBook 개인정보수정</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Membership/CSS/MemberModify.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/Header.jsp" %>
	<!-- 사이드 메뉴 -->
	<div id = "divWhole">
			<article id = "articleLoginMenu">
				<h1>회원정보수정</h1>
				<ul>
					<li><a href="#">나의 예약 현황</a></li>
				</ul>
			</article>
		
		<!-- 개인정보수정 -->
		<form action="MemberModifyOk.do" name="member" method="post" onsubmit="return validateForm()">
			<table id="modifyTable">
				<tr>
					<td>이름</td>
					<td><input id="name" type="text" name="name" value="${memberDto.name}" disabled></td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input id="id" type="text" name="id" value="${memberDto.id}" disabled></td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td><input id="nickName" type="text" name="nickName" value="${memberDto.nickName}" disabled></td>
				</tr>
				<tr>
					<td><label for="password">비밀번호</label></td>
					<td><input type="password" id="password" placeholder="8~16자 영문 대 소문자, 숫자, 특수문자" name="password"></td>
				</tr>		
				<tr>
					<td><label for="passwordChk">비밀번호 확인</label></td>
					<td><input type="password" id="passwordChk" name="passwordChk"></td>
				</tr>
				<tr>
					<td><label for="phone2">전화번호 : </label></td>
					<td>
						<select name="phone1" class="phone"><option value="010">010</option></select>
						<input class="phone" type="text" name="phone2" id="phone2" size="5" value="${memberDto.phone2}">-
						<input class="phone" type="text" name="phone3" id="phone3" size="5" value="${memberDto.phone3}">
					</td>
				</tr>
				<tr>
					<td><label for="email">이메일</label></td>
					<td><input type="email" name="email" id="email" required value="${memberDto.email}"></td> 
				</tr>
				<tr>
					<td>성별</td>
					<td><input type="radio" name="gender" value="female" checked>신부님
						<input type="radio" name="gender" value="male">신랑님
					</td>
				</tr>
				<tr>
					<td><label for="weddingDate">예식일을 선택하세요.</label></td>
					<td><input type="date" name="weddingDate" id="weddingDate" value="${memberDto.weddingDate}"></td>
				</tr>
				<tr>
					<td><label for="local">확정된 예식홀 지역을 입력하세요</label></td>
					<td><select name="local" id="local">
							<option value="서울">서울특별시</option>
							<option value="경기도">경기도</option>
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
					</select></td>
				</tr>
			</table>
		</form>
		<input type="submit" value="수정">
		
		<script>
			function validateForm() {
				var password = document.forms["member"]["password"].value;
				var passwordChk = document.forms["member"]["passwordChk"].value;
				if (password != passwordChk)
				{
					alert("비밀번호와 비밀번호 확인이 서로 다릅니다.");
					return false;
				}
				else
					alert("수정이 완료되었습니다.");
			}
		</script>
	</div>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>	
</body>
</html>