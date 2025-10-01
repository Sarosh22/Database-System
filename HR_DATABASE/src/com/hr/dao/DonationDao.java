package com.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.hr.bean.Donation; 
import com.hr.util.DBUtil;

public class DonationDao {
    
    public ArrayList<Donation> getAllDonations() throws Exception {
        ArrayList<Donation> list = new ArrayList<Donation>();
        String sql = "SELECT * FROM donations";
        ResultSet rs = DBUtil.executeQuery(sql);
        
        while (rs.next()) {
            Donation donation = new Donation();
            donation.setDonation_id(rs.getInt("donation_id"));
            donation.setUser_id(rs.getInt("user_id"));
            donation.setDonation_date(rs.getDate("donation_date"));
            donation.setBlood_type(rs.getString("blood_type"));
            donation.setAmount(rs.getInt("amount"));
            
            list.add(donation);
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
