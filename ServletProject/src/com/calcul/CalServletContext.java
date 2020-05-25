package com.calcul;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cal2")
public class CalServletContext extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v = req.getParameter("value");
		int value = Integer.parseInt(v);
		String op = req.getParameter("operator");
		
		ServletContext servletContext = req.getServletContext();

		int result = 0;
		
		if (op.equals("=")) {
			int prev = (int) servletContext.getAttribute("value");
			String prev_op = (String) servletContext.getAttribute("operator");
			
			if (prev_op.equals("+")) {
				result = prev + value;
			}
			else if (prev_op.equals("-")) {
				result = prev - value;
			}
			
			resp.getWriter().printf("result is %d\n", result);
			
		}
		else {
			servletContext.setAttribute("value", v);         
			servletContext.setAttribute("operator", op);                 
		}
	}
	
}
