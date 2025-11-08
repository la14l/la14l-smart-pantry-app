package pantryAppPackage;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        // Set frame title
        setTitle("Smart Pantry Inventory System");
        // Set frame size
        setSize(900, 700);
        // Function of the X button on the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the frame on the screen.
        setLocationRelativeTo(null);

        // Set the content of the JFrame to the content of LoginPanel. This is how to have different panels for the project (Login, Dashboard, ...)
        setContentPane(new LoginPanel());
        // Set the JFrame visible
        setVisible(true);
    }
}
