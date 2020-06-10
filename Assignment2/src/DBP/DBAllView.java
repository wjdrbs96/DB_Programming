package DBP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.Member;

@WebServlet("/DBView")
public class DBAllView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";
		
		Connection con = null;
		HttpSession httpSession = req.getSession();
		
		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			
			Statement st = con.createStatement();
			
			List<Member> list = allViewMembers(st);
			httpSession.setAttribute("list", list);
			resp.sendRedirect("allView.jsp");
			return;
			
		}
		catch (SQLException e) {
			System.out.println("쿼리 에러");
		}
		
		catch (Exception e) {
			System.out.println("에러입니다");
		}
		
		finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static List<Member> allViewMembers(Statement st) throws SQLException {
		String sql = "select * from member";
		
		ResultSet rs = st.executeQuery(sql);
		List<Member> list = new ArrayList<>();
		
		while (rs.next()) {
			Member member = new Member(rs.getString("loginId"), rs.getString("name"), rs.getString("email"));
			list.add(member);
		}
		
		return list;
	}
}
