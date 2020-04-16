package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first2")
public class ServletTest extends HttpServlet {
	
	/*@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init called");
	}*/
	
	// 
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("sevice called");
		super.service(arg0, arg1);
	}
	
	
	// doGet메소드는 호출되지 않는다 왜일까? 
	// service에서 super.service(req,res)가 없기 때문에
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet called");
		
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>Test</title></head>");
		out.print("<body><h1>doGet Have a nice</h1></body>");
		out.print("</html");
		out.close();
	}
		
}
