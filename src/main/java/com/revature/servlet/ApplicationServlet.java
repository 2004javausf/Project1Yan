package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Account;
import com.revature.beans.Application;
import com.revature.dao.ApplicationDAOImpl;

public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Account employee = LoginServlet.employee;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in EmployeeServelt doPost");
		Application app=null;
		ObjectMapper mapper=new ObjectMapper();
		app=mapper.readValue(request.getInputStream(), Application.class);
		System.out.println(app);		
		System.out.println(employee);
		
		ApplicationDAOImpl adi=new ApplicationDAOImpl();
		try {
			adi.insertApp(app, employee);
			PrintWriter pw=response.getWriter();
			pw.write("<h3>Reimbursement Form Submitted, Please wait for approval!</h3>");
			pw.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
