package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Account;
import com.revature.util.ConnFactory;

public class LoginDAOImpl {
	
	public static ConnFactory cf = ConnFactory.getInstance();
	
	//get account information 
	public Account getAccount(Account account) throws SQLException{
		//boolean status = false;
		Account employee = null;
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_USERNAME = ? AND ACCOUNT_PASSWORD = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, account.getUsername());
	    ps.setString(2, account.getPassword());
	    System.out.println("in LoginDAOImpl");
	    ResultSet rs = ps.executeQuery();
	    //status = rs.next();
	    //return status;
	    while(rs.next()) {
	    	employee = new Account(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
	    }
	    return employee;
		
	}

}
