package com.weddingbook.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sun.org.apache.bcel.internal.generic.DLOAD;
import com.weddingbook.www.dto.CommunityBoardDto;
import com.weddingbook.www.dto.CommunityCommentDto;

public class CommunityBoardDao {
	private static CommunityBoardDao boardDao = new CommunityBoardDao();
	private String CONNECTION_POOL_RESOURCE_NAME = "jdbc/weddingbook";
	private final String BOARD_TABLE_NAME = "community_board";
	private DataSource dataSource;
	private final String GET_BOARD_DTO_SQL = "SELECT * FROM " + BOARD_TABLE_NAME + " WHERE no=?";
	private final String SELECT_ALL_BOARD_SQL = "SELECT * FROM " + BOARD_TABLE_NAME + " ORDER BY groupNum DESC, stepNum ASC";
	private final String SELECT_POPUP_BOARD_SQL = "SELECT * FROM " + BOARD_TABLE_NAME;
	private final String INSERT_BOARD_SQL = "INSERT INTO " + BOARD_TABLE_NAME + "(nickName, weddingDate, contents, groupNum) VALUES(?, ?, ?, ?)";
	private final String GET_CURRENT_NUM_SQL = "SELECT MAX(no) FROM " + BOARD_TABLE_NAME;
	private final String UPDATE_BOARD_SQL = "UPDATE " + BOARD_TABLE_NAME + " SET contents=?, wTime=now() WHERE no=?";
	private final String UPDATE_STEP_NUM_SQL = "UPDATE " + BOARD_TABLE_NAME + " SET stepNum=stepNum+1 WHERE groupNum=? and stepNum>=?";
	private final String INSERT_REPLY_SQL = "INSERT INTO " + BOARD_TABLE_NAME + " (nickName, contents, groupNum, stepNum, indentNum) VALUES(?, ?, ?, ?, ?)";
	private final String DELETE_SQL = "DELETE FROM " + BOARD_TABLE_NAME + " WHERE no=?";
	
	private final String COMMENT_TABLE_NAME = "comment";
	private final String DELETE_BOARD_COMMENT_SQL = "DELETE FROM " + COMMENT_TABLE_NAME + " WHERE no=?";
	private final String SELECT_ALL_COMMENT_SQL = "SELECT * FROM " + COMMENT_TABLE_NAME + " WHERE no=? ORDER BY wTime DESC";
	private final String INSERT_COMMENT_SQL = "INSERT INTO " + COMMENT_TABLE_NAME + "(name, comment, no) VALUES(?, ?, ?)";
	private final String COMMENT_CNT_SQL = "SELECT COUNT(*) FROM " + COMMENT_TABLE_NAME + " WHERE no=?";
	private final String UPDATE_COMMENT_CNT_SQL = "UPDATE " + COMMENT_TABLE_NAME + " SET commentCnt=? WHERE no=?";
	private final String UPDATE_COMMENT_SQL = "UPDATE " + COMMENT_TABLE_NAME + " SET comment=?, wTime=NOW() WHERE commentNo=?";
	private final String DELETE_COMMENT_SQL = "DELETE FROM " + COMMENT_TABLE_NAME + " WHERE commentNo=?";
	
