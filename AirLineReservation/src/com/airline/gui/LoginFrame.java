package com.airline.gui;

import com.airline.dao.UserDAO;
import com.airline.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginFrame() {
        setTitle("Airline Reservation - Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        emailField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        add(new JLabel("Email:"));
        add(emailField);

        add(new JLabel("Password:"));
        add(passwordField);

        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(this::handleLogin);
        registerButton.addActionListener(this::handleRegister);
    }

    private void handleLogin(ActionEvent e) {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email and password are required.");
            return;
        }

        User user = new UserDAO().loginUser(email, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            this.dispose();
            new BookingFrame(user).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password.");
        }
    }

    private void handleRegister(ActionEvent e) {
        this.dispose();
        new RegisterFrame().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}