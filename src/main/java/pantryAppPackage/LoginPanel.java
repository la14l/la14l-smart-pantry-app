package main.java.pantryAppPackage;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel() {
        // Set the layout of the panel to BorderLayout, this is not final we could use another layout.
        setLayout(new BorderLayout());
        // Add a JLabel Centered on the border layout, and centered horizontally. Check BorderLayouts on Google for more info.
        add(new JLabel("Login Screen", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
