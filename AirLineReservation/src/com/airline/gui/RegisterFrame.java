package com.airline.gui;

import com.airline.dao.UserDAO;
import com.airline.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;

    public RegisterFrame() {
        setTitle("Register");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this::handleRegister);
        add(registerButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        add(cancelButton);
    }

    private void handleRegister(ActionEvent e) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.registerUser(new User(0, name, email, password));

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful! Please login.");
            this.dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Email may already be in use.");
        }
    }
}