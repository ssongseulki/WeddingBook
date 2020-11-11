<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Seulki">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" type="text/css" href="../../WeddingBook_Community/board_CSS/BoardComment.css">
</head>
<body>
	<div id="whole">
		<table id="listTable">
			<tr>
				<td id="no" colspan="2">No.${boardDto.no}</td>
				<td id="wTime" colspan="2">${boardDto.wTime}</td>
			</tr>
			<tr>
				<td id="nickName" colspan="2">${boardDto.nickName}</td>
				<td id="weddingDate" colspan="2">${boardDto.weddingDate}결혼</td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="contents" disabled>${boardDto.contents}</textarea></td>
			</tr>
			<tr>
				<td colspan="4" id="btn"><a href="">수정</a><a href="">삭제</a></td>
			</tr>
		</table>
		<div class="commentWhole">
			<div id="commentInput">
				<div class="commentCnt">
					<span>댓글 ${commentDto.commentCnt}</span>
				</div>
				<form action="CommentOk.do" method="post">
					<div id="commentFix">
						<input type="text" name="commentName" value="${memberDto.nickName}" id="commentName" readonly>
						<textarea class="comment" name="comment" placeholder="댓글을 입력하세요." id="commentText" required></textarea>
						<input type="hidden" name="no" value="${boardDto.no }" />
						<input type="hidden" name="commentName" value="${boardDto.nickName}"/>
						<input class="commentSubmit" type="submit" value="등록" id="commentBtn" />
					</div>
				</form>
			</div>
			<div class="comment">
				<c:forEach var="commentDto" items="${commentList}">
					<table class="commentTable">
						<tr>
							<td class="inputCommentName">${commentDto.commentName}</td>
							<c:choose>
								<c:when test="${memberDto.nickName !=null && memberDto.nickName==commentDto.commentName}">
									<form method="post" action="CommentModifyOk.do">
										<td class="inputComment"><input type="text" name="comment" value="${commentDto.comment}"></td>
										<td class="commentWTime">${commentDto.wTime}</td>
										<td class="commentModi">
											<input type="hidden" name="no" value="${commentDto.no}">
											<input type="hidden" name="commentNo" value="${commentDto.commentNo}">
											<input class="commentBtn" type="submit" value="수정">
										</td>	
									</form>
									<form method="post" action="CommentDeleteOk.do" onsubmit="return delCheck();">
										<td>
											<input type="hidden" name="no" value="${commentDto.no}">
											<input type="hidden" name="commentNo" value="${commentDto.commentNo}">
											<input type="submit" class="commentBtn" value="삭제">
										</td>
									</form>
								</c:when>
								<c:otherwise>
									<td class="inputComment">${commentDto.comment}</td>
									<td class="commentwTime">${commentDto.wTime}</td>
									<td class="commentModi"></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</table>
				</c:forEach>
			</div>
		</div>
	</div>
	<script>
		function delCheck()
		{
			if(comfirm("댓글을 삭제하시겠습니까?"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	</script>
</body>
</html>