package com.yr.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.yr.io.IODAO;
import com.yr.io.IODTO;
import com.yr.product.ProductDAO;
import com.yr.product.ProductDTO;
import com.yr.util.DBConnect;

public class DBConnectTest {

	// IOTest
	//@Test
	public void selectListTest2() throws Exception {
		IODAO dao = new IODAO();
		ArrayList<IODTO> ar = dao.select_list();
		assertNotEquals(0, ar.size());
	}

	
	
	
	@Test
	public void inserTest2(){
		
		int result = 0;

		IODAO idao = new IODAO();
		IODTO idto = new IODTO();
		idto.setPnum(4);
		idto.setIn_pct(50);
		idto.setIn_date("2019-04-06");
		Connection con = null;
		try {
		con = DBConnect.getConnect();
		
		
		con.setAutoCommit(false);
		result = idao.insert(idto, con);	

		if(result>0) {

			ProductDAO pdao = new ProductDAO();
			ProductDTO pdto = new ProductDTO();

			pdto.setPnum(122);
			pdto.setStock(idto.getIn_pct());
			result = pdao.update(pdto, con);
		}

		if(result>0) {
			con.commit();
		} else {
			con.rollback();
		}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				con.close();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(1, result);

	}




	// productTest


	//@Test
	public void updateTest() throws Exception {
		ProductDAO dao = new ProductDAO();

		//int result = dao.update(dto, con);
	}

	//@Test
	public void inserTest() throws Exception{
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
		dto.setCategory("Electronic");
		dto.setPname("Apple");
		dto.setPrice(300);
		dto.setStock(30);

		int result = dao.insert(dto);

	}
	//@Test
	public void selectListTest() throws Exception {
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> ar = dao.select_list();
		assertNotEquals(0, ar.size());
	}

	//@Test
	public void selectOneTest() throws Exception {
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = dao.select_one(2);
		assertNotNull(dto);

	}
	public void deleteTest() throws Exception {
		ProductDAO dao = new ProductDAO();

	}


	//@Test
	public void test() {
		try {
			Connection con = DBConnect.getConnect();
			assertNotNull(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
