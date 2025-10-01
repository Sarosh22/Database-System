package com.hr.test;

import com.hr.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Donation {
    private int donation_id;
    private int donor_id;
    private Date donation_date;
    private String blood_type;
    private int amount;

    public ArrayList<Donation> getAllDonations() throws Exception {
        ArrayList<Donation> list = new ArrayList<>();
        String sql = "SELECT * FROM donations";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Donation donation = new Donation();
                donation.setDonation_id(rs.getInt("donation_id"));
                donation.setDonor_id(rs.getInt("donor_id"));
                donation.setDonation_date(rs.getDate("donation_date"));
                donation.setBlood_type(rs.getString("blood_type"));
                donation.setAmount(rs.getInt("amount"));

                list.add(donation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error fetching donations");
        }

        return list;
    }

    public void insertDonation(Donation donation) throws Exception {
        String sql = "INSERT INTO donations (donor_id, donation_date, blood_type, amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, donation.getDonor_id());
            stmt.setDate(2, new java.sql.Date(donation.getDonation_date().getTime()));
            stmt.setString(3, donation.getBlood_type());
            stmt.setInt(4, donation.getAmount());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error inserting donation");
        }
    }

    public void updateDonation(Donation donation) throws Exception {
        String sql = "UPDATE donations SET donor_id = ?, donation_date = ?, blood_type = ?, amount = ? WHERE donation_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, donation.getDonor_id());
            stmt.setDate(2, new java.sql.Date(donation.getDonation_date().getTime()));
            stmt.setString(3, donation.getBlood_type());
            stmt.setInt(4, donation.getAmount());
            stmt.setInt(5, donation.getDonation_id());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error updating donation");
        }
    }

    public void deleteDonation(int donation_id) throws Exception {
        String sql = "DELETE FROM donations WHERE donation_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, donation_id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error deleting donation");
        }
    }

    // Getters and Setters
    public int getDonation_id() { return donation_id; }
    public void setDonation_id(int donation_id) { this.donation_id = donation_id; }
    public int getDonor_id() { return donor_id; }
    public void setDonor_id(int donor_id) { this.donor_id = donor_id; }
    public Date getDonation_date() { return donation_date; }
    public void setDonation_date(Date donation_date) { this.donation_date = donation_date; }
    public String getBlood_type() { return blood_type; }
    public void setBlood_type(String blood_type) { this.blood_type = blood_type; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
}

