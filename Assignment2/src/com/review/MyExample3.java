package com.review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my3")
public class MyExample3 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("doGET메소드입니다");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v = req.getParameter("value");
		int value = Integer.parseInt(v);
		String op = req.getParameter("operator");
		System.out.println(value);
		System.out.println(op);
		
		ServletContext servletContext = req.getServletContext();
		PrintWriter out = resp.getWriter();

		int result = 0;
		
		if (op.equals("=")) {
			int prev = (int) servletContext.getAttribute("value");
			String prev_op = (String) servletContext.getAttribute("operator");
			
			if (prev_op.equals("+")) {
				result = prev + value;
				out.println(result);
				
			}
			else if (prev_op.equals("-")) {
				result = prev - value;
				out.println(result);
			}			
		}
		else {
			servletContext.setAttribute("value", value);         
			servletContext.setAttribute("operator", op);                 
		}
	}
}
