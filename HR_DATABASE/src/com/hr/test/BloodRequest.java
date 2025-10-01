package com.hr.test;

import com.hr.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class BloodRequest {
    private int request_id;
    private int hospital_id;
    private Date request_date;
    private String blood_type;
    private int amount;
    private String status;

    public ArrayList<BloodRequest> getAllBloodRequests() throws Exception {
        ArrayList<BloodRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM blood_requests";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BloodRequest request = new BloodRequest();
                request.setRequest_id(rs.getInt("request_id"));
                request.setHospital_id(rs.getInt("hospital_id"));
                request.setRequest_date(rs.getDate("request_date"));
                request.setBlood_type(rs.getString("blood_type"));
                request.setAmount(rs.getInt("amount"));
                request.setStatus(rs.getString("status"));

                list.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error fetching blood requests");
        }

        return list;
    }

    public void insertBloodRequest(BloodRequest request) throws Exception {
        String sql = "INSERT INTO blood_requests (hospital_id, request_date, blood_type, amount, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, request.getHospital_id());
            stmt.setDate(2, new java.sql.Date(request.getRequest_date().getTime()));
            stmt.setString(3, request.getBlood_type());
            stmt.setInt(4, request.getAmount());
            stmt.setString(5, request.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error inserting blood request");
        }
    }

    public void updateBloodRequest(BloodRequest request) throws Exception {
        String sql = "UPDATE blood_requests SET hospital_id = ?, request_date = ?, blood_type = ?, amount = ?, status = ? WHERE request_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, request.getHospital_id());
            stmt.setDate(2, new java.sql.Date(request.getRequest_date().getTime()));
            stmt.setString(3, request.getBlood_type());
            stmt.setInt(4, request.getAmount());
            stmt.setString(5, request.getStatus());
            stmt.setInt(6, request.getRequest_id());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error updating blood request");
        }
    }

    public void deleteBloodRequest(int request_id) throws Exception {
        String sql = "DELETE FROM blood_requests WHERE request_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, request_id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error deleting blood request");
        }
    }

    // Getters and Setters
    public int getRequest_id() { return request_id; }
    public void setRequest_id(int request_id) { this.request_id = request_id; }
    public int getHospital_id() { return hospital_id; }
    public void setHospital_id(int hospital_id) { this.hospital_id = hospital_id; }
    public Date getRequest_date() { return request_date; }
    public void setRequest_date(Date request_date) { this.request_date = request_date; }
    public String getBlood_type() { return blood_type; }
    public void setBlood_type(String blood_type) { this.blood_type = blood_type; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
