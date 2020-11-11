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

import com.weddingbook.www.dto.GoodsReservationDto;

public class GoodsReservationDao {
	private static GoodsReservationDao reserDao = new GoodsReservationDao();
	private String CONNECTION_POOL_RESOURCE_NAME = "jdbc/weddingbook";
	private final String RESERVATION_TABLE_NAME = "goodsreservation";
	private DataSource dataSource;
	private final String GET_RESERVATION_DTO_SQL = "SELECT * FROM " + RESERVATION_TABLE_NAME + " WHERE no=?";
	private final String SELECT_ALL_RESERVATION_SQL = "SELECT * FROM " + RESERVATION_TABLE_NAME + " ORDER BY no DESC";
	private final String INSERT_RESERVATION_SQL = "INSERT INTO " + RESERVATION_TABLE_NAME + "(goodsName, goodsBusinessTel, reservationName, reservationDate, reservationHours, memberId) VALUES(?, ?, ?, ?, ?, ?)";
	private final String DELETE_RESERVATION_SQL = "DELETE FROM " + RESERVATION_TABLE_NAME + " WHERE no=?";
	
	private GoodsReservationDao()
	{
		try
		{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/" + CONNECTION_POOL_RESOURCE_NAME);
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static GoodsReservationDao getGoodsReservationDao()
	{
		return reserDao;
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
	public GoodsReservationDto getGoodsReservationDto(GoodsReservationDto reserDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(GET_RESERVATION_DTO_SQL);
			System.out.println(GET_RESERVATION_DTO_SQL);
			pstmt.setInt(1, reserDto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				reserDto.setNo(rs.getInt("no"));
				reserDto.setMemberId(rs.getString("memberId"));
				reserDto.setGoodsName(rs.getString("goodsName"));
				reserDto.setGoodsBusinessTel(rs.getString("goodsBusibessTel"));
				reserDto.setReservationName(rs.getString("reservationName"));
				reserDto.setReservationDate(rs.getString("reservationDate"));
				reserDto.setReservationHours(rs.getString("reservationHours"));
				reserDto.setwTime(rs.getString("wTime"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt, conn);
		}
		return reserDto;
	}
	public void reservationOkDao(GoodsReservationDto reserDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(INSERT_RESERVATION_SQL);
			pstmt.setString(1, reserDto.getGoodsName());
			pstmt.setString(2, reserDto.getGoodsBusinessTel());
			pstmt.setString(3, reserDto.getReservationName());
			pstmt.setString(4, reserDto.getReservationDate());
			pstmt.setString(5, reserDto.getReservationHours());
			pstmt.setString(6, reserDto.getMemberId());
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
	public ArrayList<GoodsReservationDto> reserListDao()
	{
		ArrayList<GoodsReservationDto> reserList = new ArrayList<GoodsReservationDto>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(SELECT_ALL_RESERVATION_SQL);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				GoodsReservationDto reserDto = new GoodsReservationDto();
				reserDto.setNo(rs.getInt("no"));
				reserDto.setMemberId(rs.getString("memberId"));
				reserDto.setGoodsName(rs.getString("goodsName"));
				reserDto.setGoodsBusinessTel(rs.getString("goodsBusinessTel"));
				reserDto.setReservationName(rs.getString("reservationName"));
				reserDto.setReservationDate(rs.getString("reservationDate"));
				reserDto.setReservationHours(rs.getString("reservationHours"));
				reserDto.setwTime(rs.getString("wTime"));
				reserList.add(reserDto);
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
		return reserList;
	}
	public void deleteDao(int no)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(DELETE_RESERVATION_SQL);
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











