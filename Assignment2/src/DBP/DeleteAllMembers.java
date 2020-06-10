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

@WebServlet("/deleteAll")
public class DeleteAllMembers extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";
		
		Connection con = null;
		
		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			
			Statement st = con.createStatement();
			
			deleteALl(con);
			
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
	
	public static void deleteALl(Connection con) throws SQLException {
		String sql = "delete from member";
		
		PreparedStatement pr = con.prepareStatement(sql);
		int r = pr.executeUpdate();
		
		if (r > 0) {
			System.out.println("삭제 성공");
		}
		else {
			System.out.println("삭제 실패");
		}
		
	}
}
