import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myforward")
public class myForward extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// http://localhost:8081/AssignmentJSP/myforward?num1=1&num2=3 와 같은 형태로 쿼리스트링 형태를 주소창에서 넘겨줘서 실행시킴 
		String num1 = req.getParameter("num1");
		String num2 = req.getParameter("num2");
		int iNum1 = num1 != null ? Integer.parseInt(num1):0;
		int iNum2 = num2 != null ? Integer.parseInt(num2):0;
		int result = iNum1 + iNum2;
		req.setAttribute("num1",num1);
		req.setAttribute("num2", num2);
		req.setAttribute("result", result);
		RequestDispatcher dispatcher = req.getRequestDispatcher("myExample3.jsp");
		dispatcher.forward(req, resp);
		
	}

}
