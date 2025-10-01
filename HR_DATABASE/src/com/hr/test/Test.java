package com.hr.test;

import java.util.ArrayList;

import com.hr.bean.Employees;
import com.hr.dao.employeesDao;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		employeesDao emp = new employeesDao();
		
		try {
			ArrayList<Employees> list = emp.getallemployees();
			
			for (int i = 0; i < list.size(); i++) {
				
				System.out.println("emp ID:"+list.get(i).getEMPLOYEE_ID());
				
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
