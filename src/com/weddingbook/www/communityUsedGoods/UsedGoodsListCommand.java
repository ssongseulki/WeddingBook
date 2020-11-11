package com.weddingbook.www.communityUsedGoods;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.CommunityUsedGoodsDao;
import com.weddingbook.www.dto.CommunityUsedGoodsDto;

public class UsedGoodsListCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityUsedGoodsDao usedDao = CommunityUsedGoodsDao.getCommunityUsedGoodsDao();
		int curPage = 0;
		if(request.getParameter("curPage") != null)
			curPage = Integer.parseInt(request.getParameter("curPage"));
		ArrayList<CommunityUsedGoodsDto> usedList = usedDao.usedListDao(curPage);
		int totalPage = usedDao.calTotalPage();
		request.setAttribute("usedList", usedList);
		request.setAttribute("totalPage", totalPage);
	}
}
