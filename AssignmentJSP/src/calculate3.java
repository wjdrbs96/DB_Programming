import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cal3")
public class calculate3 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String num1 = req.getParameter("num1");	
		int n1 = Integer.parseInt(num1);
		
		String op = req.getParameter("operator");
		
		HttpSession session = req.getSession();
		
		
		if (op.equals("=")) {
			int result = 0;
			int x = (int)session.getAttribute("num1");
			int y = n1;
			
			String operator = (String)session.getAttribute("op");
			
			if (operator.equals("+")) {
				result = x + y;
			}
			else {
				result = x - y;
			}
			
			session.setAttribute("result", result);
			resp.sendRedirect("myJspExample.jsp");
			
			
		}
		else {
			session.setAttribute("num1", num1);
			session.setAttribute("op", op);
		}
		
		
	}
}
