package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Application;
import com.revature.servlet.LoginServlet;
import com.revature.util.ConnFactory;

public class ApplicationDAOImpl {
	
	public static ConnFactory cf = ConnFactory.getInstance();
	Account employee = LoginServlet.employee;
	
	//get list on the supervisor/department/benco page
	public List<Application> getSuperInfo(Account employee) throws SQLException{
		System.out.println(employee);
		List<Application> appList = new ArrayList<Application>();
		Connection conn = cf.getConnection();
		String account_type = employee.getType().toUpperCase();
		String sql = "SELECT * FROM APPLICATION WHERE SUBSTATUS='"+account_type+"'AND STATUS = 'PENDING' ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			appList.add(new Application(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), 
					rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getBlob(11), 
					rs.getBlob(12), rs.getString(13), rs.getString(14), rs.getDouble(15), rs.getTimestamp(16), 
					rs.getString(17), rs.getString(18), rs.getString(19), rs.getDouble(20), rs.getString(21)));
		}
		System.out.println(appList);
		return appList;
		
	}
	
	//get pending list on the profile page
	public List<Application> getmyInfo(Account employee) throws SQLException{
			System.out.println(employee);
			List<Application> appList = new ArrayList<Application>();
			Connection conn = cf.getConnection();
			int account_id = employee.getId();
			String sql = "SELECT * FROM APPLICATION WHERE EMPLOYEE_ID='"+account_id+"'AND STATUS = 'PENDING' ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				appList.add(new Application(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), 
						rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getBlob(11), 
						rs.getBlob(12), rs.getString(13), rs.getString(14), rs.getDouble(15), rs.getTimestamp(16), 
						rs.getString(17), rs.getString(18), rs.getString(19), rs.getDouble(20), rs.getString(21)));
			}
			System.out.println(appList);
			return appList;		
		}
	
	//get approved list on the profile page
	public List<Application> getmyInfo2(Account employee) throws SQLException{
			System.out.println(employee);
			List<Application> appList = new ArrayList<Application>();
			Connection conn = cf.getConnection();
			int account_id = employee.getId();
			String sql = "SELECT * FROM APPLICATION WHERE EMPLOYEE_ID='"+account_id+"'AND STATUS = 'PROVED' ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				appList.add(new Application(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), 
						rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getBlob(11), 
						rs.getBlob(12), rs.getString(13), rs.getString(14), rs.getDouble(15), rs.getTimestamp(16), 
						rs.getString(17), rs.getString(18), rs.getString(19), rs.getDouble(20), rs.getString(21)));
			}
			System.out.println(appList);
			return appList;		
		}

    //get grade/presentation on the benco/supervisor page
	public List<Application> getBencoInfo(Account employee) throws SQLException{
			System.out.println(employee);
			List<Application> appList = new ArrayList<Application>();
			Connection conn = cf.getConnection();
			String account_type = employee.getType().toUpperCase();
			String sql = "SELECT * FROM APPLICATION WHERE SUBSTATUS='"+account_type+"'AND STATUS = 'PROVED' ";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				appList.add(new Application(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), 
						rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getBlob(11), 
						rs.getBlob(12), rs.getString(13), rs.getString(14), rs.getDouble(15), rs.getTimestamp(16), 
						rs.getString(17), rs.getString(18), rs.getString(19), rs.getDouble(20), rs.getString(21)));
			}
			System.out.println(appList);
			return appList;
		}
	
    //get awarded reimbursement on the benco page
	public List<Account> getAwarded() throws SQLException{
			List<Account> accList = new ArrayList<Account>();
			Connection conn = cf.getConnection();
			String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_TYPE='employee'";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accList.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getDouble(6)));
			}
			System.out.println(accList);
			return accList;
		}
		
	//insert application form into mySQL
	public void insertApp(Application app, Account employee) throws SQLException{
		Connection conn = cf.getConnection();
		//CALCULATE APPLYAMOUNT
		Double cost = app.getEventCost();
		String type = app.getTypeofEvent();
		Double amount = (double) 0;
		if (cost > 1000) {
		    amount = (double) 1000;
		}
		else if (type.equals("University Courses")) {
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
		
		String sql = "INSERT INTO APPLICATION VALUES(MYSEQ1.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,'PENDING','SUPERVISOR',?,?,'UNKNOWN','UNKNOWN','UNKNOWN',0,'UNKNOWN')";
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
		System.out.println("In insertApp");
	}
	
	//supervisor/department/benco denied application
	public void updateApp2(Application app) throws SQLException{
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int appid = app.getAppId();
		String denyInfo = app.getDenyInfo();
		String sql = "UPDATE APPLICATION SET SUBSTATUS = 'EMPLOYEE', DENY_INFO = '"+denyInfo+"' WHERE APPLICATION_ID = "+appid+"";
		stmt.executeQuery(sql); 
	}
	
	//supervisor approved application
	public void updateApp(Application app) throws SQLException{
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int appid = app.getAppId();
		String sql = "UPDATE APPLICATION SET SUBSTATUS = 'DEPARTMENT' WHERE APPLICATION_ID = "+appid+"";
		stmt.executeQuery(sql); 
	}
	
	//department head approved application
	public void updateApp3(Application app) throws SQLException{
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int appid = app.getAppId();
		String sql = "UPDATE APPLICATION SET SUBSTATUS = 'BENCO' WHERE APPLICATION_ID = "+appid+"";
		stmt.executeQuery(sql); 
	}
	
	//benco prove application
	public void updateApp4(Application app) throws SQLException{
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int appid = app.getAppId();
		Double provedAmount = app.getProvedAmount();
		String denyInfo = app.getDenyInfo();
		String sql = "UPDATE APPLICATION SET STATUS = 'PROVED', SUBSTATUS = 'EMPLOYEE', PROVED_AMOUNT = "+provedAmount+", DENY_INFO = '"+denyInfo+"' WHERE APPLICATION_ID = "+appid+"";
		stmt.executeQuery(sql); 
	}
	
	//benco/supervisor final prove
	public void updateApp9(Application app) throws SQLException{
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int appid = app.getAppId();
		System.out.println(appid);
		ResultSet rs = stmt.executeQuery("SELECT * FROM APPLICATION WHERE APPLICATION_ID = "+appid+"");
		if(rs.next() == true) {
			int accid = rs.getInt(2);
			Double award = rs.getDouble(20);
			String sql = "UPDATE APPLICATION SET STATUS = 'AVAILABLE', SUBSTATUS = 'EMPLOYEE' WHERE APPLICATION_ID = "+appid+"";
			stmt.executeQuery(sql); 
			String sql2 = "UPDATE ACCOUNT SET AWARDED_REIMBURSMENT = AWARDED_REIMBURSMENT+"+award+" WHERE ACCOUNT_ID = "+accid+"";
			System.out.println(sql2);
			stmt.executeQuery(sql2); 
		}else {
			System.out.println("The Application ID you input is not available");
		}
	}
	
	//benco/supervisor final deny
	public void updateApp10(Application app) throws SQLException{
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int appid = app.getAppId();
		String denyInfo = app.getDenyInfo();
		String sql = "UPDATE APPLICATION SET STATUS = 'DENY', SUBSTATUS = 'DENY', DENY_INFO = '"+denyInfo+"' WHERE APPLICATION_ID = "+appid+"";
		System.out.println(sql);
		stmt.executeQuery(sql); 
	}
	
	//employee canceled application
	public void updateApp5(Application app) throws SQLException{
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int appid = app.getAppId();
		String sql = "UPDATE APPLICATION SET STATUS = 'CANCEL', SUBSTATUS = 'CANCEL' WHERE APPLICATION_ID = "+appid+"";
		stmt.executeQuery(sql); 
	}
	
	//employee confirm application
	public void updateApp6(Application app) throws SQLException{
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int appid = app.getAppId();
		String addtionalInfo = app.getAdditionalInfo();
		String sql = "UPDATE APPLICATION SET SUBSTATUS = 'SUPERVISOR', ADDITIONAL_INFO = '"+addtionalInfo+"' WHERE APPLICATION_ID = "+appid+"";
		stmt.executeQuery(sql);
	}
	
	//employee submit grade
	public void updateApp7(Application app) throws SQLException{
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int appid = app.getAppId();
		String grade = app.getGrade();
		String sql = "UPDATE APPLICATION SET SUBSTATUS = 'BENCO', GRADE = '"+grade+"' WHERE APPLICATION_ID = "+appid+"";
		stmt.executeQuery(sql);
	}
	
	//employee submit presentation
		public void updateApp8(Application app) throws SQLException{
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			int appid = app.getAppId();
			String presentation = app.getPresentation();
			String sql = "UPDATE APPLICATION SET SUBSTATUS = 'SUPERVISOR', PRESENTATION = '"+presentation+"' WHERE APPLICATION_ID = "+appid+"";
			stmt.executeQuery(sql);
		}


}
