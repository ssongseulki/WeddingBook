package com.weddingbook.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.MemberDao;
import com.weddingbook.www.dto.MemberDto;

public class MemberModifyOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao memberDao = MemberDao.getMemberDao();
		MemberDto memberDto = new MemberDto();
		
		memberDto.setNickName(request.getParameter("nickName"));
		memberDto.setPassword(request.getParameter("password"));
		memberDto.setPhone1(request.getParameter("phone1"));
		memberDto.setPhone2(request.getParameter("phone2"));
		memberDto.setPhone3(request.getParameter("phone3"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setGender(request.getParameter("gender"));
		memberDto.setWeddingDate(request.getParameter("weddingDate"));
		memberDto.setLocal(request.getParameter("local"));
		memberDao.memberModifyOK(memberDto);
	}
}
