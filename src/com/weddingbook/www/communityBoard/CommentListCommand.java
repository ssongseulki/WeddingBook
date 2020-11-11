package com.weddingbook.www.communityBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.CommunityBoardDao;
import com.weddingbook.www.dto.CommunityCommentDto;

public class CommentListCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityBoardDao boardDao = CommunityBoardDao.getBoardDao();
		ArrayList<CommunityCommentDto> commentList = boardDao.commentListDao(Integer.parseInt(request.getParameter("no")));
		request.setAttribute("commentList", commentList);
	}
}
