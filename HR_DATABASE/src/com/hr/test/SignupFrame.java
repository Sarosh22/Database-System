package com.hr.test;

import javax.swing.*;

import com.hr.dao.UserDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupFrame extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    //private JTextField dobField;
    private JTextField roleField;
    private UserDao userDAO;

    public SignupFrame() {
        userDAO = new UserDao();

        setTitle("User Signup");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

      /*  JLabel dobLabel = new JLabel("Date of Birth (yyyy-mm-dd):");
        dobField = new JTextField(20)*/

        JLabel roleLabel = new JLabel("Role:");
        roleField = new JTextField(20);

        JButton signupButton = new JButton("Sign Up");

        // Set up the layout
        setLayout(new GridLayout(7, 2));
        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        //add(dobLabel);
       /* add(dobField);*/
        add(roleLabel);
        add(roleField);
        add(new JLabel()); // empty cell for spacing
        add(signupButton);

        // Add action listener to the signup button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertUser();
            }
        });
    }

    private void insertUser() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String pwd = new String(passwordField.getPassword());
       // String dob = dobField.getText();
        String role = roleField.getText();

        try {
            boolean isInserted = UserDao.insertUser(firstName, lastName, email, pwd, role);

            if (isInserted) {
                JOptionPane.showMessageDialog(this, "Failed to register user.");
            } else {
                JOptionPane.showMessageDialog(this, "User registered successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SignupFrame().setVisible(true);
            }
        });
    }
}


