package com.yr.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import com.yr.io.IODTO;
import com.yr.util.DBConnect;

public class ProductDAO {
	
	public int insert(ProductDTO dto) throws Exception {
		Connection con = DBConnect.getConnect();
		
		String sql = "insert into product values(product_seq.nextval,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		
		st.setString(1, dto.getCategory());
		st.setString(2, dto.getPname());
		st.setInt(3, dto.getPrice());
		st.setInt(4, dto.getStock());
		
		int result = st.executeUpdate();
		DBConnect.disConnect(st, con);
		
		return result;
	}
	
	public int update(ProductDTO dto, Connection con) throws Exception {

		
		
		String sql = "update product set stock=stock+? where pnum=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, dto.getStock());
		st.setInt(2, dto.getPnum());
		
		int result = st.executeUpdate();
		DBConnect.disConnect(st, con);
		return result;
		
	}
	
	public ArrayList<ProductDTO> select_list() throws Exception {
		Connection con = DBConnect.getConnect();
		
		String sql = "select * from product";
		
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		ProductDTO dto = null;
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		
		while (rs.next()) {
			dto = new ProductDTO();
			dto.setPnum(rs.getInt("pnum"));
			dto.setCategory(rs.getString("category"));
			dto.setPname(rs.getString("pname"));
			dto.setPrice(rs.getInt("price"));
			dto.setStock(rs.getInt("stock"));
			ar.add(dto);
		}
		DBConnect.disConnect(st, con, rs);
		return ar;
	}
	
	public ProductDTO select_one(int pnum) throws Exception {
		Connection con = DBConnect.getConnect();
		
		String sql = "select * from product where pnum=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, pnum);
		ResultSet rs = st.executeQuery();
		ProductDTO dto = null;
		
		if(rs.next()) {
			dto = new ProductDTO();
			dto.setPnum(rs.getInt("pnum"));
			dto.setCategory(rs.getString("category"));
			dto.setPname(rs.getString("pname"));
			dto.setPrice(rs.getInt("price"));
			dto.setStock(rs.getInt("stock"));
			
		}
		DBConnect.disConnect(st, con, rs);
		return dto;
	}
	
	
	
	
	
}
