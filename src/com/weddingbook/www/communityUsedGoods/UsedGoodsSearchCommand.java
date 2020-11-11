package com.weddingbook.www.communityUsedGoods;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.CommunityUsedGoodsDao;
import com.weddingbook.www.dto.CommunityUsedGoodsDto;

public class UsedGoodsSearchCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityUsedGoodsDao usedDao = CommunityUsedGoodsDao.getCommunityUsedGoodsDao();
		String kindOfSearch = request.getParameter("kindOfSearch");
		String searchKeyword = request.getParameter("searchKeyword");
		ArrayList<CommunityUsedGoodsDto> usedList = usedDao.search(kindOfSearch, searchKeyword);
		request.setAttribute("usedList", usedList);
	}
}
