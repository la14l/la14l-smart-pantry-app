package pantryAppPackage.gui;

import pantryAppPackage.DashboardBackend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Pantry extends JPanel {

    private Inventory inventory;
    private AddItemPantry addItemPantry;
    private JButton AddItemButton;
    private String pantryFilePath;
    private String currentUserID;

    Pantry (String[][] inventoryData, String pantryFilePath, String userID) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        this.pantryFilePath = pantryFilePath;
        this.currentUserID = userID;
        SearchFilter searchFilter = new SearchFilter();
        inventory = new Inventory(inventoryData);
        addItemPantry = new AddItemPantry();
        AddItemButton = addItemPantry.getAddItemBtn();

        AddItemButtonHandler addItemButtonHandler = new AddItemButtonHandler();
        AddItemButton.addActionListener(addItemButtonHandler);

        searchFilter.getSearchBar().addActionListener(e -> {
            String filterCategory = (String) searchFilter.getSearchFilterComboBox().getSelectedItem();
            inventory.filterTableWithText(searchFilter.getSearchBar().getText(), filterCategory);
        });

        searchFilter.getAboutToExpireItemsCheckBox().addActionListener(e -> {
            inventory.filterTableWithCheckBoxes("About To Expire", searchFilter.getAboutToExpireItemsCheckBox().isSelected());
        });

        searchFilter.getLowStockItemsCheckBox().addActionListener(e -> {
            inventory.filterTableWithCheckBoxes("Low Stock Items", searchFilter.getLowStockItemsCheckBox().isSelected());
        });

        add(searchFilter);
        add(inventory);
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(addItemPantry);
    }

    private class AddItemButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)  {
            String name = addItemPantry.getItemName();
            String category = addItemPantry.getItemCategory();
            String quantity = addItemPantry.getItemQuantity();
            String unit = addItemPantry.getItemUnit();
            String threshold = addItemPantry.getItemThreshold();
            String expiry = addItemPantry.getItemExpiryDate();

            DefaultTableModel inventoryModel = inventory.getModel();


            if (name.isEmpty() || category.isEmpty() || quantity.isEmpty() || unit.isEmpty() || threshold.isEmpty() || expiry.isEmpty() ) {
                JOptionPane.showMessageDialog(null, "You have left one or more text fields.");
            } else {

                // Update the GUI
                String itemID = "ITM" + String.format("%03d", inventoryModel.getRowCount() + 1);
                inventoryModel.addRow(new Object[]{itemID, name, category, quantity, unit, threshold, expiry});

                // Update the database
                String[] itemData = {itemID, name, category, quantity, unit, threshold, expiry};
                try {
                    DashboardBackend.writeItemDataIntoPantryFile(pantryFilePath, currentUserID, itemData);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                addItemPantry.setItemName("");
                addItemPantry.setItemCategory("");
                addItemPantry.setItemQuantity("");
                addItemPantry.setItemUnit("");
                addItemPantry.setItemThreshold("");
                addItemPantry.setItemExpiryDate("");

            }
        }
    }
}
