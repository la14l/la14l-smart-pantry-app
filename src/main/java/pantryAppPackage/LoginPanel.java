package pantryAppPackage;

import javax.swing.*;
import java.awt.*;


public class LoginPanel extends JPanel {
    // Labels
    JLabel loginLabel, registerLabel, usernameLabel, passwordLabel, emailLabel, phoneLabel;

    // Inputs
    JTextField usernameField, emailField, phoneField;
    JPasswordField passwordField;

    // Buttons
    JButton enterButton, registerChoiceButton, loginChoiceButton;

    // Values
    boolean operationIsLogin = true;
    String username;
    String password;
    String phoneNumber;
    String email;

    public LoginPanel() {
        // Base layout
        setLayout(new GridBagLayout()); // Using the griBagLayout
        GridBagConstraints c = new GridBagConstraints(); // Creating a constraint object for element positioning. The elements are centered because anchor equals center by default
        c.insets = new Insets(10, 10, 10, 10); // Create padding between the components
        c.fill = GridBagConstraints.HORIZONTAL; // Make the components stretch over their allowed horizontal space

        // Fonts (panel-scoped)
        Font labelFont = new Font("SansSerif", Font.BOLD, 16);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);

        // Login and Register titles (Add one of these based on the mode)
        loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setFont(titleFont);

        registerLabel = new JLabel("Register", SwingConstants.CENTER);
        registerLabel.setFont(titleFont);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2; // Letting the element take the place of two elements horizontally.
        add(loginLabel, c); // Adding the element to the grid based on the constraints c
        add(registerLabel, c);

        // Username
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(usernameLabel, c);

        usernameField = new JTextField(20);
        usernameField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 1;
        add(usernameField, c);

        // Password
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 2;
        add(passwordLabel, c);

        passwordField = new JPasswordField(20);
        passwordField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 2;
        add(passwordField, c);

        // Email (Register only)
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 3;
        add(emailLabel, c);

        emailField = new JTextField(20);
        emailField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 3;
        add(emailField, c);

        // Phone (Register only)
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 4;
        add(phoneLabel, c);

        phoneField = new JTextField(20);
        phoneField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 4;
        add(phoneField, c);

        // Enter button (span 2 cols)
        enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> onEnter()); // Using lambda to instantiate anonymous class. Skipping the creation of a class.
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        add(enterButton, c);

        // Mode buttons (Login/Register)
        JPanel modePanel = new JPanel(); // Making a panel to contain the buttons so they have equal sizes. And not affected by the grid's different column widths at the moment.
        loginChoiceButton = new JButton("Login");
        registerChoiceButton = new JButton("Register");

        loginChoiceButton.addActionListener(e -> setMode(true));
        registerChoiceButton.addActionListener(e -> setMode(false));

        modePanel.add(loginChoiceButton);
        modePanel.add(registerChoiceButton);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        add(modePanel, c);

        // Start in Login mode (hide email/phone)
        setMode(true);
    }


    private void setMode(boolean isLogin) {
        operationIsLogin = isLogin;

        // Buttons reflect selection (disabled = selected)
        loginChoiceButton.setEnabled(!isLogin);
        registerChoiceButton.setEnabled(isLogin);

        // Show/hide register-only fields
        emailLabel.setVisible(!isLogin);
        emailField.setVisible(!isLogin);
        phoneLabel.setVisible(!isLogin);
        phoneField.setVisible(!isLogin);

        // Displaying the right title based on the mode
        loginLabel.setVisible(isLogin);
        registerLabel.setVisible(!isLogin);

        revalidate(); // re-calculate layout (positions & sizes).
        repaint(); // redraw (paint) what’s on the screen to show changes.
    }


    private void onEnter() {
        // Store the user input
        username = usernameField.getText().trim();
        char[] pw = passwordField.getPassword();
        password = new String(pw).trim();
        if (!operationIsLogin) {
            email = emailField.getText().trim();
            phoneNumber = phoneField.getText().trim();
        }

        // Check if all fields all filled out based on mode
        if (operationIsLogin && (username.isEmpty() || password.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields to login.");
            return;
        }

        if (!operationIsLogin && (username.isEmpty() || password.isEmpty() || email.isEmpty() || phoneNumber.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Please fill the fields to register.");
            return;
        }

        // Do action based on mode
        if (operationIsLogin) {
            // TODO: Check if the user is in the users.txt file and with the correct password
            // if ok -> proceed; else -> show error
            JOptionPane.showMessageDialog(this, "(Login) Not implemented yet.");
        } else {
            // TODO: Register the user into the users.txt file
            JOptionPane.showMessageDialog(this, "(Register) Not implemented yet.");
        }
    }
}
