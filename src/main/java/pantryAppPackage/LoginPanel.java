package main.java.pantryAppPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    JLabel registerLoginLabel, usernameTextLabel, passwordTextLabel;
    JTextField usernameTextField;
    JPasswordField passwordField;
    JButton registerLoginButton, registerChoiceButton, loginChoiceButton;

    boolean operationIsLogin = true;
    String username;
    String password;
    boolean authenticated = false;

    public LoginPanel() {
        // Setting the layout of the panel to a GridBagLayout.
        setLayout(new GridBagLayout());
        // Using this class to create constraints object c for each panel element.
        GridBagConstraints c = new GridBagConstraints();

        // Set the fonts.
        UIManager.put("Label.font", new Font("SansSerif", Font.BOLD, 15));
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 15));
        UIManager.put("TextField.font", new Font("SansSerif", Font.BOLD, 15));

        c.insets = new Insets(10, 10, 10, 10); // padding between components
        c.fill = GridBagConstraints.HORIZONTAL; // Making the components fill their horizontal space


        // Title
        registerLoginLabel = new JLabel("Login / Register", SwingConstants.CENTER);
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        registerLoginLabel.setFont(titleFont);
        add(registerLoginLabel, c);

        // Username label
        usernameTextLabel = new JLabel("Username:");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(usernameTextLabel, c);

        // Username field
        usernameTextField = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        add(usernameTextField, c);

        // Password label
        passwordTextLabel = new JLabel("Password:");
        c.gridx = 0;
        c.gridy = 2;
        add(passwordTextLabel, c);

        // Password field
        passwordField = new JPasswordField(20);
        c.gridx = 1;
        c.gridy = 2;
        add(passwordField, c);

        // Main action button
        registerLoginButton = new JButton("Enter");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        registerLoginButton.addActionListener(new EnterButton());
        add(registerLoginButton, c);

        // Option buttons
        JPanel buttonPanel = new JPanel();
        registerChoiceButton = new JButton("Register");
        loginChoiceButton = new JButton("Login");
        loginChoiceButton.addActionListener(new LoginButton());
        registerChoiceButton.addActionListener(new RegisterButton());
        loginChoiceButton.setEnabled(!operationIsLogin);  // selected
        registerChoiceButton.setEnabled(operationIsLogin);
        buttonPanel.add(loginChoiceButton);
        buttonPanel.add(registerChoiceButton);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        add(buttonPanel, c);
    }

    private class LoginButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginChoiceButton.setEnabled(false);
            registerChoiceButton.setEnabled(true);
            operationIsLogin = true;
        }
    }

    private class RegisterButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginChoiceButton.setEnabled(true);
            registerChoiceButton.setEnabled(false);
            operationIsLogin = false;
        }
    }

    private class EnterButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (usernameTextField.getText().isEmpty() || passwordField.getPassword().length == 0 || (registerChoiceButton.isEnabled() && loginChoiceButton.isEnabled())) {
                JOptionPane.showMessageDialog(null, "Please fill out all necessary information.");
            } else {
                username = usernameTextField.getText().trim();
                char[] pw = passwordField.getPassword();
                password = new String(pw).trim();
                if (operationIsLogin) {
                    // TODO Search the users.txt file for matching user and assign the value of authenticated
                } else {
                    authenticated = true;
                    // TODO Add the user to the users.txt file
                }
            }
        }
    }
}
