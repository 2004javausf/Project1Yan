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
import com.revature.dao.ApplicationDAOImpl;

public class BencoServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in BencoServlet3 doGet");
		ObjectMapper mapper = new ObjectMapper();
		ApplicationDAOImpl apdi = new ApplicationDAOImpl();
		PrintWriter pw = response.getWriter();
		String apJSON;
		try {
			apJSON = mapper.writeValueAsString(apdi.getAwarded());
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

}
