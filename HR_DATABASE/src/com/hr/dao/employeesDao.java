 package com.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.hr.bean.Employees;
import com.hr.util.DBUtil;

public class employeesDao {
	
	public ArrayList<Employees> getallemployees() throws Exception{
		ArrayList<Employees>	list = new ArrayList<Employees>();
		String sql = "select * from employees";
		ResultSet rs = DBUtil.executeQuery(sql);
		while(rs.next()) {
			Employees emp = new Employees();
			emp.setEMPLOYEE_ID(rs.getInt(1));
			emp.setFIRST_NAME(rs.getString(2));		
			list.add(emp);
			
		}
		
		try {
			DBUtil.conn.close();
			System.out.println("close successfully");
		} catch (Exception e) {
		}
		return list;
		
		
	}

}
