package DBP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");


		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";
		

		Connection con = null;
		HttpSession session = req.getSession();
		String loginId = req.getParameter("id");
		

		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			Statement st = con.createStatement();
			if (loginId != null) {
				Member member = findMember(con, loginId);
				session.setAttribute("member", member);
				resp.sendRedirect("updateMember.jsp");
				return;
			}
			
			List<Member> list = memberAllList(st);
			session.setAttribute("list", list);
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
	
	public static List<Member> memberAllList(Statement st) throws SQLException {
		String sql = "select * from member";
		List<Member> list = new ArrayList<>();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			Member member = new Member(rs.getInt("memberId"), rs.getString("loginId"), rs.getString("password"), rs.getString("name"), rs.getString("department"));
			list.add(member);
		}

		return list;
	}
	
	public static Member findMember(Connection con, String loginId) throws Exception {
		String sql = "select * from member where loginId = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, loginId);

		ResultSet rs = pstmt.executeQuery();
		rs.next();
		Member member = new Member(rs.getInt("memberId"), rs.getString("loginId"), rs.getString("password"), rs.getString("name"), rs.getString("department"));
		
		return member;
	}
}
