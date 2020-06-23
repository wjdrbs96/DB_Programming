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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		String number = req.getParameter("number");

		// 입력하지 않은 것이 존재하면 redirect
		if (loginId.length() == 0 || loginId == null || password.length() == 0 || password == null) {
			resp.sendRedirect("login.jsp");
			return;
		}

		Member member = new Member(loginId, password);

		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";

		Connection con = null;
		HttpSession session = req.getSession();

		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			Statement st = con.createStatement();
			boolean check = checkLoginId(con, loginId);

			// 아이디가  DB에 저장되어 있지 않을 때
			if (!check) {
				if (number.equals("1")) {
					String errorMsg = "존재하지 않는 아이디 입니다";
					session.setAttribute("errorMsg", errorMsg);
					resp.sendRedirect("login.jsp");
					return;
				}
				
				insert(con, member);
			}

			// 아이디, 이름이 DB에 저장되어 있을 때
			else {
				if (number.equals("2")) {
					String errorMsg = "이미 존재하는 아이디입니다";
					session.setAttribute("errorMsg", errorMsg);
					resp.sendRedirect("login.jsp");
					return;
				}
				
				boolean checkPW = checkPassword(con, loginId, password);

				// 비밀번호가 일치하지 않을 때
				if (!checkPW) {
					String errorMsg = "비밀번호가 틀렸습니다";
					session.setAttribute("errorMsg", errorMsg);
					resp.sendRedirect("login.jsp");
					return;
				}

				// 로그인이 되었을 때
				List<Member> list = memberAllList(st);
				session.setAttribute("list", list);
				resp.sendRedirect("listAll.jsp");
				return;
			}

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

		String sql = "insert into member(loginId, password) " + "values(?, ?)";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, member.getLoginId());
		pstmt.setString(2, member.getPassword());

		int count = pstmt.executeUpdate();

		if (count == 1) {
			System.out.println("==========insert 성공입니다==========");

		}

		else {
			System.out.println("insert 실패입니다");
		}

		pstmt.close();
	}
	
	public static List<Member> memberAllList(Statement st) throws SQLException {
		String sql = "select * from member";
		List<Member> list = new ArrayList<>();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			Member member = new Member(rs.getInt("memberId"), rs.getString("loginId"), rs.getString("password"));
			list.add(member);
		}

		return list;
	}

	public static int checkMemberId(Connection con, Member member) throws SQLException {
		String sql = "select * from member where loginId = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member.getLoginId());

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt("memberId");
		}

		return 0;

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

	public static boolean checkPassword(Connection con, String loginId, String password) throws SQLException {
		String sql = "select * from member where loginId = ? and password = ?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, loginId);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			return true;
		}

		return false;

	}

	public static List<Member> isCheck(Statement st) throws SQLException {
		String sql = "select * from member " + "where loginId = ?";

		ResultSet rs = st.executeQuery(sql);

		List<Member> list = new ArrayList<>();

		while (rs.next()) {
			String loginId = rs.getString(2);
			String name = rs.getString(3);
			String email = rs.getString(4);
			Member member = new Member(loginId);
			list.add(member);

		}

		rs.close();
		return list;

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String id = req.getParameter("id");
		int Id = Integer.parseInt(id);

		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";

		Connection con = null;
		HttpSession session = req.getSession();

		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			Statement st = con.createStatement();
			String loginId = findMember(con, Id);
			
			session.setAttribute("loginId", loginId);
			req.setAttribute("loginId", loginId);
			resp.sendRedirect("memberView.jsp");
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
		
		
	public static String findMember(Connection con, int memberId) throws Exception {
		String sql = "select * from member where memberId = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, memberId);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			return rs.getString(2);
		}
		
		
		return null;
	}

}
