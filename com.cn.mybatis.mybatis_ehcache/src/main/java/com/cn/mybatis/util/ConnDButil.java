package com.cn.mybatis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class ConnDButil {
	public static void main(String[] args) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr?/charactertEncoding=utf-8", "root", "root");
			String sql = "select * from user where id=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("id")+" "+rs.getString("username"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ps!=null) {
			ps.close();
		}
		if (rs!=null) {
			conn.close();
		}
		
	}
	
	
	

}
