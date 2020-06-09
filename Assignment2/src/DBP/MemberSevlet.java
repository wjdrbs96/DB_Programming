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
		
		// 입력하지 않은 것이 존재하면  redirect
		if (loginId.length() == 0 || password.length() == 0 || name.length() == 0 || email.length() == 0) {
			resp.sendRedirect("register.jsp");
			return;
		}
		
		Member member = new Member(loginId, password, name, email);
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";

		Connection con = null;
		HttpSession session = req.getSession();
		
		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			Statement st = con.createStatement();
			boolean check = checkLoginId(con, loginId, name);
			
			// 아이디, 이름이 DB에 저장되어 있지 않을 때
			if (!check) {
				insert(con, member);
			}
			
			// 아이디, 이름이 DB에 저장되어 있을 때
			else {
				boolean checkPW = checkPassword(con, loginId, password);
				
				// 비밀번호가 일치하지 않을 때
				if (!checkPW) {
					String errorMsg = "비밀번호가 틀렸습니다";
					session.setAttribute("errorMsg", errorMsg);
					resp.sendRedirect("register.jsp");
					return;
					
				}
				
				int memberId = checkMemberId(con, member);
				updateMember(con, member, memberId);
				
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
	
	public static void updateMember(Connection con, Member member, int memberId) throws SQLException {
		String sql = "update member set loginId = ?, password = ?, name = ?, email = ? where memberId = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,member.getLoginId());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getEmail());
		pstmt.setInt(5,  memberId);
		
		int count = pstmt.executeUpdate();
		
		if (count == 1) {
			System.out.println("업데이트 성공입니다");
		}
		else {
			System.out.println("업데이트 실패입니다");
		}
		
		pstmt.close();
	}
	
	public static boolean checkLoginId(Connection con, String loginId, String name) throws SQLException {
		// 아이디, 이름이 모두 같을 때 중복체크임,,, 
		String sql = "select * from member where loginId = ? and name = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, loginId);
		pstmt.setString(2, name);
		
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
			System.out.println("==========insert 성공입니다==========");
			
			for (int i = 0; i < 1; ++i) {
				System.out.println("로그인 아이디 : " + member.getLoginId());
				System.out.println("이름 : " + member.getName());
				System.out.println("이메일 : " + member.getEmail());
			}
			
		}
		
		else {
			System.out.println("insert 실패입니다");
		}
		
		pstmt.close();
	}
	
	public static List<Member> isCheck(Statement st) throws SQLException {
		String sql = "select * from member " +
					 "where loginId = ? and name = ?";
		
		ResultSet rs = st.executeQuery(sql);
		
		List<Member> list = new ArrayList<>();
		
		while (rs.next()) {
			String loginId = rs.getString(2);
			String name = rs.getString(3);
			String email = rs.getString(4);
			Member member = new Member(loginId, name, email);         
			list.add(member);
		
		}
		
		rs.close();
		return list;
		
	}
}
