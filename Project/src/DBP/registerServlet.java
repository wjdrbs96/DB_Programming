package DBP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.Member;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String department = req.getParameter("department");
		
		if (loginId.length() == 0 || loginId == null || password.length() == 0 || password == null || name.length() == 0 || name == null || department.length() == 0 || department == null) {
			String error = "정보를 다시 입력하세요";
			session.setAttribute("error", error);
			resp.sendRedirect("register.jsp");
			return;
		}

		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";

		Connection con = null;

		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			Statement st = con.createStatement();
			boolean check = checkLoginId(con, loginId);
			
			// 아이디 중복 체크
			if (check) {
				String error = "아이디가 존재합니다";
				session.setAttribute("error", error);
				resp.sendRedirect("register.jsp");
				return;
			}
			
			Member member = new Member(loginId, password, name, department);
			
			insert(con, member);
			
			resp.sendRedirect("login.jsp");
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
	
	public static void insert(Connection con, Member member) throws SQLException {

		String sql = "insert into member(loginId, password, name, department) " + "values(?, ?, ?, ?)";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, member.getLoginId());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getDepartment());

		int count = pstmt.executeUpdate();

		if (count == 1) {
			System.out.println("==========insert 성공입니다==========");

		}

		else {
			System.out.println("insert 실패입니다");
		}

		pstmt.close();
	}


	
	public static boolean checkLoginId(Connection con, String loginId) throws SQLException {
		String sql = "select * from member where loginId = ?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, loginId);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			return true;
		}

		return false;
	}
}
