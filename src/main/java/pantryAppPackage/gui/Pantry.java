package pantryAppPackage.gui;

import javax.swing.*;
import java.awt.*;

public class Pantry extends JPanel {

    Pantry () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        SearchFilter searchFilter = new SearchFilter();
        Inventory inventory = new Inventory();
        AddItemPantry addItemPantry = new AddItemPantry();

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
}
