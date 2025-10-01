package com.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.hr.util.DBUtil;

public class BloodRequestDao {

    public ArrayList<com.hr.test.BloodRequest> getAllBloodRequests() throws Exception {
        ArrayList<com.hr.test.BloodRequest> list = new ArrayList<com.hr.test.BloodRequest>();
        String sql = "SELECT * FROM blood_requests";
        ResultSet rs = DBUtil.executeQuery(sql);

        while (rs.next()) {
            com.hr.test.BloodRequest request = new com.hr.test.BloodRequest();
            request.setRequest_id(rs.getInt("request_id"));
            request.setHospital_id(rs.getInt("hospital_id"));
            request.setRequest_date(rs.getDate("request_date"));
            request.setBlood_type(rs.getString("blood_type"));
            request.setAmount(rs.getInt("amount"));
            request.setStatus(rs.getString("status"));

            list.add(request);
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

