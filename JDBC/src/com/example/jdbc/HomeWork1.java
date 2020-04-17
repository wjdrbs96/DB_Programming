package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import DAO.AddressBook;

public class HomeWork1 {

	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/address?serverTimezone=UTC";

		Connection con = null;
		
		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "root");
			Statement st = con.createStatement();
			List<AddressBook> listDate = inputDate();   // insert할 데이터 입력받기 
			

			// 5개 insert 하기 
			for (int i = 0; i < 5; ++i) {
				insert(con, listDate.get(i));
			}
			
			List<AddressBook> list1 = listAll(st);           // insert 한 이후에 log 찍기 
			
			for (int i = 0; i < list1.size(); ++i) {
				update(con, list1.get(i));                  // 모든 데이터 다 update 
			}
			
			List<AddressBook> list2 = listAll(st);     // update 한 이후에 데이터 log 찍기 
			
			
			delete(con, list2.size() - 1);                    // 하위 2개 지우기 
			delete(con, list2.size() - 2);
			List<AddressBook> list3 = listAll(st);         // delete 한 이후에 데이터 log 찍기

		}
		
		catch (SQLException e) {
			System.out.println("쿼리 에러입니다");
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
	
	// 전체 데이터를 조회
	public static List<AddressBook> listAll(Statement st) throws SQLException {
		String sql = "select * from addressbook";
		ResultSet rs = st.executeQuery(sql);
		
		List<AddressBook> list = new ArrayList<>();
		
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String tel = rs.getString(3);
			String email = rs.getString(4);
			String address = rs.getString(5);
			AddressBook addressbook = new AddressBook(id, name, tel, email, address);
			System.out.println(name + " " + tel + " " + email + " " + address);          // 데이터 log 찍기 
			list.add(addressbook);
		
		}
		
		rs.close();
		return list;
		
	}
	
	// 데이터 업데이트
	public static void update(Connection con, AddressBook addressbook) throws SQLException {
		String sql = "update addressbook " +
					 "set email = ? " +
					 "where id = ?";
		
	
		String email = addressbook.getEmail();
		String[] emailId = email.split("@");              // @를 기준으로 앞에 Id 값만 얻기 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, emailId[0] + "@naver.com");           // 모든 email 주소를 @naver.com 으로 변경 
		pstmt.setLong(2, addressbook.getId());
		
		int count = pstmt.executeUpdate();                       // 바뀐 row 수 int 형으로 반환 

		if (count == 1) {
			System.out.println("데이터 update 성공");
		}

		else {
			System.out.println("데이터 update 실패");
		}
	
		pstmt.close();
		
	}
	
	public static void delete(Connection con, int id) throws SQLException {
		String sql = "delete from addressbook " +
					 "where id = ?";
		
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		
		int count = pstmt.executeUpdate();              // 바뀐 row 수 int 형으로 반환 

		if (count == 1) {
			System.out.println("데이터 delete 성공");
		}

		else {
			System.out.println("데이터 delete 실패");
		}
		
		pstmt.close();
	}
	
	// 데이터를 insert 하기 
	public static void insert(Connection con, AddressBook addressbook) throws SQLException {
	
		String sql = "insert into addressbook(name, tel, email, address) " +
					 "values(?, ?, ?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, addressbook.getName());
		pstmt.setString(2, addressbook.getTel());
		pstmt.setString(3, addressbook.getEmail());
		pstmt.setString(4, addressbook.getAddress());
		
		int count = pstmt.executeUpdate();                   // 바뀐 row 수 int 형으로 반환 
		
		if (count == 1) {
			System.out.println("데이터 insert 성공");
		}
		
		else {
			System.out.println("데이터 insert 실패");
		}
		
		pstmt.close();
	}
	
	// 데이터를 입력받기
	public static List<AddressBook> inputDate() {
		
		List<AddressBook> list = new ArrayList<>();
		System.out.println("insert할 데이터를 입력하세요");
		Scanner input = new Scanner(System.in);
		
		for (int i = 0; i < 5; ++i) {                    // 5개 데이터 입력받기 
			String name = input.next();
			String tel = input.next();
			String email = input.next();
			String address = input.next();
			AddressBook addressbook = new AddressBook(name, tel, email, address);
			list.add(addressbook);
		}
		
		input.close();
		
		return list;
	}
}

