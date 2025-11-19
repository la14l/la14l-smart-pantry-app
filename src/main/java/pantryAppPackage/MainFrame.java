package pantryAppPackage;

import pantryAppPackage.gui.Dashboard;

import javax.swing.*;

public class MainFrame extends JFrame implements PanelSwitchable{


    public MainFrame() {
        // Set frame title
        setTitle("Smart Pantry Inventory System");
        // Set frame size
        setSize(900, 700);
        // Function of the X button on the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the frame on the screen.
        setLocationRelativeTo(null);

        // Set the JFrame visible
        setVisible(true);

        // Start by showing the login page
        showLogin();
    }

    @Override
    public void showDashboard(User user) {
        Dashboard dash = new Dashboard(user, this);
        setContentPane(dash);
        revalidate();
        repaint();
    }

    @Override
    public void showLogin() {
        LoginPanel login = new LoginPanel(this);
        setContentPane(login);
        revalidate();
        repaint();
    }
}
