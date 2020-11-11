package com.weddingbook.www.reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.GoodsReservationDao;
import com.weddingbook.www.dto.GoodsReservationDto;

public class ReservationListCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsReservationDao reserDao = GoodsReservationDao.getGoodsReservationDao();
		ArrayList<GoodsReservationDto> reserList = reserDao.reserListDao();
		request.setAttribute("reserList", reserList);
	}
}
