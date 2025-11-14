package pantryAppPackage.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardSidebar extends JPanel {

    private JButton inventoryMenuBtn;
    private JButton shoppingMenuBtn;
    private JLabel profile;
    private String selectedMenu;    // INVENTORY or SHOPPING

    DashboardSidebar (String username) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        JLabel logo = new JLabel("Smart Pantry");
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setMaximumSize(new Dimension(Integer.MAX_VALUE, logo.getPreferredSize().height));

        inventoryMenuBtn = new JButton("Inventory");
        shoppingMenuBtn = new JButton("Shopping");

        // Enabling the buttons to take the full width of the sidebar
        inventoryMenuBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, inventoryMenuBtn.getPreferredSize().height));
        shoppingMenuBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, shoppingMenuBtn.getPreferredSize().height));

        profile = new JLabel(username);

        add(logo);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(inventoryMenuBtn);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(shoppingMenuBtn);
        add(Box.createVerticalGlue());
        add(profile);

        selectedMenu = "INVENTORY";

        inventoryMenuBtn.setEnabled(false);

        MenuBtnEventHandler menuBtnEventHandler = new MenuBtnEventHandler();
        inventoryMenuBtn.addActionListener(menuBtnEventHandler);
        shoppingMenuBtn.addActionListener(menuBtnEventHandler);
    }

    private class MenuBtnEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedMenu.equals("INVENTORY")) {
                inventoryMenuBtn.setEnabled(true);
                shoppingMenuBtn.setEnabled(false);
                selectedMenu = "SHOPPING";
            } else {
                inventoryMenuBtn.setEnabled(false);
                shoppingMenuBtn.setEnabled(true);
                selectedMenu = "INVENTORY";
            }

        }
    }

    // TEST - REMOVE LATER
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dashboard");
        frame.add(new DashboardSidebar("Luffy"));
        frame.setSize(200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
