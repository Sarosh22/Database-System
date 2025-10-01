package com.hr.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BloodTestFrame extends JFrame {
    public BloodTestFrame() {
        setTitle("Blood Tests");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Placeholder for blood test management UI components
        JLabel label = new JLabel("Blood Tests Management");
        label.setBounds(50, 50, 300, 30);
        add(label);
    }
}
