package com.weddingbook.www.communityUsedGoods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.CommunityUsedGoodsDao;
import com.weddingbook.www.dto.MemberDto;

public class UsedGoodsWriteCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityUsedGoodsDao usedDao = CommunityUsedGoodsDao.getCommunityUsedGoodsDao();
		MemberDto memberDto = new MemberDto();
		usedDao.writeDao(memberDto);
	}

}
