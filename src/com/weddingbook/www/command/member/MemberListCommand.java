package com.weddingbook.www.command.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.MemberDao;
import com.weddingbook.www.dto.MemberDto;

public class MemberListCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao memberDao = MemberDao.getMemberDao();
		ArrayList<MemberDto> memberList = memberDao.listDao();
		request.setAttribute("memberList", memberList);
	}
}
