package pantryAppPackage.gui;

import pantryAppPackage.AuthService;
import pantryAppPackage.MainFrame;
import pantryAppPackage.User;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {

    private Pantry pantry;
    private Shopping shopping;

    public Dashboard(User user, MainFrame mainFrame, String[][] pantryData, String pantryFilePath, String userID, Object[][] shoppingData, String shoppingListFilePath) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.setBackground(Color.WHITE);

        DashboardSidebar dashboardSidebar = new DashboardSidebar(user.getName());
        dashboardSidebar.setPreferredSize(new Dimension(175, dashboardSidebar.getHeight()));
        shopping = new Shopping(mainFrame, shoppingData, pantryFilePath, userID, shoppingListFilePath, null);
        pantry = new Pantry(pantryData, pantryFilePath, userID, shoppingListFilePath, shopping);
        shopping.setPantry(pantry);

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
