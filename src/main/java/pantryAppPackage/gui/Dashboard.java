package pantryAppPackage.gui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    Dashboard (String username) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.setBackground(Color.WHITE);

        DashboardSidebar dashboardSidebar = new DashboardSidebar(username);
        dashboardSidebar.setPreferredSize(new Dimension(175, dashboardSidebar.getHeight()));
        Pantry pantry = new Pantry();
        Shopping shopping = new Shopping(this);

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

        mainPanel.add(dashboardSidebar);
        mainPanel.add(new JSeparator(SwingConstants.VERTICAL));
        mainPanel.add(currentWindow);

        add(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // TEST - REMOVE LATER
    public static void main(String[] args) {
        new Dashboard("Luffy");
    }

}
