package com.hr.test;

//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class BloodRequestFrame extends JFrame {
//    public BloodRequestFrame() {
//        setTitle("Blood Requests");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(null);
//
//        // Placeholder for blood request management UI components
//        JLabel label = new JLabel("Blood Requests Management");
//        label.setBounds(50, 50, 300, 30);
//        add(label);
//    }
//}



import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.hr.util.DBUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BloodRequestFrame extends JFrame {
    private JTextField requestIdField;
    private JTextField hospitalIdField;
    private JTextField requestDateField;
    private JTextField bloodTypeField;
    private JTextField statusField;
    private JTable table;
    private DefaultTableModel tableModel;
    private BloodRequest bloodRequest;

    public BloodRequestFrame() {
        bloodRequest = new BloodRequest();

        setTitle("Blood Request Management");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        requestIdField = new JTextField(20);
        hospitalIdField = new JTextField(20);
        requestDateField = new JTextField(20);
        bloodTypeField = new JTextField(20);
      
        statusField = new JTextField(20);

        JButton fetchButton = new JButton("Fetch All");
        JButton insertButton = new JButton("Insert");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        tableModel = new DefaultTableModel(new String[]{"Request ID", "Hospital ID", "Request Date", "Blood Type", "Amount", "Status"}, 0);
        table = new JTable(tableModel);

        // Set up the layout
        JPanel panel = new JPanel(new GridLayout(10, 5));
        panel.add(new JLabel("  Request ID:"));
        panel.add(requestIdField);
        panel.add(new JLabel("  Hospital ID:"));
        panel.add(hospitalIdField);
        panel.add(new JLabel("  Request Date:"));
        panel.add(requestDateField);
        panel.add(new JLabel("  Blood Type:"));
        panel.add(bloodTypeField);
      
        panel.add(new JLabel("  Status:"));
        panel.add(statusField);
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
                fetchAllRequests();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertBloodRequest();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	updateBloodRequest();
            }

			private void updateBloodRequest() {
				// TODO Auto-generated method stub
				
			}
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBloodRequest();
            }
        });
    }

    private void fetchAllRequests() {
        try {
            ArrayList<BloodRequest> requests = bloodRequest.getAllBloodRequests();
            tableModel.setRowCount(0);
            for (BloodRequest request : requests) {
                tableModel.addRow(new Object[]{
                        request.getRequest_id(),
                        request.getHospital_id(),
                        request.getRequest_date(),
                        request.getBlood_type(),
                        request.getAmount(),
                        request.getStatus()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching blood requests: " + e.getMessage());
        }
    }

    private void insertBloodRequest() {
        try {
            // Check for empty fields
            String hospitalIdText = hospitalIdField.getText();
            String requestDateText = requestDateField.getText();
            String bloodTypeText = bloodTypeField.getText();
            String statusText = statusField.getText();

            if (hospitalIdText.isEmpty() || requestDateText.isEmpty() || bloodTypeText.isEmpty() || statusText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            // Parse values
            int hospitalId = Integer.parseInt(hospitalIdText);
            java.sql.Date requestDate = java.sql.Date.valueOf(requestDateText);

            // Create and set BloodRequest object
            BloodRequest request = new BloodRequest();
            request.setHospital_id(hospitalId);
            request.setRequest_date(requestDate);
            request.setBlood_type(bloodTypeText);
            request.setStatus(statusText);

            // Insert the request
            bloodRequest.insertBloodRequest(request);
            fetchAllRequests();
            JOptionPane.showMessageDialog(this, "Blood request inserted successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for Hospital ID.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format for Request Date.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting blood request: " + e.getMessage());
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

    private void deleteBloodRequest() {
        try {
            int request_id = Integer.parseInt(requestIdField.getText());
            
            // Ask for confirmation
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this blood request?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                bloodRequest.deleteBloodRequest(request_id);
                fetchAllRequests();
                JOptionPane.showMessageDialog(this, "Blood request deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Deletion cancelled.");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid request ID.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting blood request: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BloodRequestFrame().setVisible(true);
            }
        });
    }
}
