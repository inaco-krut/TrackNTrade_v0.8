package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JDialog implements ActionListener {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JLabel statusLabel;
    private boolean loggedIn;

    public LoginScreen(Frame parent) {
        super(parent, "Login Screen", true);

        // create UI components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        statusLabel = new JLabel("");

        // set layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(Box.createVerticalStrut(7));
        inputPanel.add(usernameLabel);
        inputPanel.add(Box.createVerticalStrut(7));
        inputPanel.add(usernameField);
        inputPanel.add(Box.createVerticalStrut(7));
        inputPanel.add(passwordLabel);
        inputPanel.add(Box.createVerticalStrut(7));
        inputPanel.add(passwordField);
        inputPanel.add(Box.createVerticalStrut(7));

        // create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createHorizontalGlue());

        // create status panel
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(statusLabel);
        statusPanel.add(Box.createHorizontalGlue());

        // add panels to dialog
        add(inputPanel);
        add(buttonPanel);
        add(statusPanel);

        // add action listeners
        loginButton.addActionListener(this);

        // set dialog properties
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // check username and password
            String[] acceptedPasswords = {"fqwerty", "dwolive", "rnspice", "ajetreat", "eajelly"};
            boolean validPassword = false;
            for (String acceptedPassword : acceptedPasswords) {
                if (password.equals(acceptedPassword)) {
                    validPassword = true;
                    break;
                }
            }
            if (username.equals("admin") && validPassword) {
                statusLabel.setText("Login successful");
                loggedIn = true;
                dispose();
            } else {
                statusLabel.setText("Login failed");
            }
        }
    }


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public static void main(String[] args) {
        LoginScreen loginScreen = new LoginScreen(null);

        if (loginScreen.isLoggedIn()) {
            // create and show main program window here
        } else {
            // exit the program if login fails
            System.exit(0);

        }
    }
}
