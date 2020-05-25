package com.calcul;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cal")
public class Calculator extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		int x = Integer.parseInt(req.getParameter("x"));
		int y = Integer.parseInt(req.getParameter("y"));
		
		String op = req.getParameter("operator");
		
		int result = 0;
		
		if (op.equals("덧셈")) {
			result = x + y;
		}
		else if (op.equals("뺼셈")) {
			result = x - y;
		}
		
		resp.getWriter().printf("result is %d\n", result);
		
	}
}
