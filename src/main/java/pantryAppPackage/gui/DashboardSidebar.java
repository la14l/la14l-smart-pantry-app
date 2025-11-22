package pantryAppPackage.gui;

import javax.swing.*;
import java.awt.*;

public class DashboardSidebar extends JPanel {

    private JButton pantryMenuBtn;
    private JButton shoppingMenuBtn;
    private JButton logoutMenuBtn;
    private JLabel profile;

    DashboardSidebar(String username) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        JLabel logo = new JLabel("Smart Pantry");
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setMaximumSize(new Dimension(Integer.MAX_VALUE, logo.getPreferredSize().height));

        pantryMenuBtn = new JButton("Pantry");
        shoppingMenuBtn = new JButton("Shopping");
        logoutMenuBtn = new JButton("Logout");

        // Enabling the buttons to take the full width of the sidebar
        pantryMenuBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, pantryMenuBtn.getPreferredSize().height));
        shoppingMenuBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, shoppingMenuBtn.getPreferredSize().height));
        logoutMenuBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, shoppingMenuBtn.getPreferredSize().height));

        profile = new JLabel(username);

        add(logo);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(pantryMenuBtn);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(shoppingMenuBtn);
        add(Box.createVerticalGlue());
        add(logoutMenuBtn);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(profile);

    }

    public JButton getPantryMenuBtn() {return pantryMenuBtn;}
    public JButton getShoppingMenuBtn() {return shoppingMenuBtn;}
    public JButton getLogoutMenuBtn() {return logoutMenuBtn;}
}
