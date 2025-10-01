package com.hr.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.hr.bean.User; 
import com.hr.util.DBUtil;

public class UserDao {
    
    public ArrayList<User> getAllUsers() throws Exception {
        ArrayList<User> list = new ArrayList<User>();
        String sql = "SELECT * FROM users";
        ResultSet rs = DBUtil.executeQuery(sql);
        
        while (rs.next()) {
            User user = new User();
            user.setUser_id(rs.getInt("user_id"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
           // user.setDob(rs.getDate("dob"));
            user.setRole(rs.getString("role"));
            list.add(user);
        }
       
        try {
            DBUtil.conn.close();
            System.out.println("Connection closed successfully");
        } catch (Exception e) {
            // Handle exception
        }
        
        return list;
    }
    
/* public static void insertUser(int user_id,String first_name, String last_name, String email, String password, Date DOB, String role) throws Exception {


      String sql = "INSERT INTO users (user_id, first_name, last_name, email, password, DOB, role) "
                 + "VALUES (SEQ_USER_ID.NEXTVAL, '" + first_name +"','" + last_name + "','"+ email +"','"+ password +"','"+ DOB +"','"+ role +"')";
DBUtil.executeQuery(sql);
System.out.println("tested");
DBUtil.conn.close();

    }
 // Add more methods as needed, such as insert, update, delete operations*/

public static boolean insertUser(String first_name, String last_name, String email, String password, String role) throws Exception {
	// TODO Auto-generated method stub  
	String sql = "INSERT INTO users (user_id, first_name, last_name, email, password, role) "
            + "VALUES (SEQ_USER_ID.NEXTVAL, '" + first_name +"','" + last_name + "','"+ email +"','"+ password +"','"+ role +"')";
DBUtil.executeQuery(sql);
System.out.println("tested");
DBUtil.conn.close();
	return false;
}


	
}


