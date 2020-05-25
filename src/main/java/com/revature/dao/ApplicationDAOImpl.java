package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Application;
import com.revature.util.ConnFactory;

public class ApplicationDAOImpl {
	
	public static ConnFactory cf = ConnFactory.getInstance();
	
	//get list
	public static List<Application> getInfo() throws SQLException{
		List<Application> appList = new ArrayList<Application>();
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM APPLICATION";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			appList.add(new Application(rs.getInt(1), 0, sql, null, sql, sql, null, sql, sql, sql, null, null, sql, sql, null, null));
		}
		return appList;
		
	}
	
	//insert row
	public void insertApp(Application app, Account employee) throws SQLException{
		
		Connection conn = cf.getConnection();
		//CALCULATE APPLYAMOUNT
		Double cost = app.getEventCost();
		String type = app.getTypeofEvent();
		Double amount = (double) 0;
		if (type.equals("University Courses")) {
		    amount = cost*0.8;
		}
		else if (type.equals("Seminars")){
			amount = cost*0.6;
		}
		else if (type.equals("Certification Preparation Classes")){
			amount = cost*0.75;
		}
		else if (type.equals("Certification")){
			amount = cost;
		}
		else if (type.equals("Technical Training")){
			amount = cost*0.9;
		}
		else if (type.equals("Other")){
			amount = cost*0.3;
		}
		
		String sql = "INSERT INTO APPLICATION VALUES(MYSEQ1.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,'PENDING','SUPERVISOR',?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,employee.getId());
		ps.setString(2,employee.getName());
		ps.setDate(3,app.getEventDate());
		ps.setString(4,app.getEventLocation());
		ps.setString(5,app.getEventDescription());
		ps.setDouble(6,app.getEventCost());
		ps.setString(7,app.getGradingFormat());
		ps.setString(8,app.getTypeofEvent());
		ps.setString(9,app.getJustification());
		ps.setBlob(10,app.getAttachment());
		ps.setBlob(11,app.getApproval());
		ps.setDouble(12,amount);
		ps.setTimestamp(13,new Timestamp(System.currentTimeMillis()));
		ps.executeUpdate();
	}

}
