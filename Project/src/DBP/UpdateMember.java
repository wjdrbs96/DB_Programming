package DBP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.Member;

@WebServlet("/update2")
public class UpdateMember extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String department = req.getParameter("department");
		String memberId = req.getParameter("memberId");
		int id = Integer.parseInt(memberId);
		
		Member member = new Member(id, loginId, password, name, department);
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";

		Connection con = null;
		HttpSession session = req.getSession();

		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			Statement st = con.createStatement();
			updateMember(con, member);
			resp.sendRedirect("listAll.jsp");
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
	
	public static void updateMember(Connection con, Member member) throws SQLException {
		String sql = "update member " +
					 "set loginId = ?, password = ?, name = ?, department = ? " +
					 "where memberId = ?";
		       
		
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, member.getLoginId());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getDepartment());
		pstmt.setInt(5, member.getMemberId());
		
		int count = pstmt.executeUpdate();                       

		if (count == 1) {
			System.out.println("업데이트 성공");
		}

		else {
			System.out.println("업데이트 실패");
		}
	
		pstmt.close();
		
	}
}
