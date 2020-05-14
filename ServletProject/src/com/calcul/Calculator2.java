package com.calcul;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cal2")
public class Calculator2 extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v = req.getParameter("value");
		int value = Integer.parseInt(v);
		String op = req.getParameter("operator");
		
		Cookie[] cookies = req.getCookies();
		
		int result = 0;
		
		if (op.equals("=")) {
			int prev = 0;
			String prev_op = "";
			
			for (Cookie c : cookies) {
				if (c.getName().equals("value")) {
					prev = Integer.parseInt(c.getValue());
				}
				if (c.getName().equals("operator")) {
					prev_op = c.getValue();
				}
			}
			
			if (prev_op.equals("+")) {
				result = prev + value;
			} 
			else if (prev_op.equals("-")) {
				result = prev - value;
			}
			
			resp.getWriter().printf("Result is %d\n", result);
		}
		else {
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie operatorCookie = new Cookie("operator", op);
			valueCookie.setMaxAge(60 * 60 * 2);
			
			
			resp.addCookie(valueCookie);
			resp.addCookie(operatorCookie);
			
			resp.sendRedirect("Calculator2.html");
		}
	}
	
	
}
