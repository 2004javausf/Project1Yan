package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Account;
import com.revature.dao.LoginDAOImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDAOImpl loginDao;
	static Account employee = new Account();
	
	public void init() {
		loginDao = new LoginDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("in doPost");
		PrintWriter out= response.getWriter();
		
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        String type = null;
		try {
			employee = loginDao.getAccount(account);
			type = employee.getType();
			System.out.println(type);
			
			if (type.equals("employee")) {
			    HttpSession session = request.getSession();
			    session.setAttribute("username",username);
			    response.sendRedirect("employee.html");
			}
			else if (type.equals("supervisor")){
			    HttpSession session = request.getSession();
			    session.setAttribute("username",username);
			    response.sendRedirect("supervisor.html");
			}
			else if (type.equals("department")){
			    HttpSession session = request.getSession();
			    session.setAttribute("username",username);
			    response.sendRedirect("department.html");
			}
			else if (type.equals("benco")){
			    HttpSession session = request.getSession();
			    session.setAttribute("username",username);
			    response.sendRedirect("benco.html");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("input error");
		    out.print("Sorry, username or password error!");
			request.getRequestDispatcher("index.html").include(request, response);
		}
		out.close();
	}
	

}
