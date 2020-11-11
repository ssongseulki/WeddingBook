package com.weddingbook.www.communityBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.CommunityBoardDao;
import com.weddingbook.www.dto.CommunityBoardDto;
import com.weddingbook.www.dto.CommunityCommentDto;

public class CommentOkcommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityBoardDao boardDao = CommunityBoardDao.getBoardDao();
		CommunityCommentDto commentDto = new CommunityCommentDto();
		commentDto.setCommentName(request.getParameter("commentName"));
		commentDto.setComment(request.getParameter("comment"));
		commentDto.setNo(Integer.parseInt(request.getParameter("no")));
		boardDao.commentOkDao(commentDto);
		request.setAttribute("commentDto", commentDto);
	}
}
