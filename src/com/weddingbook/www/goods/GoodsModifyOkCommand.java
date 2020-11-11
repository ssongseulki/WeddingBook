package com.weddingbook.www.goods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.GoodsDao;
import com.weddingbook.www.dto.GoodsDto;

public class GoodsModifyOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsDao goodsDao = GoodsDao.getGoodsDao();
		GoodsDto goodsDto = new GoodsDto();
		goodsDto.setGoodsSDM(request.getParameter("goodsSDM"));
		goodsDto.setGoodsName(request.getParameter("goodsName"));
		goodsDto.setGoodsPosition(request.getParameter("goodsPosition"));
		goodsDto.setGoodsPromotion(request.getParameter("goodsPromotion"));
		goodsDto.setGoodsInfo(request.getParameter("goodsInfo"));
		goodsDto.setGoodsBusinessHours(request.getParameter("goodsBusinessHours"));
		goodsDto.setGoodsBusinessTel(request.getParameter("goodsBusinessTel"));
		goodsDto.setGoodsBusinessAdd(request.getParameter("goodsBusinessAdd"));
		goodsDto.setGoodsHomepageAdd(request.getParameter("goodsHomepageAdd"));
		goodsDto.setNo(Integer.parseInt(request.getParameter("no")));
		goodsDao.modifyOk(goodsDto);
		request.setAttribute("goodsDto", goodsDto);
		
	}

}
