package com.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.hr.bean.BloodTest; // Replace with your actual package name
import com.hr.util.DBUtil;

public class BloodTestDao {

    public ArrayList<BloodTest> getAllBloodTests() throws Exception {
        ArrayList<BloodTest> list = new ArrayList<BloodTest>();
        String sql = "SELECT * FROM blood_tests";
        ResultSet rs = DBUtil.executeQuery(sql);

        while (rs.next()) {
            BloodTest test = new BloodTest();
            test.setTest_id(rs.getInt("test_id"));
            test.setDonation_id(rs.getInt("donation_id"));
            test.setTest_date(rs.getDate("test_date"));
            test.setResults(rs.getString("results"));

            list.add(test);
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
