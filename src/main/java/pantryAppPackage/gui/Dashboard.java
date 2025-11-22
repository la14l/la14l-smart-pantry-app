package pantryAppPackage.gui;

import pantryAppPackage.backend.AuthService;
import pantryAppPackage.backend.MainFrame;
import pantryAppPackage.backend.User;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {

    public Dashboard(User user, MainFrame mainFrame, String[][] pantryData, String pantryFilePath, String userID) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.setBackground(Color.WHITE);

        DashboardSidebar dashboardSidebar = new DashboardSidebar(user.getName());
        dashboardSidebar.setPreferredSize(new Dimension(175, dashboardSidebar.getHeight()));
        Pantry pantry = new Pantry(pantryData, pantryFilePath, userID);
        Shopping shopping = new Shopping(mainFrame);

        JPanel currentWindow = new JPanel();
        currentWindow.setLayout(new CardLayout());
        currentWindow.add(pantry, "Pantry");
        currentWindow.add(shopping, "Shopping");

        dashboardSidebar.getPantryMenuBtn().addActionListener(e -> {
            CardLayout cl = (CardLayout) currentWindow.getLayout();
            cl.show(currentWindow, "Pantry");
        });

        dashboardSidebar.getShoppingMenuBtn().addActionListener(e -> {
            CardLayout cl = (CardLayout) currentWindow.getLayout();
            cl.show(currentWindow, "Shopping");
        });

        dashboardSidebar.getLogoutMenuBtn().addActionListener(e -> {
            AuthService.logout();
            mainFrame.showLogin();
            System.out.println("Logout successful");
        });

        this.add(dashboardSidebar);
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(currentWindow);
    }
}
