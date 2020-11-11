<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.weddingbook.www.dao.MemberDao" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Membership/CSS/IdCheck.css">
</head>
<body>
	<h3>아이디 중복 확인 결과</h3>
	<%
		request.setCharacterEncoding("UTF-8");
		MemberDao memberDao = MemberDao.getMemberDao();
		String id = request.getParameter("id");
		int cnt = memberDao.duplecateID(id);
		out.println("ID : <strong>" + id + "</strong>");
		if(cnt == 0)
		{
			out.println("<p>사용 가능한 아이디입니다. </p>");
			out.println("<a href = 'javascript:apply(\"" + id + "\")'>적용</a>");
	%>
	
	<script>
		function apply(id) 
		{
			opener.document.member.id.value = id;
			window.close();		//창닫기
		}		
	</script>
	<%
		}
		else
		{
			out.println("<p>이미 사용중인 ID입니다.</p>");
		}
	%>
	<a href="javascript:history.back()">재입력</a>
	&nbsp; &nbsp;
	<a href="javascript:window.close()">닫기</a>
</body>
</html>