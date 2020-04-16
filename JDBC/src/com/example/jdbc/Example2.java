package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Example2 {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      
      String jdbc_driver = "com.mysql.cj.jdbc.Driver";
      String jdbc_url = "jdbc:mysql://localhost:3306/datebase?serverTimezone=UTC";
      try {
         Class.forName(jdbc_driver).newInstance();
         Connection con = DriverManager.getConnection(jdbc_url, "root", "root");
         //Statement st = con.createStatement();
         
         String sql = "insert into member(name, major, year, email, phone) " +
         		       "values(?, ?, ?, ?, ?)";
         PreparedStatement st = con.prepareStatement(sql);
       
         
         st.setString(1, "Hong");
         st.setString(2, "Computer");
         st.setString(3, "1999-01-01");
         st.setString(4, "asfasaa@gmail.com");
         st.setString(5, "1111-9999-9888");
         
         st.executeUpdate();

     
         st.close();
         con.close();         
      } catch (Exception e) {
         e.printStackTrace();
      } 
   }
}