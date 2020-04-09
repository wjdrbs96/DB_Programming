package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Example1 {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      
      String jdbc_driver = "com.mysql.cj.jdbc.Driver";
      String jdbc_url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
      try {
         Class.forName(jdbc_driver).newInstance();
         Connection con = DriverManager.getConnection(jdbc_url, "root", "root");
         Statement st = con.createStatement();
         String sql = "SELECT * FROM member";
         ResultSet rs = st.executeQuery(sql);

         rs.next();

         @SuppressWarnings("unused")
         String name = rs.getString("name");
         System.out.printf("name: %s\n", name);

         rs.close();
         st.close();
         con.close();         
      } catch (Exception e) {
         e.printStackTrace();
      } 
   }
}
