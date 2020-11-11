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

import com.sun.prism.paint.RadialGradient;
import com.weddingbook.www.dto.GoodsDto;

import javafx.css.PseudoClass;

public class GoodsDao {
	private static GoodsDao goodsDao = new GoodsDao();
	private String CONNECTION_POOL_RESOURCE_NAME = "jdbc/weddingbook";
	private final String GOODS_TABLE_NAME = "Goods";
	private DataSource dataSource;
	private final String GET_GOODS_DTO_SQL = "SELECT * FROM " + GOODS_TABLE_NAME + " WHERE no=?";
	private final String SELECT_ALL_GOODS_SQL = "SELECT * FROM " + GOODS_TABLE_NAME + " ORDER BY no DESC";
	private final String INSERT_GOODS_SQL = "INSERT INTO " + GOODS_TABLE_NAME + "(goodsSDM, goodsName, goodsPosition, goodsPromotion, goodsInfo, goodsBusinessHours,goodsBusinessTel, goodsBusinessAdd, goodsHomepageAdd,"
			+ "goodsFile1, goodsFile2, goodsFile3, goodsFile4, goodsFile5) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	private final String UPDATE_GOODS_SQL = "UPDATE " + GOODS_TABLE_NAME + " SET goodsSDM=?, goodsName=?, goodsPosition=?, goodsPromotion=?, goodsInfo=?, goodsBusinessHours=?, goodsBusinessTel=?, goodsBusinessAdd=?, goodsHomepageAdd=? WHERE no=?";
	private final String DELETE_GOODS_SQL = "DELETE FROM " + GOODS_TABLE_NAME + " WHERE no=?";
	private final String SEARCH_GOODS_SQL = "SELECT * FROM " + GOODS_TABLE_NAME + " WHERE goodsSDM LIKE '%studio%' OR goodsSDM LIKE '%dress%' OR goodsSDM LIKE '%makeUp% ORDER BY no DESC";
	
