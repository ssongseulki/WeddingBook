package com.weddingbook.www.communityBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.CommunityBoardDao;
import com.weddingbook.www.dto.CommunityBoardDto;

public class BoardReplyOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityBoardDao boardDao = CommunityBoardDao.getBoardDao();
		CommunityBoardDto boardDto = new CommunityBoardDto();
		boardDto.setNickName(request.getParameter("nickName"));
		boardDto.setContents(request.getParameter("contents"));
		boardDto.setNo(Integer.parseInt(request.getParameter("no")));
		boardDto.setGroupNum(Integer.parseInt(request.getParameter("groupNum")));
		boardDto.setStepNum(Integer.parseInt(request.getParameter("stepNum")));
		boardDto.setIndentNum(Integer.parseInt(request.getParameter("indentNum")));
		boardDao.replyOkDao(boardDto);
	}
}
