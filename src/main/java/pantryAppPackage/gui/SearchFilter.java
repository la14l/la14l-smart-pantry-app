package pantryAppPackage.gui;

import javax.swing.*;
import java.awt.*;

public class SearchFilter extends JPanel {

    private JTextField searchBar;
    private JCheckBox aboutToExpireItemsCheckBox, lowStockItemsCheckBox;
    private final String[] categories = {"Name", "Category"};
    private JComboBox searchFilterComboBox;

    SearchFilter () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Creating two sections
        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.X_AXIS));
        topSection.setBackground(Color.WHITE);

        JPanel bottomSection = new JPanel();
        bottomSection.setLayout(new BoxLayout(bottomSection, BoxLayout.X_AXIS));
        bottomSection.setBackground(Color.WHITE);

        // Creating the input components
        searchBar = new JTextField();
        searchFilterComboBox = new JComboBox(categories);
        searchFilterComboBox.setBackground(Color.WHITE);

        JLabel bottomSectionTitle = new JLabel("Filter by");
        aboutToExpireItemsCheckBox = new JCheckBox("About To Expire");
        lowStockItemsCheckBox = new JCheckBox("Low Stock Items");

        aboutToExpireItemsCheckBox.setBackground(Color.WHITE);
        lowStockItemsCheckBox.setBackground(Color.WHITE);

        topSection.add(searchBar);
        topSection.add(Box.createRigidArea(new Dimension(5, 0)));
        topSection.add(searchFilterComboBox);

        bottomSection.add(bottomSectionTitle);
        bottomSection.add(Box.createHorizontalGlue());
        bottomSection.add(aboutToExpireItemsCheckBox);
        bottomSection.add(Box.createRigidArea(new Dimension(5, 0)));
        bottomSection.add(lowStockItemsCheckBox);

        add(topSection);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(bottomSection);
    }

    public JTextField getSearchBar() {return searchBar;}
    public JCheckBox getAboutToExpireItemsCheckBox() {return aboutToExpireItemsCheckBox;}
    public JCheckBox getLowStockItemsCheckBox() {return lowStockItemsCheckBox;}
    public JComboBox getSearchFilterComboBox() {return searchFilterComboBox;}
}
