package com.calcul;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CalSession extends HttpServlet{ 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v = req.getParameter("value");
		int value = Integer.parseInt(v);
		String op = req.getParameter("operator");
		
		// ServletContext와 Session의 차이는 ? 
		
		/*
		ServletContext는 웹 어플리케이션 생명주기를 따름 => 크롬에서 하나 익스플로어에서 하나 정보를 가져올 수 있음
		Session은 클라이언트 단위 (하지만 Session은 크롬과 익스플로어는 다름)
		*/
		
		
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
			
			resp.getWriter().printf("result is %d\n", result);
			
		}
		else {
			session.setAttribute("value", v);
			session.setAttribute("operator", op);
		}
	}
	
}
