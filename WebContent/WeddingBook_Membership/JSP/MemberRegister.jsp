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
<title>WeddingBook_회원가입</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Membership/CSS/MemberRegister.css">
</head>
<body>
	<%@include file="../../WeddingBook_Main/JSP/Header.jsp"%>
	<!-- 사이드 메뉴 -->
	<article id="article_login_menu">
		<h1>회원가입</h1>
		<ul>
			<li><a href="LoginMain.do">로그인</a>
		</ul>	
	</article>
	
	<!-- 회원가입 양식-->
	<form action="memberRegisterOk.do" method="post" name="member" onsubmit="return memberFunction()">
		<table id="registerTable">
			<tr>
				<td><label for="name">이름 : </label></td>
				<td><input type="text" placeholder="필수 정보 입력" name="name" id="name"></td>
			</tr>
			<tr>
				<td><label for="id">아이디 : </label></td>
				<td>
					<input type="text" placeholder="ID 중복확인이 필요합니다." name="id" id="id" readonly>
					<input class="idChk" type="button" onclick="idCheck()" value="중복확인">
				</td>
			</tr>
			<tr>
				<td><label for="nickName">닉네임 : </label></td>
				<td><input type="text" name="nickName" id="nickName"></td>
			</tr>
			<tr>
				<td><label for="password">비밀번호 : </label></td>
				<td><input type="password" name="password" id="password" placeholder="8~16자 영문 대 소문자, 숫자, 특수문자"></td>
			</tr>
			<tr>
				<td><label for="passwordChk">비밀번호 확인: </label></td>
				<td><input type="password" name="passwordChk" id="passwordChk" ></td>
			</tr>
			<tr>
				<td><label for="phone2">연락처: </label></td>
				<td>
					<select name="phone1" class="phone">
						<option value="010">010</option>
					</select>
					<input type="text" name="phone2" id="phone2" class="phone" size="5" >-<input type="text" name="phone3" class="phone" size="5">
				</td>
			</tr>
			<tr>
				<td><label for="email">이메일 : </label></td>
				<td><input type="email" name="email" id="email" required></td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="gender" value="female" checked>신부님
					<input type="radio" name="gender" value="male">신랑님
				</td>			
			</tr>
			<tr>
				<td><label for="weddingDate">예식일을 선택하세요.</label></td>
				<td><input type="date" name="weddingDate" id="weddingDate"></td>
			</tr>
			<tr>
				<td><label for="local">확정된 예식 홀 지역을 선택하세요.</label></td>
				<td><select name="local" id="local">
					<option value="서울">서울특별시</option>
					<option value="경기도">경기도</option>
					<option value="강원도">강원도</option>
					<option value="충남">충청남도</option>
					<option value="축북">충청북도</option>
					<option value="경남">경상남도</option>
					<option value="경북">경상북도</option>
					<option value="전남">전라남도</option>
					<option value="전북">전라북도</option>
					<option value="제주도">제주특별자치도</option>
					<option value="제주도">제주특별자치도</option>
					<option value="세종">세종특별자치시</option>
					<option value="인천">인천광역시</option>
					<option value="대전">대전광역시</option>
					<option value="부산">부산광역시</option>
					<option value="울산">울산광역시</option>
					<option value="대구">대구광역시</option>
					<option value="광주">광주광역시</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" onclick="register()"  value="회원가입" >
		<input type="reset" value="초기화">
	</form>
	
	<script>
		function memberFunction() 
		{
			if(document.getElementById("name").value == "")
			{
				alert("이름을 입력하세요.");
				member.name.focus();
				return false;
			}
			if(document.getElementById("id".value == ""))
			{
				alert("아이디를 입력하세요.");
				member.id.focus();
				return false;
			}
			
			var password = document.getElementById("password").value;
			var passwordChk = document.getElementById("passwordChk").value;
			if(password == "")
			{
				alert("비밀번호를 입력하세요.");
				member.password.focus();
				return false;
			}
			
			//if(passwordChk == "");
			//{
			//	alert("비밀번호 확인을 입력하세요.");
			//	member.passwordChk.focus();
			//	return false;
			//}
			if(password != passwordChk)
			{
				alert("비밀번호가 다릅니다. 다시 입력해 주세요.");
				member.passwordChk.value = "";
				member.passwordChk.focus();
				return false;
			}
			return true;
		}
		
		function idCheck() 
		{
			window.open("IdCheckForm.jsp", "IdCheckForm", "width=600, height=300");
		}
		function register() 
		{
			alert("회원가입이 완료 되었습니다.")	
		}
	</script>
	<%@include file="../../WeddingBook_Main/JSP/Footer.jsp" %>
</body>
</html>















