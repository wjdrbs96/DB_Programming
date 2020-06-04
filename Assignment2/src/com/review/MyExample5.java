package com.review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/my5")
public class MyExample5 extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v_ = req.getParameter("value");
		int v = Integer.parseInt(v_);
		String op = req.getParameter("operator");	
		
		// 쿠키 배열
		Cookie[] cookies = req.getCookies();
		
		int result = 0;
		if(op.equals("=")) {	
			int prev = 0;
			String prev_op = "";
			
			for (Cookie c : cookies) {
				if (c.getName().equals("value")) {
					prev = Integer.parseInt(c.getValue());
					System.out.println(prev);
				}
				
				if (c.getName().equals("operator")) {
					prev_op = c.getValue();
				}
			}
			
			if (prev_op.equals("+")) {
				result = prev + v;
				
			} else if (prev_op.equals("-")) {
				result = prev - v;
			}
			
			resp.getWriter().printf("Result is %d\n", result);
			
		} else {
			// 서버에서 쿠키 생성
			System.out.println(v);
			System.out.println(op);
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie operatorCookie = new Cookie("operator", op);
			valueCookie.setMaxAge(60 * 60 * 2);
			valueCookie.setPath("/");
			
			// 클라이언트에게 쿠키 전송
			resp.addCookie(valueCookie);
			resp.addCookie(operatorCookie);
			
			resp.sendRedirect("cal2.html");
		
		}
	}
}
