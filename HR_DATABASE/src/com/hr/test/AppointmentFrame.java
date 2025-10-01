package com.hr.test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.hr.bean.Appointment;
import com.hr.util.DBUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentFrame extends JFrame {
    private JTextField appointmentIdField;
    private JTextField donorIdField;
    private JTextField appointmentDateField;
    private JTextField hospitalIdField;
    private JTextField statusField;
    private JTextField notesField;
    private JTable table;
    private DefaultTableModel tableModel;
    private Appointment appointment;

    public AppointmentFrame() {
        appointment = new Appointment();

        setTitle("Appointment Management");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        appointmentIdField = new JTextField(20);
        donorIdField = new JTextField(20);
        appointmentDateField = new JTextField(20);
        hospitalIdField = new JTextField(20);
        statusField = new JTextField(20);
        notesField = new JTextField(20);

        JButton fetchButton = new JButton("Fetch All");
        JButton insertButton = new JButton("Insert");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        tableModel = new DefaultTableModel(new String[]{"Appointment ID", "Donor ID", "Appointment Date", "Hospital ID", "Status", "Notes"}, 0);
        table = new JTable(tableModel);

        // Set up the layout
        JPanel panel = new JPanel(new GridLayout(10, 5));
        panel.add(new JLabel("  Appointment ID:"));
        panel.add(appointmentIdField);
        panel.add(new JLabel("  Donor ID:"));
        panel.add(donorIdField);
        panel.add(new JLabel("  Appointment Date:"));
        panel.add(appointmentDateField);
        panel.add(new JLabel("  Hospital ID:"));
        panel.add(hospitalIdField);
        panel.add(new JLabel("  Status:"));
        panel.add(statusField);
        panel.add(new JLabel("  Notes:"));
        panel.add(notesField);
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
                fetchAllAppointments();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertAppointment();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAppointment();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAppointment();
            }
        });
    }

    private void fetchAllAppointments() {
        try {
            ArrayList<Appointment> appointments = appointment.getAllAppointments();
            tableModel.setRowCount(0);
            for (Appointment appointment : appointments) {
                tableModel.addRow(new Object[]{
                        appointment.getAppointment_id(),
                        appointment.getDonor_id(),
                        appointment.getAppointment_date(),
                        appointment.getHospital_id(),
                        appointment.getStatus(),
                        appointment.getNotes()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching appointments: " + e.getMessage());
        }
    }

    private void insertAppointment() {
        try {
            // Check for empty fields
            String donorIdText = donorIdField.getText();
            String appointmentDateText = appointmentDateField.getText();
            String hospitalIdText = hospitalIdField.getText();
            String statusText = statusField.getText();
            String notesText = notesField.getText();

            if (donorIdText.isEmpty() || appointmentDateText.isEmpty() || hospitalIdText.isEmpty() || statusText.isEmpty() || notesText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields");
                return;
            }

            Appointment newAppointment = new Appointment();
            newAppointment.setUser_id(Integer.parseInt(donorIdField.getText()));
            newAppointment.setAppointment_date(new java.sql.Timestamp(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(appointmentDateField.getText()).getTime()));
            newAppointment.setHospital_id(Integer.parseInt(hospitalIdField.getText()));
            newAppointment.setStatus(statusField.getText());
            newAppointment.setNotes(notesField.getText());
            appointment.insertAppointment(newAppointment);
            fetchAllAppointments();
            JOptionPane.showMessageDialog(this, "Appointment inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting appointment: " + e.getMessage());
        }
    }

    private void updateAppointment() {
        try {
            int appointmentId = Integer.parseInt(appointmentIdField.getText());

            // Check for empty fields
            String donorIdText = donorIdField.getText();
            String appointmentDateText = appointmentDateField.getText();
            String hospitalIdText = hospitalIdField.getText();
            String statusText = statusField.getText();
            String notesText = notesField.getText();

            if (donorIdText.isEmpty() || appointmentDateText.isEmpty() || hospitalIdText.isEmpty() || statusText.isEmpty() || notesText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields");
                return;
            }

            Appointment updatedAppointment = new Appointment();
            updatedAppointment.setAppointment_id(appointmentId);
            updatedAppointment.setUser_id(Integer.parseInt(donorIdField.getText()));
            updatedAppointment.setAppointment_date(new java.sql.Timestamp(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(appointmentDateField.getText()).getTime()));
            updatedAppointment.setHospital_id(Integer.parseInt(hospitalIdField.getText()));
            updatedAppointment.setStatus(statusField.getText());
            updatedAppointment.setNotes(notesField.getText());
            appointment.updateAppointment(updatedAppointment);
            fetchAllAppointments();
            JOptionPane.showMessageDialog(this, "Appointment updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating appointment: " + e.getMessage());
        }
    }

    private void deleteAppointment() {
        try {
            int appointmentId = Integer.parseInt(appointmentIdField.getText());
            appointment.updateAppointment(appointmentId);
            fetchAllAppointments();
            JOptionPane.showMessageDialog(this, "Appointment deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting appointment: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppointmentFrame frame = new AppointmentFrame();
            frame.setVisible(true);
        });
    }
}