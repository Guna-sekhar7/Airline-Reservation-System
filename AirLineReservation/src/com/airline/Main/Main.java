package com.airline.Main;

import com.airline.gui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Ensure the GUI is created on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}