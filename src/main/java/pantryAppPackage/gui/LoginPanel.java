package pantryAppPackage.gui;

import pantryAppPackage.backend.AuthService;
import pantryAppPackage.backend.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class LoginPanel extends JPanel {
    private final AuthService authService = new AuthService();
    private final MainFrame mainFrame;

    // Labels
    JLabel loginLabel, registerLabel, nameLabel, passwordLabel, emailLabel, phoneLabel;

    // Inputs
    JTextField nameField, emailField, phoneField;
    JPasswordField passwordField;

    // Buttons
    JButton enterButton, registerChoiceButton, loginChoiceButton;

    // Values
    boolean operationIsLogin = true;
    String name;
    String password;
    String phoneNumber;
    String email;

    public LoginPanel(MainFrame mainFrame) {
        // Base layout
        this.mainFrame = mainFrame;
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

        // Email
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(emailLabel, c);

        emailField = new JTextField(20);
        emailField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 1;
        add(emailField, c);

        // Name (Register only)
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 2;
        add(nameLabel, c);

        nameField = new JTextField(20);
        nameField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 2;
        add(nameField, c);

        // Password
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 3;
        add(passwordLabel, c);

        passwordField = new JPasswordField(20);
        passwordField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 3;
        add(passwordField, c);

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
        enterButton.addActionListener(e -> {
            try {
                onEnter();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }); // Using lambda to instantiate anonymous class. Skipping the creation of a class.
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
        nameLabel.setVisible(!isLogin);
        nameField.setVisible(!isLogin);
        phoneLabel.setVisible(!isLogin);
        phoneField.setVisible(!isLogin);

        // Displaying the right title based on the mode
        loginLabel.setVisible(isLogin);
        registerLabel.setVisible(!isLogin);

        revalidate(); // re-calculate layout (positions & sizes).
        repaint(); // redraw (paint) what’s on the screen to show changes.
    }


    private void onEnter() throws FileNotFoundException  {
        // Store the user input
        email = emailField.getText().trim().toLowerCase();
        char[] pw = passwordField.getPassword();
        password = new String(pw).trim();
        if (!operationIsLogin) {
            name = nameField.getText().trim();
            phoneNumber = phoneField.getText().trim();
        }

        if (isValidInput()) {
            // Do action based on mode
            if (operationIsLogin) {
                if (authService.login(email, password)) {
                    System.out.println("Login successful");

                    try {
                    mainFrame.showDashboard(authService.getCurrentUser());}
                    catch (FileNotFoundException e) {
                        System.out.println("Rendering Dashboard Failed Due To an IO Exception");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Please check your email or password.");
                }

            } else {
                try {
                    authService.register(name, email, phoneNumber, password);
                } catch (IllegalStateException e) {
                    JOptionPane.showMessageDialog(this, "Your email is already registered, consider logging in.");
                    setMode(true);
                    return;
                }
                System.out.println("Registration successful");

                try {
                    mainFrame.showDashboard(authService.getCurrentUser());}
                catch (FileNotFoundException e) {
                    System.out.println("Rendering Dashboard Failed Due To an IO Exception");
                }

            }
        }
    }

    private boolean isValidInput() {
        if (operationIsLogin) {
            // Check if all fields all filled out if login
            if ((email.isEmpty() || password.isEmpty())) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields to login.");
                return false;
            }
            // Checks if the email is valid.
            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email.");
                return false;
            }
        }


        if (!operationIsLogin) {
            // Check if all fields all filled out if registration
            if ((name.isEmpty() || password.isEmpty() || email.isEmpty() || phoneNumber.isEmpty())) {
                JOptionPane.showMessageDialog(this, "Please fill the fields to register.");
                return false;
            }

            // Checks if the phone number is an integer and therefore a valid phone number (simplified checking method).
            try {
                Integer.parseInt(phoneNumber);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid phone number.");
                return false;
            }
        }


        // Checks if the password is long enough
        if (password.length() < 4) {
            JOptionPane.showMessageDialog(this, "The password is too short.");
            return false;
        }


        return true;
    }
}
