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

import com.weddingbook.www.dto.MemberDto;

public class MemberDao {
	private static MemberDao memberDao = new MemberDao();
	private String CONNECTION_POOL_RESOURCE_NAME = "jdbc/weddingbook";
	private final String TABLE_NAME = "Membership";
	private DataSource dataSource;
	
	private final String SELECT_LOGIN_SQL = "SELECT * FROM " + TABLE_NAME + " WHERE id=? AND password=?";
	private final String INSERT_MEMBER_REGISTER_SQL = "INSERT INTO " + TABLE_NAME + " (name, id, nickName, password, phone1, phone2, phone3, email, gender, weddingDate, local) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String GET_MEMBER_DTO_SQL = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
	private final String UPDATE_MEMBER_MODIFY_SQL = "UPDATE " + TABLE_NAME + " SET nickName=?, password=?, phone1=?, phone2=?, phone3=?, email=?, gender=?, weddingDate=?, local=? WHERE id=?";
	private final String SELECT_MODIFY_SQL = "SELECT name, id, nickName, phone2, phone3, email, weddingDate FROM " + TABLE_NAME + " WHERE id=?";
	private final String SELECT_ALL_MEMBER_SQL = "SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC";
	private final String DELETE_MEMBER_SQL = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
	private final String DUPLECATE_ID_SQL = "SELECT id FROM " + TABLE_NAME;
	
	private final String SEARCH_MEMBER_SQL_BY_NAME = "SELECT * FROM " + TABLE_NAME + " WHERE name LIKE ?";
	private final String SEARCH_MEMBER_SQL_BY_PHONE = "SELECT * FROM " + TABLE_NAME + " WHERE phone1 LIKE ? OR phone2 LIKE ? OR phone3 LIKE ?";
	private final String SEARCH_MEMBER_SQL_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id LIKE ?";
	
