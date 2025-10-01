package com.hr.test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.hr.bean.Receiving;
import com.hr.dao.ReceivingDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ReceivingFrame extends JFrame {
    private JTextField receivingIdField;
    private JTextField userIdField;
    private JTextField receivingDateField;
    private JTextField bloodTypeField;
 
    private JTextField hospitalIdField;
    private JTextField statusField;
    private JTextField receivedByField;
    private JTextField storageLocationField;
    private JTable table;
    private DefaultTableModel tableModel;
    private ReceivingDao receivingDao;

    public ReceivingFrame() {
        receivingDao = new ReceivingDao();

        setTitle("Receiving Management");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        receivingIdField = new JTextField(20);
        userIdField = new JTextField(20);
        receivingDateField = new JTextField(20);
        bloodTypeField = new JTextField(20);
      
        hospitalIdField = new JTextField(20);
        statusField = new JTextField(20);
        receivedByField = new JTextField(20);
        storageLocationField = new JTextField(20);

        JButton fetchButton = new JButton("Fetch All");
        JButton insertButton = new JButton("Insert");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        tableModel = new DefaultTableModel(new String[]{"Receiving ID", "User ID", "Receiving Date", "Blood Type", "Amount", "Hospital ID", "Status", "Received By", "Storage Location"}, 0);
        table = new JTable(tableModel);

        // Set up the layout
        JPanel panel = new JPanel(new GridLayout(10, 5));
        panel.add(new JLabel("  Receiving ID:"));
        panel.add(receivingIdField);
        panel.add(new JLabel("  User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("  Receiving Date:"));
        panel.add(receivingDateField);
        panel.add(new JLabel("  Blood Type:"));
        panel.add(bloodTypeField);
        
        panel.add(new JLabel("  Hospital ID:"));
        panel.add(hospitalIdField);
        panel.add(new JLabel("  Status:"));
        panel.add(statusField);
        panel.add(new JLabel("  Received By:"));
        panel.add(receivedByField);
        panel.add(new JLabel("  Storage Location:"));
        panel.add(storageLocationField);
        panel.add(fetchButton);
        panel.add(insertButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Add action listeners
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchAllReceivings();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertReceiving();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReceiving();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteReceiving();
            }
        });
    }

    private void fetchAllReceivings() {
        try {
            ArrayList<Receiving> receivings = receivingDao.getAllReceivings();
            tableModel.setRowCount(0);
            for (Receiving receiving : receivings) {
                tableModel.addRow(new Object[]{
                        receiving.getReceiving_id(),
                        receiving.getUser_id(),
                        receiving.getReceiving_date(),
                        receiving.getBlood_type(),
                      
                        receiving.getHospital_id(),
                        receiving.getStatus(),
                        receiving.getReceiving_date(),
                        receiving.getStorage_location()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching receivings: " + e.getMessage());
        }
    }

    private void insertReceiving() {
        try {
            // Check for empty fields
            String userIdText = userIdField.getText();
            String receivingDateText = receivingDateField.getText();
            String bloodTypeText = bloodTypeField.getText();

            String hospitalIdText = hospitalIdField.getText();
            String statusText = statusField.getText();
            String receivedByText = receivedByField.getText();
            String storageLocationText = storageLocationField.getText();

            if (userIdText.isEmpty() || receivingDateText.isEmpty() || bloodTypeText.isEmpty() || hospitalIdText.isEmpty() || statusText.isEmpty() || receivedByText.isEmpty() || storageLocationText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            // Parse values
            int userId = Integer.parseInt(userIdText);
            java.sql.Date receivingDate = java.sql.Date.valueOf(receivingDateText);
           
            int hospitalId = Integer.parseInt(hospitalIdText);

            // Create and set Receiving object
            Receiving receiving = new Receiving();
            receiving.setUser_id(userId);
            receiving.setReceiving_date(receivingDate);
            receiving.setBlood_type(bloodTypeText);
         
            receiving.setHospital_id(hospitalId);
            receiving.setStatus(statusText);
            receiving.setReceiving_id(receivedByText);
            receiving.setStorage_location(storageLocationText);

            // Insert the receiving
            receivingDao.insertReceiving(receiving);
            fetchAllReceivings();
            JOptionPane.showMessageDialog(this, "Receiving inserted successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for numeric fields.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format for Receiving Date.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting receiving: " + e.getMessage());
        }
    }


    private void updateReceiving() {
        try {
            // Check for empty fields
            String receivingIdText = receivingIdField.getText();
            String userIdText = userIdField.getText();
            String receivingDateText = receivingDateField.getText();
            String bloodTypeText = bloodTypeField.getText();
          
            String hospitalIdText = hospitalIdField.getText();
            String statusText = statusField.getText();
            String receivedByText = receivedByField.getText();
            String storageLocationText = storageLocationField.getText();

            if (receivingIdText.isEmpty() || userIdText.isEmpty() || receivingDateText.isEmpty() || bloodTypeText.isEmpty() || hospitalIdText.isEmpty() || statusText.isEmpty() || receivedByText.isEmpty() || storageLocationText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            // Parse values
            int receivingId = Integer.parseInt(receivingIdText);
            int userId = Integer.parseInt(userIdText);
            java.sql.Date receivingDate = java.sql.Date.valueOf(receivingDateText);
            
            int hospitalId = Integer.parseInt(hospitalIdText);

            // Create and set Receiving object
            Receiving receiving = new Receiving();
        //    receiving.setReceiving_id1(receivingId);
            receiving.setUser_id(userId);
            receiving.setReceiving_date(receivingDate);
            receiving.setBlood_type(bloodTypeText);
          
            receiving.setHospital_id(hospitalId);
            receiving.setStatus(statusText);

            receiving.setStorage_location(storageLocationText);

            // Update the receiving
            receivingDao.updateReceiving(receiving);
            fetchAllReceivings();
            JOptionPane.showMessageDialog(this, "Receiving updated successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for numeric fields.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format for Receiving Date.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating receiving: " + e.getMessage());
        }
    }

    private void deleteReceiving() {
        try {
            int receivingId = Integer.parseInt(receivingIdField.getText());

            // Ask for confirmation
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this receiving?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                receivingDao.deleteReceiving(receivingId);
                fetchAllReceivings();
                JOptionPane.showMessageDialog(this, "Receiving deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Deletion cancelled.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid receiving ID.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting receiving: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReceivingFrame().setVisible(true);
            }
        });
    }
}