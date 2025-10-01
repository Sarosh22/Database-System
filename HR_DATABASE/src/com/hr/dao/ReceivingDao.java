package com.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hr.bean.Receiving;
import com.hr.util.DBUtil;

public class ReceivingDao {

    public void insertReceiving(Receiving receiving) throws Exception {
        String sql = "INSERT INTO receivings (user_id, receiving_date, blood_type, amount, hospital_id, status, received_by, storage_location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, receiving.getUser_id());
            stmt.setDate(2, new java.sql.Date(receiving.getReceiving_date().getTime()));
            stmt.setString(3, receiving.getBlood_type());
            stmt.setInt(4, receiving.getAmount());
            stmt.setInt(5, receiving.getHospital_id());
            stmt.setString(6, receiving.getStatus());
            stmt.setString(7, receiving.getReceiving_id1());
            stmt.setString(8, receiving.getStorage_location());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error inserting receiving");
        }
    }

	

    public ArrayList<Receiving> getAllReceivings() {
        ArrayList<Receiving> receivingsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_donation", "username", "password");

            // Create the SQL query
            String sql = "SELECT * FROM Receiving";

            // Create the PreparedStatement
            preparedStatement = connection.prepareStatement(sql);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the ResultSet
            while (resultSet.next()) {
                Receiving receiving = new Receiving();
                receiving.setUser_id(resultSet.getInt("id"));
                receiving.setDonorName(resultSet.getString("donor_name"));
                receiving.setBlood_type(resultSet.getString("blood_type"));
                receiving.setReceiving_date(resultSet.getDate("receiving_date"));
                receiving.setQuantity(resultSet.getInt("quantity"));

                receivingsList.add(receiving);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return receivingsList;
    }



	public void updateReceiving(Receiving receiving) {
		// TODO Auto-generated method stub
		
	}



	public void deleteReceiving(int receivingId) {
		// TODO Auto-generated method stub
		
	}
}

