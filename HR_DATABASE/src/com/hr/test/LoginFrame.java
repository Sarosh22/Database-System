package com.hr.test;

import javax.swing.*;

import com.hr.dao.MyDatabaseDAO;
import com.hr.util.DBUtil;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextArea emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 400); // Adjusted size to accommodate the welcome text
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Add welcome text
        JLabel welcomeLabel = new JLabel("WELCOME TO BLOOD ", SwingConstants.CENTER);
        welcomeLabel.setBounds(0, 10, 400, 30); // Adjust as necessary
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 18)); // Set font family, style, and size
        welcomeLabel.setForeground(Color.MAGENTA); // Set text color
        add(welcomeLabel);

        JLabel welcomeLabell = new JLabel(" DONATION MANAGEMENT SYSTEM", SwingConstants.CENTER);
        welcomeLabell.setBounds(20, 30, 400, 30); // Adjust as necessary
        welcomeLabell.setFont(new Font("Serif", Font.BOLD, 18)); // Set font family, style, and size
        welcomeLabell.setForeground(Color.MAGENTA); // Set text color
        add(welcomeLabell);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(60, 60, 100, 30);
        add(emailLabel);

        emailField = new JTextArea();
        emailField.setBounds(160, 60, 200, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 110, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 110, 200, 30);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 150, 100, 30);
        add(loginButton);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(210, 150, 100, 30);
        add(signupButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                loginUser(email, password);
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignupFrame().setVisible(true);
            }
        });
    }

    private void loginUser(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                new MenuFrame().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
        MyDatabaseDAO.fetchData();
    }
}
