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
<title>회원정보_관리자</title>
<link rel="stylesheet" type="text/css" href="../../AdminMember/CSS/AdminMemberList.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/AdminHeader.jsp" %>
	<div id="search">
		<form action="MemberSearch.do" method="post">
			<select name="kindOfSearch">
				<option value="searchName">이름</option>
				<option value="searchPhone">연락처</option>
				<option value="searchId">아이디</option>
			</select>
			<input type="text" name="searchKeyword">
			<input type="submit" value="검색"> 
		</form>
	</div>
	<form action="AdminMemberDelete.do?id=${memberDto.id}" method="post" onsubmit="member_deleteChk()">
		<table id="memberList">
			<caption>회원정보</caption>
			<tr>
				<td></td><td>이름</td><td>닉네임</td><td>ID</td><td>연락처</td><td>e-mail</td><td>성별</td><td>예식일</td><td>삭제</td>
			</tr>
			<c:forEach var="memberDto" items="${memberList}">
				<tr>
					<td id="listChk"><input type="checkbox"></td>
					<td>${memberDto.name}</td>
					<td>${memberDto.nickName}</td>
					<td>${memberDto.id}</td>
					<td>${memberDto.phone1}-${memberDto.phone2}-${memberDto.phone3}</td>
					<td>${memberDto.email}</td>
					<td>${memberDto.gender}</td>
					<td>${memberDto.weddingDate}</td>
					<td>
						<input type="hidden" value="${memberDto.id}">
						<input type="submit" value="삭제" name="${memberDto.id}">
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<script>
		function member_deleteChk() {
			if (confirm("해당 회원을 삭제하시겠습니까?")) 
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






