package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Account;
import com.revature.beans.Application;
import com.revature.dao.ApplicationDAOImpl;


public class SupervisorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Account employee = LoginServlet.employee;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in Supervisor doGet");
		System.out.println(employee.getUsername());
		ObjectMapper mapper = new ObjectMapper();
		ApplicationDAOImpl apdi = new ApplicationDAOImpl();
		PrintWriter pw = response.getWriter();
		String apJSON;
		try {
			apJSON = mapper.writeValueAsString(apdi.getSuperInfo(employee));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			pw.print(apJSON);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.flush();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in SupervisorServelt doPost");
		Application app=null;
		ObjectMapper mapper=new ObjectMapper();
		app=mapper.readValue(request.getInputStream(), Application.class);
		System.out.println(app);		
		String a = app.getSubStatus();
		ApplicationDAOImpl adi=new ApplicationDAOImpl();	
		if (a.equals("Approve")) {
			try {
				adi.updateApp(app);
				PrintWriter pw=response.getWriter();
				pw.write("<h3>Supervisor approved Reimbursement Form</h3>");
				pw.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				adi.updateApp2(app);
				PrintWriter pw=response.getWriter();
				pw.write("<h3>Supervisor Denied Reimbursement Form</h3>");
				pw.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
