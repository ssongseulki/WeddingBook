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

import com.mysql.cj.protocol.ResultStreamer;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xml.internal.serialize.OutputFormat.DTD;
import com.weddingbook.www.dto.CommunityUsedGoodsDto;
import com.weddingbook.www.dto.MemberDto;

public class CommunityUsedGoodsDao {
	private static CommunityUsedGoodsDao usedDao = new CommunityUsedGoodsDao();
	private String CONNECTION_POOL_RESOURCE_NAME = "jdbc/weddingbook";
	private final String MEMBER_TABLE_NAME = "membership";
	private final String USED_GOODS_TABLE_NAME = "Community_usedGoods";
	private DataSource dataSource;
	private int sizeOfPage = 10;
	private final String GET_BOARD_DTO_SQL = "SELECT * FROM " + USED_GOODS_TABLE_NAME + " WHERE no=?";
	private final String SELECT_ALL_BOARD_SQL = "SELECT * FROM " + USED_GOODS_TABLE_NAME + " ORDER BY groupNum DESC, no LIMIT ?,?";
	private final String INSERT_BOARD_SQL = "INSERT INTO " + USED_GOODS_TABLE_NAME + "(nickName, title, contents, groupNum) VALUES(?, ?, ?, ?)";
	private final String GET_CURRENT_NUM_SQL = "SELECT MAX(no) FROM " + USED_GOODS_TABLE_NAME;
	private final String INCREASE_HIT_SQL = "UPDATE " + USED_GOODS_TABLE_NAME + " SET hit=hit+1 WHERE no=?";
	private final String UPDATE_BOARD_SQL = "UPDATE " + USED_GOODS_TABLE_NAME + " SET nickName=?, title=?, contents=?, wTime=NOW() WHERE no=?";
	private final String UPDATE_STEP_NUM_SQL = "UPDATE " + USED_GOODS_TABLE_NAME + " SET stepNum=stepNum+1 WHERE groupNum=? AND stepNum>=?";
	private final String INSERT_REPLY_SQL = "INSERT INTO " + USED_GOODS_TABLE_NAME + "(nickName, title, contents, groupNum, stepNum, indentNum) VALUES(?, ?, ?, ?, ?, ?)";
	private final String DELETE_SQL = "DELTE FROM " + USED_GOODS_TABLE_NAME + " WHERE no=?";
	private final String SELECT_WRITE_SQL = "SELECT nickName FROM " + MEMBER_TABLE_NAME + " WHERE id=?";
	private final String SELECT_COUNT_OF_LIST = "SELECT COUNT(*) FROM " + USED_GOODS_TABLE_NAME;
	private final String SEARCH_BOARD_SQL_BY_NAME = "SELECT * FROM " + USED_GOODS_TABLE_NAME + " WHERE nickName LIKE ? ORDER BY groupNum DESC, stepNum ASC";
	private final String SEARCH_BOARD_SQL_BY_TITLE = "SELECT * FROM " + USED_GOODS_TABLE_NAME + " WHERE title LIKE ? ORDER BY groupNum DESC, stepNum ASC";
	private final String SEARCH_BOARD_SQL_BY_TITLE_AND_CONTENTS = "SELECT * FROM " + USED_GOODS_TABLE_NAME + " WHERE title LIKE ? OR contents LIKE ? ORDER BY groupNum DESC, stepNum ASC";
	