	private GoodsDao()
	{
		try
		{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/" + CONNECTION_POOL_RESOURCE_NAME);
		}catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}
	public static GoodsDao getGoodsDao()
	{
		return goodsDao;
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
	public GoodsDto getGoodsDto(GoodsDto goodsDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(GET_GOODS_DTO_SQL);
			System.out.println(GET_GOODS_DTO_SQL);
			pstmt.setInt(1, goodsDto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				goodsDto.setNo(rs.getInt("no"));
				goodsDto.setGoodsSDM(rs.getString("goodsSDM"));
				goodsDto.setGoodsName(rs.getString("goodsName"));
				goodsDto.setGoodsPosition(rs.getString("goodsPosition"));
				goodsDto.setGoodsFile1(rs.getString("goodsFile1"));
				goodsDto.setGoodsFile2(rs.getString("goodsFile2"));
				goodsDto.setGoodsFile3(rs.getString("goodsFile3"));
				goodsDto.setGoodsFile4(rs.getString("goodsFile4"));
				goodsDto.setGoodsFile5(rs.getString("goodsFile5"));
				goodsDto.setGoodsPromotion(rs.getString("goodspromotion"));
				goodsDto.setGoodsInfo(rs.getString("goodsInfo"));
				goodsDto.setGoodsBusinessHours(rs.getString("goodsBusinessHours"));
				goodsDto.setGoodsBusinessTel(rs.getString("goodsBusinessTel"));
				goodsDto.setGoodsBusinessAdd(rs.getString("goodsBusinessAdd"));
				goodsDto.setGoodsHomepageAdd(rs.getString("goodsHomepageAdd"));
				goodsDto.setGoodsReservationName(rs.getString("goodsReservationName"));
				goodsDto.setGoodsReservationDate(rs.getString("goodsReservationDate"));
				goodsDto.setGoodsReservationHours(rs.getString("goodsReservationHours"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			cloas(rs, pstmt, conn);
		}
		return goodsDto;
	}
	public void writeOkDao(GoodsDto goodsDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(INSERT_GOODS_SQL);
			pstmt.setString(1, goodsDto.getGoodsSDM());
			pstmt.setString(2, goodsDto.getGoodsName());
			pstmt.setString(3, goodsDto.getGoodsPosition());
			pstmt.setString(4, goodsDto.getGoodsPromotion());
			pstmt.setString(5, goodsDto.getGoodsInfo());
			pstmt.setString(6, goodsDto.getGoodsBusinessHours());
			pstmt.setString(7, goodsDto.getGoodsBusinessTel());
			pstmt.setString(8, goodsDto.getGoodsBusinessAdd());
			pstmt.setString(9, goodsDto.getGoodsHomepageAdd());
			pstmt.setString(10, goodsDto.getGoodsFile1());
			pstmt.setString(11, goodsDto.getGoodsFile2());
			pstmt.setString(12, goodsDto.getGoodsFile3());
			pstmt.setString(13, goodsDto.getGoodsFile4());
			pstmt.setString(14, goodsDto.getGoodsFile5());
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
	public ArrayList<GoodsDto> goodsListDao()
	{
		ArrayList<GoodsDto> goodsList = new ArrayList<GoodsDto>();
		Connection conn =  getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(SELECT_ALL_GOODS_SQL);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				GoodsDto goodsDto = new GoodsDto();
				goodsDto.setNo(rs.getInt("no"));
				goodsDto.setGoodsSDM(rs.getString("goodsSDM"));
				goodsDto.setGoodsFile1(rs.getString("goodsFile1"));
				goodsDto.setGoodsName(rs.getString("goodsName"));
				goodsDto.setGoodsPosition(rs.getString("goodsPosition"));
				goodsList.add(goodsDto);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			cloas(rs, pstmt, conn);
		}
		return goodsList;
	}
	public ArrayList<GoodsDto> busiListDao()
	{
		ArrayList<GoodsDto> busiList = new ArrayList<GoodsDto>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(SELECT_ALL_GOODS_SQL);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				GoodsDto goodsDto = new GoodsDto();
				goodsDto.setNo(rs.getInt("no"));
				goodsDto.setGoodsSDM(rs.getString("goodsSDM"));
				goodsDto.setGoodsName(rs.getString("goodsName"));
				goodsDto.setGoodsPosition(rs.getString("goodsPosition"));
				goodsDto.setGoodsBusinessTel(rs.getString("goodsBusinessTel"));
				busiList.add(goodsDto);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			cloas(rs, pstmt, conn);
		}
		return busiList;
	}
	public GoodsDto viewDao(GoodsDto goodsDto)
	{
		goodsDto = getGoodsDto(goodsDto);
		return goodsDto;
	}
	public GoodsDto modify(GoodsDto goodsDto)
	{
		goodsDto = getGoodsDto(goodsDto);
		System.out.println(GET_GOODS_DTO_SQL);
		return goodsDto;
	}
	public void modifyOk(GoodsDto goodsDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(UPDATE_GOODS_SQL);
			pstmt.setString(1, goodsDto.getGoodsSDM());
			pstmt.setString(2, goodsDto.getGoodsName());
			pstmt.setString(3, goodsDto.getGoodsPosition());
			pstmt.setString(4, goodsDto.getGoodsPromotion());
			pstmt.setString(5, goodsDto.getGoodsInfo());
			pstmt.setString(6, goodsDto.getGoodsBusinessHours());
			pstmt.setString(7, goodsDto.getGoodsBusinessTel());
			pstmt.setString(8, goodsDto.getGoodsBusinessAdd());
			pstmt.setString(9, goodsDto.getGoodsHomepageAdd());
			pstmt.setInt(10, goodsDto.getNo());
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
			pstmt = conn.prepareStatement(DELETE_GOODS_SQL);
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
	public ArrayList<GoodsDto> search(String kindOfSearch, String studio, String dress, String makeup)
	{
		ArrayList<GoodsDto> goodsList = new ArrayList<GoodsDto>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			if(kindOfSearch.equals("goodsSDM"))
			{
				pstmt = conn.prepareStatement(SEARCH_GOODS_SQL);
				pstmt.setString(1, "%studio%");
				pstmt.setString(2, "%dress%");
				pstmt.setString(3, "%makeup%");
			}
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				GoodsDto goodsDto = new GoodsDto();
				goodsDto.setNo(rs.getInt("no"));
				goodsDto.setGoodsSDM(rs.getString("goodsSDM"));
				goodsDto.setGoodsFile1(rs.getString("goodsFile1"));
				goodsDto.setGoodsName(rs.getString("goodsName"));
				goodsDto.setGoodsPosition(rs.getString("goodsPosition"));
				goodsList.add(goodsDto);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			cloas(rs, pstmt, conn);
		}
		return goodsList;
	}
	//close
	public void cloas(ResultSet rs, PreparedStatement pstmt, Connection conn)
	{
		try
		{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null)conn.close();
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








