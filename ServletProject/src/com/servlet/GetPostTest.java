package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/example")
public class GetPostTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 실행됨");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
	
		int cnt = Integer.parseInt(req.getParameter("cnt"));
		System.out.printf("cnt = %d\n", cnt); 
		
		String[] values = req.getParameterValues("name");

		
		for (int i = 0; i < values.length; ++i) {
			System.out.print(values[i] + " ");
		}
		
		System.out.println();
		
		String query = req.getQueryString();
		System.out.println(query);
		
		PrintWriter out = resp.getWriter();
		for(int i = 0; i < cnt; i++) {
			out.print("<html><head><title>Test</title></head>");
		    out.print("<body><h1>doGet 안녕하세요!!</h1></body>");
		    out.print("</html>");
		}
		
	    out.close();	
		
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	
}
