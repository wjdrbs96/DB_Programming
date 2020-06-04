package com.review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/my4")
public class MyExample4 extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v = req.getParameter("value");
		int value = Integer.parseInt(v);
		String op = req.getParameter("operator");
		PrintWriter out = resp.getWriter();
		
		HttpSession session = req.getSession();
		int result = 0;
		
		if (op.equals("=")) {
			int prev = (int) session.getAttribute("value");
			String prev_op = (String) session.getAttribute("operator");
			
			if (prev_op.equals("+")) {
				result = prev + value;
			}
			else if (prev_op.equals("-")) {
				result = prev - value;
			}
			
			out.println(result);
			
		}
		
		else {
			session.setAttribute("value", value);
			session.setAttribute("operator", op);
		}
		
	}

}
