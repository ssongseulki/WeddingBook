package com.weddingbook.www.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.GoodsReservationDao;

public class ReservationDeleteCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsReservationDao reserDao = GoodsReservationDao.getGoodsReservationDao();
		reserDao.deleteDao(Integer.parseInt(request.getParameter("no")));
	}
}
