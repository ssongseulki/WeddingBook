package com.weddingbook.www.communityUsedGoods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.CommunityUsedGoodsDao;
import com.weddingbook.www.dto.CommunityUsedGoodsDto;

public class UsedGoodsReplyOkCommnad implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityUsedGoodsDao usedDao = CommunityUsedGoodsDao.getCommunityUsedGoodsDao();
		CommunityUsedGoodsDto usedDto = new CommunityUsedGoodsDto();
		usedDto.setNickName(request.getParameter("nickName"));
		usedDto.setTitle(request.getParameter("title"));
		usedDto.setContents(request.getParameter("contents"));
		usedDto.setNo(Integer.parseInt(request.getParameter("no")));
		usedDto.setGroupNum(Integer.parseInt(request.getParameter("groupNum")));
		usedDto.setStepNum(Integer.parseInt(request.getParameter("stepNum")));
		usedDto.setIndentNum(Integer.parseInt(request.getParameter("indentNum")));
		usedDao.replyOkDao(usedDto);
	}
}
