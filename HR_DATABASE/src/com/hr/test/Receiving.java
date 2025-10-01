package com.hr.test;

import com.hr.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Receiving {
    private int receiving_id;
    private int user_id;
    private Date receiving_date;
    private String blood_type;
    private int amount;
    private int hospital_id;
    private String status;
    private String received_by;
    private String storage_location;

    // Method to fetch all receiving records
    public ArrayList<Receiving> getAllReceivings() throws Exception {
        ArrayList<Receiving> list = new ArrayList<>();
        String sql = "SELECT * FROM receiving";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Receiving receiving = new Receiving();
                receiving.setReceiving_id(rs.getInt("receiving_id"));
                receiving.setUser_id(rs.getInt("user_id"));
                receiving.setReceiving_date(rs.getDate("receiving_date"));
                receiving.setBlood_type(rs.getString("blood_type"));
                receiving.setAmount(rs.getInt("amount"));
                receiving.setHospital_id(rs.getInt("hospital_id"));
                receiving.setStatus(rs.getString("status"));
                receiving.setReceived_by(rs.getString("received_by"));
                receiving.setStorage_location(rs.getString("storage_location"));

                list.add(receiving);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error fetching receivings");
        }

        return list;
    }

    // Method to insert a new receiving record
    public void insertReceiving(Receiving receiving) throws Exception {
        String sql = "INSERT INTO receiving (user_id, receiving_date, blood_type, amount, hospital_id, status, received_by, storage_location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, receiving.getUser_id());
            stmt.setDate(2, new java.sql.Date(receiving.getReceiving_date().getTime()));
            stmt.setString(3, receiving.getBlood_type());
            stmt.setInt(4, receiving.getAmount());
            stmt.setInt(5, receiving.getHospital_id());
            stmt.setString(6, receiving.getStatus());
            stmt.setString(7, receiving.getReceived_by());
            stmt.setString(8, receiving.getStorage_location());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error inserting receiving");
        }
    }

    // Method to update an existing receiving record
    public void updateReceiving(Receiving receiving) throws Exception {
        String sql = "UPDATE receiving SET user_id = ?, receiving_date = ?, blood_type = ?, amount = ?, hospital_id = ?, status = ?, received_by = ?, storage_location = ? WHERE receiving_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, receiving.getUser_id());
            stmt.setDate(2, new java.sql.Date(receiving.getReceiving_date().getTime()));
            stmt.setString(3, receiving.getBlood_type());
            stmt.setInt(4, receiving.getAmount());
            stmt.setInt(5, receiving.getHospital_id());
            stmt.setString(6, receiving.getStatus());
            stmt.setString(7, receiving.getReceived_by());
            stmt.setString(8, receiving.getStorage_location());
            stmt.setInt(9, receiving.getReceiving_id());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error updating receiving");
        }
    }

    // Method to delete a receiving record
    public void deleteReceiving(int receiving_id) throws Exception {
        String sql = "DELETE FROM receiving WHERE receiving_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, receiving_id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error deleting receiving");
        }
    }

    // Getters and Setters
    public int getReceiving_id() { return receiving_id; }
    public void setReceiving_id(int receiving_id) { this.receiving_id = receiving_id; }
    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }
    public Date getReceiving_date() { return receiving_date; }
    public void setReceiving_date(Date receiving_date) { this.receiving_date = receiving_date; }
    public String getBlood_type() { return blood_type; }
    public void setBlood_type(String blood_type) { this.blood_type = blood_type; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public int getHospital_id() { return hospital_id; }
    public void setHospital_id(int hospital_id) { this.hospital_id = hospital_id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReceived_by() { return received_by; }
    public void setReceived_by(String received_by) { this.received_by = received_by; }
    public String getStorage_location() { return storage_location; }
    public void setStorage_location(String storage_location) { this.storage_location = storage_location; }
}