	private CommunityUsedGoodsDao()
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
	public static CommunityUsedGoodsDao getCommunityUsedGoodsDao()
	{
		return usedDao;
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
	public CommunityUsedGoodsDto getUsedDto(CommunityUsedGoodsDto usedDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(GET_BOARD_DTO_SQL);
			System.out.println(GET_BOARD_DTO_SQL);
			pstmt.setInt(1, usedDto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				usedDto.setNo(rs.getInt("no"));
				usedDto.setId(rs.getString("id"));
				usedDto.setNickName(rs.getString("nickName"));
				usedDto.setTitle(rs.getString("title"));
				usedDto.setContents(rs.getString("contents"));
				usedDto.setHit(rs.getInt("hit"));
				usedDto.setwTime(rs.getString("wTime"));
				usedDto.setGroupNum(rs.getInt("groupNum"));
				usedDto.setStepNum(rs.getInt("stepNum"));
				usedDto.setIndentNum(rs.getInt("indentNum"));
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
		return usedDto;
	}
	public void increaseHit(int no)
	{
		Connection conn = getConnection();
		PreparedStatement pstnt = null;
		int result = 0;
		try
		{
			pstnt = conn.prepareStatement(INCREASE_HIT_SQL);
			pstnt.setInt(1, no);
			result = pstnt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			close(pstnt, conn);
		}
	}
	public int getCurrentNum()
	{
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
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
	 public MemberDto writeDao(MemberDto memberDto)
	 {
		 Connection conn = getConnection();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try
		 {
			 pstmt = conn.prepareStatement(SELECT_WRITE_SQL);
			 pstmt.setString(1, memberDto.getId());
			 rs = pstmt.executeQuery();
			 if(rs.next())
			 {
				 memberDto.setNickName(rs.getString("nickName"));
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
		 return memberDto;
	 }
	 public void writeOkDao(CommunityUsedGoodsDto usedDto)
	 {
		 Connection conn = getConnection();
		 PreparedStatement pstmt = null;
		 int curNum = getCurrentNum();
		 int result = 0;
		 try
		 {
			 pstmt = conn.prepareStatement(INSERT_BOARD_SQL);
			 pstmt.setString(1, usedDto.getNickName());
			 pstmt.setString(2, usedDto.getTitle());
			 pstmt.setString(3, usedDto.getContents());
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
	 public CommunityUsedGoodsDto modify(CommunityUsedGoodsDto usedDto)
	 {
		 getUsedDto(usedDto);
		 System.out.println(GET_BOARD_DTO_SQL);
		 return usedDto;
	 }
	 public void modifyOk(CommunityUsedGoodsDto usedDto)
	 {
		 Connection conn = getConnection();
		 PreparedStatement pstmt = null;
		 int result = 0;
		 try
		 {
			 pstmt = conn.prepareStatement(UPDATE_BOARD_SQL);
			 pstmt.setString(1, usedDto.getNickName());
			 pstmt.setString(2, usedDto.getTitle());
			 pstmt.setString(3, usedDto.getContents());
			 pstmt.setInt(4, usedDto.getNo());
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
	 public void updateStepNum(CommunityUsedGoodsDto usedDto)
	 {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 int result = 0;
		 conn = getConnection();
		 try
		 {
			 pstmt = conn.prepareStatement(UPDATE_STEP_NUM_SQL);
			 pstmt.setInt(1, usedDto.getGroupNum());
			 pstmt.setInt(2, usedDto.getStepNum());
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
	public CommunityUsedGoodsDto replyDao(CommunityUsedGoodsDto usedDto)
	{
		getUsedDto(usedDto);
		System.out.println(GET_BOARD_DTO_SQL);
		return usedDto;
	}
	public void replyOkDao(CommunityUsedGoodsDto usedDto)
	{
		updateStepNum(usedDto);
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		conn = getConnection();
		try
		{
			pstmt = conn.prepareStatement(INSERT_REPLY_SQL);
			pstmt.setString(1, usedDto.getNickName());
			pstmt.setString(2, usedDto.getTitle());
			pstmt.setString(3, usedDto.getContents());
			pstmt.setInt(4, usedDto.getGroupNum());
			pstmt.setInt(5, usedDto.getStepNum());
			pstmt.setInt(6, usedDto.getIndentNum());
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
	public int cntOfList()
	{
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(SELECT_COUNT_OF_LIST);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				cnt = rs.getInt(1);
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
		return cnt;
	}
	public int calTotalPage()
	{
		int cnt = cntOfList();
		return cnt / sizeOfPage;
	}
	public ArrayList<CommunityUsedGoodsDto> usedListDao(int curPage)
	{
		ArrayList<CommunityUsedGoodsDto> usedList = new ArrayList<CommunityUsedGoodsDto>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(SELECT_ALL_BOARD_SQL);
			pstmt.setInt(1, curPage*sizeOfPage);
			pstmt.setInt(2, sizeOfPage);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				CommunityUsedGoodsDto usedDto = new CommunityUsedGoodsDto();
				usedDto.setNo(rs.getInt("no"));
				usedDto.setNickName(rs.getString("nickName"));
				usedDto.setTitle(rs.getString("title"));
				usedDto.setContents(rs.getString("contents"));
				usedDto.setHit(rs.getInt("hit"));
				usedDto.setwTime(rs.getString("wTime"));
				usedDto.setGroupNum(rs.getInt("groupNum"));
				usedDto.setStepNum(rs.getInt("stepNum"));
				usedDto.setIndentNum(rs.getInt("indentNum"));
				usedList.add(usedDto);
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
		return usedList;
	}
	public CommunityUsedGoodsDto usedViewDao(CommunityUsedGoodsDto usedDto)
	{
		increaseHit(usedDto.getNo());
		usedDto = getUsedDto(usedDto);
		return usedDto;
	}
	public ArrayList<CommunityUsedGoodsDto> search(String kindOfSearch, String searchKeyword)
	{
		ArrayList<CommunityUsedGoodsDto> usedList = new ArrayList<CommunityUsedGoodsDto>();
		Connection conn = getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try
		{
			if(kindOfSearch.equals("searchName"))
			{
				pstmt = conn.prepareStatement(SEARCH_BOARD_SQL_BY_NAME);
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			else if(kindOfSearch.equals("searchTitle"))
			{
				pstmt = conn.prepareStatement(SEARCH_BOARD_SQL_BY_TITLE);
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			else if(kindOfSearch.equals("searchTitleAndContents"))
			{
				pstmt = conn.prepareStatement(SEARCH_BOARD_SQL_BY_TITLE_AND_CONTENTS);
				pstmt.setString(1, "%" + searchKeyword + "%");
				pstmt.setString(2, "%" + searchKeyword + "%");
			}
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				CommunityUsedGoodsDto usedDto = new CommunityUsedGoodsDto();
				usedDto.setNo(rs.getInt("no"));
				usedDto.setNickName(rs.getString("nickName"));
				usedDto.setTitle(rs.getString("title"));
				usedDto.setContents(rs.getString("contents"));
				usedDto.setHit(rs.getInt("hit"));
				usedDto.setwTime(rs.getString("wTime"));
				usedDto.setGroupNum(rs.getInt("groupNum"));
				usedDto.setStepNum(rs.getInt("stepNum"));
				usedDto.setIndentNum(rs.getInt("indentNum"));
				usedList.add(usedDto);
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
		return usedList;
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






