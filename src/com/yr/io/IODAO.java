package com.yr.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import javax.security.sasl.SaslClient;

import com.yr.util.DBConnect;

public class IODAO {

	public int insert(IODTO dto, Connection con) throws Exception{
		
		String sql = "insert into io values (io_seq.nextval,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, dto.getPnum());
		st.setInt(2, dto.getIn_pct());
		st.setString(3, dto.getIn_date());
		st.setInt(4, dto.getOut_pct());
		st.setString(5, dto.getOut_date());
		
		int result = st.executeUpdate();
		DBConnect.disConnect(st, con);
		return result;
	
	
	}
	
	public ArrayList<IODTO> select_list() throws Exception {
		Connection con = DBConnect.getConnect();
		
		String sql = "select * from io";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		IODTO dto = null;
		ArrayList<IODTO> ar = new ArrayList<IODTO>();
		
		while(rs.next()) {
			dto = new IODTO();
			
			dto.setNum(rs.getInt("num"));
			dto.setPnum(rs.getInt("pnum"));
			dto.setIn_pct(rs.getInt("in_pct"));
			dto.setIn_date(rs.getString("in_date"));
			dto.setOut_pct(rs.getInt("out_pct"));
			dto.setOut_date(rs.getString("out_date"));
			
			ar.add(dto);
		}
		DBConnect.disConnect(st, con, rs);
		return ar;
	}
	
	public IODTO select_one(int num) throws Exception{
		
		Connection con = DBConnect.getConnect();
		
		String sql = "select * from where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		
		IODTO dto = null;
		
		if(rs.next()) {
			dto = new IODTO();
			dto.setNum(rs.getInt("num"));
			dto.setPnum(rs.getInt("pnum"));
			dto.setIn_pct(rs.getInt("in_pct"));
			dto.setIn_date(rs.getString("in_date"));
			dto.setOut_pct(rs.getInt("out_pct"));
			dto.setOut_date(rs.getString("out_date"));
		
		}
		
		DBConnect.disConnect(st, con, rs);
		return dto;
		
		
	}
	
	public int update(IODTO dto) throws Exception {
		Connection con = DBConnect.getConnect();
		Scanner sc = new Scanner(System.in);
		
		String sql = "update io set in_pct=?, in_date=?, out_pct=?, out_date=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, dto.getIn_pct());
		st.setString(2, dto.getIn_date());
		st.setInt(3, dto.getOut_pct());
		st.setString(4, dto.getOut_date());
		
		int result = st.executeUpdate();
		DBConnect.disConnect(st, con);
		return result;
	}
	
	public int delete(int num) throws Exception {
		Connection con = DBConnect.getConnect();
		String sql = "delete io where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		DBConnect.disConnect(st, con);
		
		return result;
	}
	
	
	
	
	
	
}
