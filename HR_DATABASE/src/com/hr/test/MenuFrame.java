package com.hr.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    public MenuFrame() {
        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton bloodRequestButton = new JButton("Blood Requests");
        bloodRequestButton.setBounds(100, 100, 200, 30);
        add(bloodRequestButton);


        JButton appointmentsButton = new JButton("Appointments");
        appointmentsButton.setBounds(100, 200, 200, 30);
        add(appointmentsButton);
        
        JButton donationButton = new JButton("Donation");
        donationButton.setBounds(100, 150, 200, 30);
        add(donationButton);

        JButton receivingButton = new JButton("Receiving");
        receivingButton.setBounds(100, 50, 200, 30);
        add(receivingButton);

        bloodRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BloodRequestFrame().setVisible(true);
            }
        });

       

        appointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AppointmentFrame().setVisible(true);
            }
        });
        
        donationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DonationFrame().setVisible(true);
            }
        });
        
        receivingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReceivingFrame().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuFrame().setVisible(true);
            }
        });
    }
}

