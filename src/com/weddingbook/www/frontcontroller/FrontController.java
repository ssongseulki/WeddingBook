package com.weddingbook.www.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingbook.www.command.Command;
import com.weddingbook.www.command.member.*;
import com.weddingbook.www.communityBoard.*;
import com.weddingbook.www.communityUsedGoods.*;


import com.weddingbook.www.dto.*;
import com.weddingbook.www.goods.*;
import com.weddingbook.www.reservation.*;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FrontController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doAction(request, response);
	}
	public void doAction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		String commandName = request.getServletPath();
		System.out.println("sevletPate" + commandName);
		String viewPage = null;
		Command command = null;
		int flag = 0;
		
		//Membership Command
		if(commandName.equals("/WeddingBook_Membership/JSP/MemberRegister.do")) //회원가입 화면 이동
		{
			viewPage = "MemberRegister.jsp";
		}
		else if(commandName.equals("/WeddingBook_Membership/JSP/memberRegisterOk.do")) //회원가입 성공
		{
			command = new MemberRegisterOkCommand();
			command.excute(request, response);
			viewPage = "LoginMain.jsp";
		}
		else if(commandName.equals("/WeddingBook_Membership/JSP/LoginMain.do")) // 로그인 화면
		{
			viewPage = "LoginMain.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Membership/JSP/loginOk.do")) //로그인 성공
		{
			command = new LoginOkCommand();
			command.excute(request, response);
			viewPage = "../../WeddingBook_Main/JSP/Main.jsp";
		}
		else if(commandName.equals("/WeddingBook_Membership/JSP/Logout.do")) //로그아웃
		{
			request.getSession().invalidate();
			viewPage = "LoginMain.jsp";
		}
		else if(commandName.equals("/WeddingBook_Membership/JSP/MemberModify.do")) //회원정보수정
		{
			command = new MemberModifyCommand();
			command.excute(request, response);
			viewPage = "MemberModify.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Membership/JSP/MemberModifyOk.do")) //회원정보수정 성공
		{
			command = new MemberModifyOkCommand();
			command.excute(request, response);
			viewPage = "LoginMain.jsp";
		}
		else if(commandName.equals("/WeddingBook_Main/JSP/AdminLogin.do")) //관리자 로그인
		{
			viewPage = "AdminLogin.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Main/JSP/AdminLoginOk.do")) //관리자 로그인 성공
		{
			command = new LoginOkCommand();
			command.excute(request, response);
			viewPage = "../../WeddingBook_Main/JSP/AdminMain.jsp";
		}
		else if(commandName.equals("/AdminMember/JSP/AdminMemberList.do")) //관리자 회원 리스트
		{
			command = new MemberListCommand();
			command.excute(request, response);
			viewPage = "AdminMemberList.jsp";
			flag = 1;
		}
		else if(commandName.equals("/AdminMember/JSP/AdminMemberDelete.do")) //관리자 회원 삭제
		{
			command = new MemberDeleteCommand();
			command.excute(request, response);
			viewPage = "AdminMemberList.do";
		}
		else if(commandName.equals("/AdminMember/JSP/AdminMemberSearch.do")) //관리자 회원 검색
		{
			command = new MemberSearchCommand();
			command.excute(request, response);
			viewPage = "AdminMemberSearch.jsp";
			flag = 1;
		}
		
		//Community Board Command
		else if(commandName.equals("/WeddingBook_Community/board_JSP/BoardList.do"))
		{
			command = new BoardListCommand();
			command.excute(request, response);
			viewPage = "BoardList.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Community/board_JSP/BoardWrite.do"))
		{
			viewPage = "BoardWrite.jsp";
		}
		else if(commandName.equals("/WeddingBook_Community/board_JSP/BoardWriteOk.do"))
		{
			command = new BoardWriteOkCommand();
			command.excute(request, response);
			viewPage = "BoardList.do";
		}
		else if(commandName.equals("/WeddingBook_Community/board_JSP/BoardModifyOk.do"))
		{
			command = new BoardModifyOkCommand();
			command.excute(request, response);
			viewPage = "BoardList.do";
		}
		else if(commandName.equals("/WeddingBook_Community/board_JSP/BoardReply.do"))
		{
			command = new BoardReplyCommand();
			command.excute(request, response);
			viewPage = "reply.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Community/board_JSP/BoardReplyOk.do"))
		{
			command = new BoardReplyOkCommand();
			command.excute(request, response);
			viewPage = "BoardList.do";
		}
		else if(commandName.equals("/WeddingBook_Comuunity/board_JSP/BoardDelete.do"))
		{
			command = new BoardDeleteCommand();
			command.excute(request, response);
			viewPage = "BoardList.do";
		}
		else if(commandName.equals("/WeddingBook_Community/board_JSP/CommentPopup.do"))
		{
			command = new CommentPopupCommand();
			command.excute(request, response);
			viewPage = "BoardComment.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Community/board_JSP/CommentOk.do"))
		{
			command = new CommentOkcommand();
			command.excute(request, response);
			viewPage = "CommentList.do";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Community/board_JSP/CommentList.do"))
		{
			command = new CommentListCommand();
			command.excute(request, response);
			viewPage = "CommentPopup.do?no=" + ((CommunityCommentDto)request.getAttribute("commentDto")).getNo();
			flag = 1;
		}
		else if(commandName.equals("WeddingBook_Community/board_JSP/CommentModifyOk.do"))
		{
			command = new CommentModifyCommand();
			command.excute(request, response);
			viewPage = "CommentPopup.do?no=" + ((CommunityCommentDto)request.getAttribute("commentDto")).getNo();
		}
		else if(commandName.equals("/WeddingBook_Community/board_JSP/CommentDeleteOk.do"))
		{
			command = new CommentDeleteCommand();
			command.excute(request, response);
			viewPage = "CommentPopup.do?no=" + ((CommunityCommentDto)request.getAttribute("commentDto")).getNo();
		}
		
	//Community UsedGoods Command
		if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsList.do"))
		{
			command = new UsedGoodsListCommand();
			command.excute(request, response);
			viewPage = "UsedGoodsList.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsWrite.do"))
		{
			command = new UsedGoodsWriteCommand();
			command.excute(request, response);
			viewPage = "UsedGoodsWrite.jsp";
		}
		else if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsWriteOk.do"))
		{
			command = new UsedGoodsWriteOkCommand();
			command.excute(request, response);
			viewPage = "UsedGoodsList.do";
		}
		else if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsView.do"))
		{
			command = new UsedGoodsViewCommand();
			command.excute(request, response);
			viewPage = "UsedGoodsView.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsModify.do"))
		{
			command = new UsedGoodsModifyCommand();
			command.excute(request, response);
			viewPage = "UsedGoodsModify.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsModifyOk.do"))
		{
			command = new UsedGoodsModifyOkCommand();
			command.excute(request, response);
			viewPage = "UsedGoodsList.do";
		}
		else if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsReply.do"))
		{
			command = new UsedGoodsReplyCommand();
			command.excute(request, response);
			viewPage = "UsedGoodsReply.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsReplyOk.do"))
		{
			command = new UsedGoodsReplyOkCommnad();
			command.excute(request, response);
			viewPage = "UsedGoodsList.do";
		}
		else if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsDelete.do"))
		{
			command = new UsedGoodsDeleteCommand();
			command.excute(request, response);
			viewPage = "UsedGoodsList.do";
		}
		else if(commandName.equals("/WeddingBook_Community/usedGoods_JSP/UsedGoodsSearch.do"))
		{
			command = new UsedGoodsSearchCommand();
			command.excute(request, response);
			viewPage = "UsedGoodsSearch.jsp";
			flag = 1;
		}
		
		//Goods Command
		else if(commandName.equals("/WeddingBook_Goods/JSP/GoodsList.do"))
		{
			command = new GoodsListCommand();
			command.excute(request, response);
			viewPage = "GoodsList.jsp";
			flag = 1;
		}
		else if(commandName.equals("/AdminGoods/JSP/AdminGoodsWriteOk.do"))
		{
			command = new GoodsWriteOkCommand();
			command.excute(request, response);
			viewPage = "AdminBusinessList.do";
		}
		else if(commandName.equals("/WeddingBook_Goods/JSP/GoodsView.do"))
		{
			command = new GoodsViewCommand();
			command.excute(request, response);
			viewPage = "GoodsView.jsp";
			flag = 1;
		}
		else if(commandName.equals("/AdminGoods/JSP/AdminGoodsModify.do"))
		{
			command = new GoodsModifyCommand();
			command.excute(request, response);
			viewPage = "AdminGoodsModify.jsp";
			flag = 1;
		}
		else if(commandName.equals("/AdminGoods/JSP/AdminGoodsModifyOk.do"))
		{
			command = new GoodsModifyOkCommand();
			command.excute(request, response);
			viewPage = "AdminBusinessView.do?no=" + ((GoodsDto)request.getAttribute("goodsDto")).getNo();
		}
		else if(commandName.equals("/AdminGoods/JSP/GoodsDelete.do"))
		{
			command = new GoodsDeleteCommand();
			command.excute(request, response);
			viewPage = "AdminBusinessList.do";
		}
		else if(commandName.equals("/WeddingBook_Goods/JSP/GoodsSearch.do"))
		{
			command = new GoodsSearchCommand();
			command.excute(request, response);
			viewPage = "GoodsSearch.jsp";
			flag = 1;
		}
		else if(commandName.equals("/AdminGoods/JSP/AdminBusinessList.do"))
		{
			command = new BusinessListCommand();
			command.excute(request, response);
			viewPage = "AdminBusinessList.jsp";
			flag = 1;
		}
		else if(commandName.equals("/AdminGoods/JSP/AdminBusinessView.do"))
		{
			command = new GoodsViewCommand();
			command.excute(request, response);
			viewPage = "AdminBusinessView.jsp";
			flag = 1;
		}
		
		//Goods Reservation
		else if(commandName.equals("/WeddingBook_Goods/JSP/ReservationOk.do"))
		{
			command = new ReservationOkCommand();
			command.excute(request, response);
			viewPage = "GoodsReservationList.do";
		}
		else if(commandName.equals("/AdminMember/JSP/AdminReservationList.do"))
		{
			command = new ReservationListCommand();
			command.excute(request, response);
			viewPage = "AdminReservationList.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Goods/JSP/GoodsReservationList.do"))
		{
			command = new ReservationListCommand();
			command.excute(request, response);
			viewPage = "GoodsReservationList.jsp";
			flag = 1;
		}
		else if(commandName.equals("/WeddingBook_Goods/JSP/DeleteReservation.do"))
		{
			command = new ReservationDeleteCommand();
			command.excute(request, response);
			viewPage = "GoodsReservationList.do";
			flag = 1;
		}
		
		
		if(flag == 0)
		{
			response.sendRedirect(viewPage);
		}
		else if(flag == 1)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
