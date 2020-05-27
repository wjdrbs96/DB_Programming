package DBP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DTO.Member;

@WebServlet("/member")
public class MemberSevlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		System.out.println(loginId);
		System.out.println(password);
		System.out.println(name);
		System.out.println(email);
		
		
		Member member = new Member(loginId, password, name, email);
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";

		Connection con = null;
		
		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			Statement st = con.createStatement();
			//insert(con, member);

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
		
		String sql = "insert into member(loginId, password, name, email) " +
					 "values(?, ?, ?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, member.getLoginId());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getEmail());
		
		int count = pstmt.executeUpdate();
		
		if (count == 1) {
			System.out.println("insert 성공입니다");
		}
		
		else {
			System.out.println("insert 실패입니다");
		}
		
		pstmt.close();
	}
}
