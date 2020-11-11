package com.weddingbook.www.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.GoodsReservationDao;
import com.weddingbook.www.dto.GoodsReservationDto;

public class ReservationOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsReservationDao reserDao = GoodsReservationDao.getGoodsReservationDao();
		GoodsReservationDto reserDto = new GoodsReservationDto();
		
		reserDto.setGoodsName(request.getParameter("goodsName"));
		reserDto.setGoodsBusinessTel(request.getParameter("goodsBusinessTel"));
		reserDto.setReservationName(request.getParameter("reservationName"));
		reserDto.setReservationDate(request.getParameter("reservationDate"));
		reserDto.setReservationHours(request.getParameter("reservationHoures"));
		reserDto.setMemberId(request.getParameter("memberId"));
		reserDao.reservationOkDao(reserDto);
	}
}
