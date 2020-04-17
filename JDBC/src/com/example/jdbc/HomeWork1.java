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
			List<AddressBook> listDate = inputDate();   // insert�� ������ �Է¹ޱ� 
			

			// 5�� insert �ϱ� 
			for (int i = 0; i < 5; ++i) {
				insert(con, listDate.get(i));
			}
			
			List<AddressBook> list1 = listAll(st);           // insert �� ���Ŀ� log ��� 
			
			for (int i = 0; i < list1.size(); ++i) {
				update(con, list1.get(i));                  // ��� ������ �� update 
			}
			
			List<AddressBook> list2 = listAll(st);     // update �� ���Ŀ� ������ log ��� 
			
			
			delete(con, list2.size() - 1);                    // ���� 2�� ����� 
			delete(con, list2.size() - 2);
			List<AddressBook> list3 = listAll(st);         // delete �� ���Ŀ� ������ log ���

		}
		
		catch (SQLException e) {
			System.out.println("���� �����Դϴ�");
		}
		catch (Exception e) {
			System.out.println("�����Դϴ�");
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
	
	// ��ü �����͸� ��ȸ
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
			System.out.println(name + " " + tel + " " + email + " " + address);          // ������ log ��� 
			list.add(addressbook);
		
		}
		
		rs.close();
		return list;
		
	}
	
	// ������ ������Ʈ
	public static void update(Connection con, AddressBook addressbook) throws SQLException {
		String sql = "update addressbook " +
					 "set email = ? " +
					 "where id = ?";
		
	
		String email = addressbook.getEmail();
		String[] emailId = email.split("@");              // @�� �������� �տ� Id ���� ��� 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, emailId[0] + "@naver.com");           // ��� email �ּҸ� @naver.com ���� ���� 
		pstmt.setLong(2, addressbook.getId());
		
		int count = pstmt.executeUpdate();                       // �ٲ� row �� int ������ ��ȯ 

		if (count == 1) {
			System.out.println("������ update ����");
		}

		else {
			System.out.println("������ update ����");
		}
	
		pstmt.close();
		
	}
	
	public static void delete(Connection con, int id) throws SQLException {
		String sql = "delete from addressbook " +
					 "where id = ?";
		
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		
		int count = pstmt.executeUpdate();              // �ٲ� row �� int ������ ��ȯ 

		if (count == 1) {
			System.out.println("������ delete ����");
		}

		else {
			System.out.println("������ delete ����");
		}
		
		pstmt.close();
	}
	
	// �����͸� insert �ϱ� 
	public static void insert(Connection con, AddressBook addressbook) throws SQLException {
	
		String sql = "insert into addressbook(name, tel, email, address) " +
					 "values(?, ?, ?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, addressbook.getName());
		pstmt.setString(2, addressbook.getTel());
		pstmt.setString(3, addressbook.getEmail());
		pstmt.setString(4, addressbook.getAddress());
		
		int count = pstmt.executeUpdate();                   // �ٲ� row �� int ������ ��ȯ 
		
		if (count == 1) {
			System.out.println("������ insert ����");
		}
		
		else {
			System.out.println("������ insert ����");
		}
		
		pstmt.close();
	}
	
	// �����͸� �Է¹ޱ�
	public static List<AddressBook> inputDate() {
		
		List<AddressBook> list = new ArrayList<>();
		System.out.println("insert�� �����͸� �Է��ϼ���");
		Scanner input = new Scanner(System.in);
		
		for (int i = 0; i < 5; ++i) {                    // 5�� ������ �Է¹ޱ� 
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

