package com.sunbeam.dao;

import java.sql.Connection;

import com.sunbeam.util.DBUtill;

public class Dao implements AutoCloseable{
	protected Connection con;
	
	protected Dao() throws Exception{
		con = DBUtill.getConnection();
	}
	
	@Override
	public void close() throws Exception {
		try {
			if (con != null)
				con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
