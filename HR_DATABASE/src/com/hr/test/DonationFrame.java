package com.hr.test;

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

public class DonationFrame extends JFrame {
    private JTextField donationIdField;
    private JTextField donorIdField;
    private JTextField donationDateField;
    private JTextField bloodTypeField;
    private JTextField amountField;
    private JTable table;
    private DefaultTableModel tableModel;
    private Donation donation;

    public DonationFrame() {
        donation = new Donation();

        setTitle("Donation Management");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        donationIdField = new JTextField(20);
        donorIdField = new JTextField(20);
        donationDateField = new JTextField(20);
        bloodTypeField = new JTextField(20);
        amountField = new JTextField(20);

        JButton fetchButton = new JButton("Fetch All");
        JButton insertButton = new JButton("Insert");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        tableModel = new DefaultTableModel(new String[]{"Donation ID", "Donor ID", "Donation Date", "Blood Type", "Amount"}, 0);
        table = new JTable(tableModel);

        // Set up the layout
        JPanel panel = new JPanel(new GridLayout(10, 5));
        panel.add(new JLabel("  Donation ID:"));
        panel.add(donationIdField);
        panel.add(new JLabel("  Donor ID:"));
        panel.add(donorIdField);
        panel.add(new JLabel("  Donation Date:"));
        panel.add(donationDateField);
        panel.add(new JLabel("  Blood Type:"));
        panel.add(bloodTypeField);
        panel.add(new JLabel("  Amount:"));
        panel.add(amountField);
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
                fetchAllDonations();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertDonation();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDonation();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDonation();
            }
        });
    }

    private void fetchAllDonations() {
        try {
            ArrayList<Donation> donations = donation.getAllDonations();
            tableModel.setRowCount(0);
            for (Donation donation : donations) {
                tableModel.addRow(new Object[]{
                        donation.getDonation_id(),
                        donation.getDonor_id(),
                        donation.getDonation_date(),
                        donation.getBlood_type(),
                        donation.getAmount()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching donations: " + e.getMessage());
        }
    }

    private void insertDonation() {
        try {
            // Check for empty fields
            String donorIdText = donorIdField.getText();
            String donationDateText = donationDateField.getText();
            String bloodTypeText = bloodTypeField.getText();
            String amountText = amountField.getText();

            if (donorIdText.isEmpty() || donationDateText.isEmpty() || bloodTypeText.isEmpty() || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            // Parse values
            int donorId = Integer.parseInt(donorIdText);
            java.sql.Date donationDate = java.sql.Date.valueOf(donationDateText);
            int amount = Integer.parseInt(amountText);

            // Create and set Donation object
            Donation donation = new Donation();
            donation.setDonor_id(donorId);
            donation.setDonation_date(donationDate);
            donation.setBlood_type(bloodTypeText);
            donation.setAmount(amount);

            // Insert the donation
            this.donation.insertDonation(donation);
            fetchAllDonations();
            JOptionPane.showMessageDialog(this, "Donation inserted successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for Donor ID or Amount.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format for Donation Date.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting donation: " + e.getMessage());
        }
    }

    private void updateDonation() {
        try {
            // Check for empty fields
            String donationIdText = donationIdField.getText();
            String donorIdText = donorIdField.getText();
            String donationDateText = donationDateField.getText();
            String bloodTypeText = bloodTypeField.getText();
            String amountText = amountField.getText();

            if (donationIdText.isEmpty() || donorIdText.isEmpty() || donationDateText.isEmpty() || bloodTypeText.isEmpty() || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            // Parse values
            int donationId = Integer.parseInt(donationIdText);
            int donorId = Integer.parseInt(donorIdText);
            java.sql.Date donationDate = java.sql.Date.valueOf(donationDateText);
            int amount = Integer.parseInt(amountText);

            // Create and set Donation object
            Donation donation = new Donation();
            donation.setDonation_id(donationId);
            donation.setDonor_id(donorId);
            donation.setDonation_date(donationDate);
            donation.setBlood_type(bloodTypeText);
            donation.setAmount(amount);

            // Update the donation
            this.donation.updateDonation(donation);
            fetchAllDonations();
            JOptionPane.showMessageDialog(this, "Donation updated successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for Donation ID, Donor ID or Amount.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format for Donation Date.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating donation: " + e.getMessage());
        }
    }

    private void deleteDonation() {
        try {
            int donation_id = Integer.parseInt(donationIdField.getText());

            // Ask for confirmation
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this donation?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                donation.deleteDonation(donation_id);
                fetchAllDonations();
                JOptionPane.showMessageDialog(this, "Donation deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid donation ID.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting donation: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DonationFrame().setVisible(true);
            }
        });
    }
}



