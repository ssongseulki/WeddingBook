package com.weddingbook.www.goods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.GoodsDao;
import com.weddingbook.www.dto.GoodsDto;

public class GoodsModifyCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsDao goodsDao = GoodsDao.getGoodsDao();
		GoodsDto goodsDto = new GoodsDto();
		goodsDto.setNo(Integer.parseInt(request.getParameter("no")));
		goodsDao.modify(goodsDto);
		request.setAttribute("goodsDto", goodsDto);
	}
}
