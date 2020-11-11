package com.weddingbook.www.communityBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.CommunityBoardDao;
import com.weddingbook.www.dto.CommunityBoardDto;
import com.weddingbook.www.dto.CommunityCommentDto;

public class CommentPopupCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityBoardDao boardDao = CommunityBoardDao.getBoardDao();
		CommunityBoardDto boardDto = new CommunityBoardDto();
		
		// 게시글 가져오기
		int commentNo = Integer.parseInt(request.getParameter("no"));
		boardDto.setNo(commentNo);
		boardDao.commentPopupDao(boardDto);
		request.setAttribute("boardDto", boardDto);
		
		// 댓글 개수 구하기		
		CommunityCommentDto commentDto = boardDao.boardCommentCnt(commentNo);
		request.setAttribute("commentDto", commentDto);
		
		// 댓글 구하기
		ArrayList<CommunityCommentDto> commentList = boardDao.commentListDao(commentNo);
		request.setAttribute("commentList", commentList);
	}
}
