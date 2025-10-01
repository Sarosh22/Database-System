package com.hr.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hr.bean.Hospital; 
import com.hr.util.DBUtil;

public class HospitalDao {
    
    public ArrayList<Hospital> getAllHospitals() throws Exception {
        ArrayList<Hospital> list = new ArrayList<Hospital>();
        String sql = "SELECT * FROM hospitals";
        ResultSet rs = DBUtil.executeQuery(sql);
        
        while (rs.next()) {
            Hospital hospital = new Hospital();
            hospital.setHospital_id(rs.getInt("hospital_id"));
            hospital.setName(rs.getString("name"));
            hospital.setAddress(rs.getString("address"));
            hospital.setContact_number(rs.getString("contact_number"));
            
            list.add(hospital);
        }
        
        try {
            DBUtil.conn.close();
            System.out.println("Connection closed successfully");
        } catch (Exception e) {
            // Handle exception
        }
        
        return list;
    }
    
    // Add more methods as needed
}

