package com.weddingbook.www.goods;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.GoodsDao;
import com.weddingbook.www.dto.GoodsDto;

public class BusinessListCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsDao goodsDao = GoodsDao.getGoodsDao();
		ArrayList<GoodsDto> busiList = goodsDao.busiListDao();
		request.setAttribute("busiList", busiList);
	}
}