	//Community Board
	private CommunityBoardDao()
	{
		try
		{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/" + CONNECTION_POOL_RESOURCE_NAME);
		}
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}
	public static CommunityBoardDao getBoardDao()
	{
		return boardDao;
	}
	public Connection getConnection()
	{
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return conn;
	}
	public  CommunityBoardDto getCommunityBoardDto(CommunityBoardDto boardDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(GET_BOARD_DTO_SQL);
			System.out.println(GET_BOARD_DTO_SQL);
			pstmt.setInt(1, boardDto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				boardDto.setNo(rs.getInt("no"));
				boardDto.setNickName(rs.getString("nickName"));
				boardDto.setWeddingDate(rs.getString("weddingDate"));
				boardDto.setContents(rs.getString("contents"));
				boardDto.setwTime(rs.getString("wTime"));
				boardDto.setGroupNum(rs.getInt("groupNum"));
				boardDto.setStepNum(rs.getInt("stepNum"));
				boardDto.setIndentNum(rs.getInt("indentNum"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			close(rs, pstmt, conn);
		}
		return boardDto;
	}
	
	public ArrayList<CommunityBoardDto> boardListDao()
	{
		ArrayList<CommunityBoardDto> boardList = new ArrayList<CommunityBoardDto>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(SELECT_ALL_BOARD_SQL);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				CommunityBoardDto boardDto = new CommunityBoardDto();
				boardDto.setNo(rs.getInt("no"));
				boardDto.setWeddingDate(rs.getString("wTime"));
				boardDto.setNickName(rs.getString("nickName"));
				boardDto.setWeddingDate(rs.getString("weddingDate"));
				boardDto.setContents(rs.getString("contents"));
				boardDto.setGroupNum(rs.getInt("groupNum"));
				boardDto.setStepNum(rs.getInt("stepNum"));
				boardDto.setIndentNum(rs.getInt("indentNum"));
				boardList.add(boardDto);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			close(rs, pstmt, conn);
		}
		return boardList;
	}
	public int getCurrentNum()
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int curNum = 0;
		try
		{
			pstmt = conn.prepareStatement(GET_CURRENT_NUM_SQL);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				curNum = rs.getInt(1);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs, pstmt, conn);
		}
		return curNum+1;
	}
	public void writeOk(CommunityBoardDto boardDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int curNum = getCurrentNum();
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(INSERT_BOARD_SQL);
			pstmt.setString(1, boardDto.getNickName());
			pstmt.setString(2, boardDto.getWeddingDate());
			pstmt.setString(3, boardDto.getContents());
			pstmt.setInt(4, curNum);
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt, conn);
		}
	}
	public void modifyOk(CommunityBoardDto boardDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(UPDATE_BOARD_SQL);
			pstmt.setString(1, boardDto.getContents());
			pstmt.setInt(2, boardDto.getNo());
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			close(pstmt, conn);
		}
	}
	public void updateStepNum(CommunityBoardDto boardDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(UPDATE_STEP_NUM_SQL);
			pstmt.setInt(1, boardDto.getGroupNum());
			pstmt.setInt(2, boardDto.getStepNum());
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt, conn);
		}
	}
	public CommunityBoardDto replyDao(CommunityBoardDto boardDto)
	{
		getCommunityBoardDto(boardDto);
		return boardDto;
	}
	public void replyOkDao(CommunityBoardDto boardDto)
	{
		updateStepNum(boardDto);
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(INSERT_REPLY_SQL);
			pstmt.setString(1, boardDto.getNickName());
			pstmt.setString(2, boardDto.getContents());
			pstmt.setInt(3, boardDto.getGroupNum());
			pstmt.setInt(4, boardDto.getStepNum());
			pstmt.setInt(5, boardDto.getIndentNum());
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt, conn);
		}
	}
	public void deleteDao(int no)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		deleteBoardComment(no);
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			close(pstmt, conn);
		}
	}
	public void deleteBoardComment(int no)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(DELETE_BOARD_COMMENT_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt, conn);
		}
	}
	public CommunityBoardDto commentPopupDao(CommunityBoardDto boardDto)
	{
		boardDto = getCommunityBoardDto(boardDto);
		return boardDto;
	}
	
	//Community Board Comment
	public ArrayList<CommunityCommentDto> commentListDao(int no)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommunityCommentDto> commentList = new ArrayList<CommunityCommentDto>();
		try
		{
			pstmt = conn.prepareStatement(SELECT_ALL_COMMENT_SQL);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				CommunityCommentDto commentDto = new CommunityCommentDto();
				commentDto.setNo(rs.getInt("no"));
				commentDto.setCommentNo(rs.getInt("commentNo"));
				commentDto.setCommentName(rs.getString("name"));
				commentDto.setComment(rs.getString("comment"));
				commentDto.setwTime(rs.getString("wTime"));
				commentList.add(commentDto);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			close(rs, pstmt, conn);
		}
		return commentList;
	}
	public void commentOkDao(CommunityCommentDto commentDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(INSERT_COMMENT_SQL);
			pstmt.setString(1, commentDto.getCommentName());
			pstmt.setString(2, commentDto.getComment());
			pstmt.setInt(3, commentDto.getNo());
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt, conn);
		}
	}
	public CommunityCommentDto boardCommentCnt(int no)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		updateCommentCnt(no);
		CommunityCommentDto commentDto = new CommunityCommentDto();
		try
		{
			pstmt = conn.prepareStatement(SELECT_ALL_COMMENT_SQL);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				commentDto.setCommentCnt(rs.getInt("commentCnt"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs, pstmt, conn);
		}
		return commentDto;
	}
	public void updateCommentCnt(int no)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		int commentCnt = getCommentCnt(no);
		try
		{
			pstmt = conn.prepareStatement(UPDATE_COMMENT_CNT_SQL);
			pstmt.setInt(1, commentCnt);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			close(pstmt, conn);
		}
	}
	public int getCommentCnt(int no)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int commentCnt = 0;
		try
		{
			pstmt = conn.prepareStatement(COMMENT_CNT_SQL);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				commentCnt = rs.getInt(1);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs, pstmt, conn);
		}
		return commentCnt;
	}
	public void commentModifyOkDao(CommunityCommentDto commentDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(UPDATE_COMMENT_SQL);
			pstmt.setString(1, commentDto.getComment());
			pstmt.setInt(2, commentDto.getNo());
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt, conn);
		}
	}
	public void commentDeleteDat(int commentNo)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(DELETE_COMMENT_SQL);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt, conn);
		}
	}

	//close
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn)
	{
		try
		{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public void close(PreparedStatement pstmt, Connection conn)
	{
		try
		{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
