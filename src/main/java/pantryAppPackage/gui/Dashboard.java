package pantryAppPackage.gui;

import pantryAppPackage.MainFrame;
import pantryAppPackage.User;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {

    public Dashboard(User user, MainFrame mainFrame) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.setBackground(Color.WHITE);

        DashboardSidebar dashboardSidebar = new DashboardSidebar(user.getName());
        dashboardSidebar.setPreferredSize(new Dimension(175, dashboardSidebar.getHeight()));
        Pantry pantry = new Pantry();
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

        this.add(dashboardSidebar);
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(currentWindow);
    }
}