	private MemberDao()
	{
		try 
		{
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + CONNECTION_POOL_RESOURCE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static MemberDao getMemberDao() 
	{
		return memberDao;
	}
	
	public Connection getConnection()
	{
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn)
	{
		try 
		{
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(PreparedStatement pstmt, Connection conn)
	{
		try 
		{
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public MemberDto getMemberDto(String id)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto memberDto = new MemberDto();
		try
		{
			pstmt = conn.prepareStatement(GET_MEMBER_DTO_SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				memberDto.setName(rs.getString("name"));
				memberDto.setId(rs.getString("id"));
				memberDto.setNickName(rs.getString("nickName"));
				memberDto.setPassword(rs.getString("password"));
				memberDto.setPhone1(rs.getString("phone1"));
				memberDto.setPhone2(rs.getString("phone2"));
				memberDto.setPhone3(rs.getString("phone3"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setGender(rs.getString("gender"));
				memberDto.setWeddingDate(rs.getString("weddingDate"));
				memberDto.setLocal(rs.getString("local"));
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
	
	public void loginOK(MemberDto memberDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = conn.prepareStatement(SELECT_LOGIN_SQL);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				memberDto.setName(rs.getString("name"));
				memberDto.setId(rs.getString("id"));
				memberDto.setNickName(rs.getString("nickName"));
				memberDto.setPassword(rs.getString("password"));
				memberDto.setPhone1(rs.getString("phone1"));
				memberDto.setPhone2(rs.getString("phone2"));
				memberDto.setPhone3(rs.getString("phone3"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setGender(rs.getString("gender"));
				memberDto.setWeddingDate(rs.getString("weddingDate"));
				memberDto.setLocal(rs.getString("local"));
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
	}
	
	public void memberRegisterDao(MemberDto memberDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(INSERT_MEMBER_REGISTER_SQL);
			pstmt.setString(1, memberDto.getName());
			pstmt.setString(2, memberDto.getId());
			pstmt.setString(3, memberDto.getNickName());
			pstmt.setString(4, memberDto.getPassword());
			pstmt.setString(5, memberDto.getPhone1());
			pstmt.setString(6, memberDto.getPhone2());
			pstmt.setString(7, memberDto.getPhone3());
			pstmt.setString(8, memberDto.getEmail());
			pstmt.setString(9, memberDto.getGender());
			pstmt.setString(10, memberDto.getWeddingDate());
			pstmt.setString(11, memberDto.getLocal());
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			close(pstmt, conn);
		}
	}
	
	public MemberDto memberModify(MemberDto memberDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(SELECT_MODIFY_SQL);
			pstmt.setString(1, memberDto.getId());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				memberDto.setName(rs.getString("name"));
				memberDto.setId(rs.getString("id"));
				memberDto.setNickName(rs.getString("nickName"));
				memberDto.setPhone2(rs.getString("phone2"));
				memberDto.setPhone3(rs.getString("phone3"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setWeddingDate(rs.getString("weddingDate"));
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
		return memberDto;
	}
	
	public void memberModifyOK(MemberDto memberDto)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(UPDATE_MEMBER_MODIFY_SQL);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getNickName());
			pstmt.setString(3, memberDto.getPassword());
			pstmt.setString(4, memberDto.getPhone1());
			pstmt.setString(5, memberDto.getPhone2());
			pstmt.setString(6, memberDto.getPhone3());
			pstmt.setString(7, memberDto.getEmail());
			pstmt.setString(8, memberDto.getGender());
			pstmt.setString(9, memberDto.getWeddingDate());
			pstmt.setString(10, memberDto.getLocal());
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
	
	public ArrayList<MemberDto> listDao()
	{
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(SELECT_ALL_MEMBER_SQL);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				MemberDto memberDto = new MemberDto();
				memberDto.setName(rs.getString("name"));
				memberDto.setNickName(rs.getString("nickName"));
				memberDto.setId(rs.getString("id"));
				memberDto.setPhone1(rs.getString("phone1"));
				memberDto.setPhone2(rs.getString("phone2"));
				memberDto.setPhone3(rs.getString("phone3"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setGender(rs.getString("gender"));
				memberDto.setWeddingDate(rs.getString("weddingDate"));
				list.add(memberDto);
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
		return list;
	}
	
	public void deleteDao(String id)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try
		{
			pstmt = conn.prepareStatement(DELETE_MEMBER_SQL);
			pstmt.setString(1, id);
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
	
	public ArrayList<MemberDto> search(String kindOfSearch, String searchKeyword)
	{
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			if(kindOfSearch.equals("searchName"))
			{
				pstmt = conn.prepareStatement(SEARCH_MEMBER_SQL_BY_NAME);
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			else if(kindOfSearch.equals("searchId"))
			{
				pstmt = conn.prepareStatement(SEARCH_MEMBER_SQL_BY_ID);
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			else if(kindOfSearch.equals("searchPhone"))
			{
				pstmt = conn.prepareStatement(SEARCH_MEMBER_SQL_BY_PHONE);
				pstmt.setString(1, "%" + searchKeyword + "%");
				pstmt.setString(2, "%" + searchKeyword + "%");
				pstmt.setString(3, "%" + searchKeyword + "%");
			}
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				MemberDto memberDto = new MemberDto();
				memberDto.setName(rs.getString("name"));
				memberDto.setNickName(rs.getString("nickName"));
				memberDto.setId(rs.getString("id"));
				memberDto.setPhone1(rs.getString("phone1"));
				memberDto.setPhone2(rs.getString("phone2"));
				memberDto.setPhone3(rs.getString("phone3"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setGender(rs.getString("gender"));
				memberDto.setWeddingDate(rs.getString("weddingDate"));
				list.add(memberDto);
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
		return list;
	}
	
	public int duplecateID(String id)
	{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = conn.prepareStatement(DUPLECATE_ID_SQL);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				if(id.equals(rs.getString("id")))
					return -1;
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
		return 0;
	}
}








