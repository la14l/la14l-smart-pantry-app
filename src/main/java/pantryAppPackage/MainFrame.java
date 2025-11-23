package pantryAppPackage;

import pantryAppPackage.gui.Dashboard;

import javax.swing.*;
import java.io.FileNotFoundException;

public class MainFrame extends JFrame implements PanelSwitchable {


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
    public void showDashboard(User user) throws FileNotFoundException {

        // Populate the inventory data into the GUI
        String[][] inventoryData = DashboardBackend.readTableDataFromFile("src/main/resources/pantry.txt", user.getID());

        DashboardBackend.createOrUpdateShoppingList("src/main/resources/pantry.txt", user.getID(), "src/main/resources/shopping_lists.txt");
        Object[][] shoppingData = DashboardBackend.getLowStockItemsFromShoppingFile("src/main/resources/shopping_lists.txt", user.getID());

        // Load the dashboard
        Dashboard dash = new Dashboard(user, this, inventoryData, "src/main/resources/pantry.txt", user.getID(), shoppingData, "src/main/resources/shopping_lists.txt");
        setContentPane(dash);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void showLogin() {
        LoginPanel login = new LoginPanel(this);
        setContentPane(login);
        revalidate();
        repaint();
    }
}
