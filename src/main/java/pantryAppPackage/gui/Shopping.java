package pantryAppPackage.gui;

import pantryAppPackage.DashboardBackend;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.FileNotFoundException;

public class Shopping extends JPanel {

    private JButton addEntryToShoppingListBtn;
    private DefaultTableModel model;
    private Pantry pantry;
    // SAMPLE DATA (LATER ON WE READ FROM FILES) (DELETE THIS LATER ON) -------------
    String[] columnNames = {"Item Name", "Quantity", "Unit", "Status"};

//    Object[][] data = {
//            {"Rice", "5", "kg", true},
//            {"Milk", "2", "L", false},
//            {"Eggs", "12", "pcs", true},
//            {"Chicken Breast", "1", "kg", false},
//            {"Butter", "0", "g", true},
//            {"Pasta", "3", "packs", true},
//            {"Tomatoes", "4", "pcs", false},
//            {"Cheese", "1", "block", true},
//    };
    // ------------------------------------------------------------------------------


    Shopping (JFrame parentFrame, Object[][] data, String pantryFilePath, String userID, String shoppingListFilePath, Pantry pantry) {
        this.pantry = pantry;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        JLabel shoppingListTitle = new JLabel("Shopping List");

        JLabel shoppingListText = new JLabel("A list of pantry items that are running low based on your current inventory levels.");
        shoppingListText.setFont(shoppingListTitle.getFont().deriveFont(Font.PLAIN, shoppingListTitle.getFont().getSize() - 2));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(shoppingListTitle);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        textPanel.setBackground(Color.WHITE);
        textPanel.add(shoppingListText);

        JPanel shoppingTable = new JPanel();
        shoppingTable.setLayout(new BoxLayout(shoppingTable, BoxLayout.X_AXIS));
        shoppingTable.setBackground(Color.WHITE);

        // ------------ TABLE CREATION (Start) ------------
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Boolean.class; // last column as checkbox
                return String.class;
            }
        };

        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // ------------ TABLE CREATION (End) ------------

        model.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 3) {
                Boolean purchased = (Boolean) model.getValueAt(row, column);

                String itemName = model.getValueAt(row, 0).toString();
                String quantity = model.getValueAt(row, 1).toString();
                String unit = model.getValueAt(row, 2).toString();

                try {
                    DashboardBackend.purchaseItem(pantryFilePath, userID, itemName, purchased, quantity, shoppingListFilePath);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                if (purchased) {
                    JOptionPane.showMessageDialog(parentFrame, itemName + " (" + quantity + " " + unit + ") added to pantry!");
                    model.setValueAt(false, row, column);
                }
//                else {
//                    JOptionPane.showMessageDialog(parentFrame, itemName + " (" + quantity + " " + unit + ") removed");
//                }
            }

//            this.pantry.getInventory().getModel().fireTableDataChanged();
            DefaultTableModel inventoryModel = this.pantry.getInventory().getModel();
            try {
                String[][] updatedPantry = DashboardBackend.readTableDataFromFile(pantryFilePath, userID);
                inventoryModel.setRowCount(0);
                for (String[] item : updatedPantry) {
                    inventoryModel.addRow(item);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });


        // ------------ TABLE STYLING (Start) ------------
        int visibleRows = 10;
        int rowHeight = table.getRowHeight();
        int headerHeight = table.getTableHeader().getPreferredSize().height;
        int height = visibleRows * rowHeight + headerHeight;

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, height));

        // Avoid overriding the last column styling
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount()-1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Color the column heads black
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        // ------------ TABLE STYLING (End) ------------

        JPanel buttonsPane = new JPanel();
        buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.Y_AXIS));
        buttonsPane.setBackground(Color.WHITE);

        addEntryToShoppingListBtn = new JButton("Add Entry");
        JButton removeEntryBtn = new JButton("Remove Entry");

        addEntryToShoppingListBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, addEntryToShoppingListBtn.getPreferredSize().height));
        removeEntryBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, removeEntryBtn.getPreferredSize().height));

        buttonsPane.add(addEntryToShoppingListBtn);
        buttonsPane.add(Box.createVerticalGlue());
        buttonsPane.add(removeEntryBtn);

        shoppingTable.add(scrollPane);
        shoppingTable.add(Box.createRigidArea(new Dimension(10, 0)));
        shoppingTable.add(buttonsPane);

        addEntryToShoppingListBtn.addActionListener(e -> {
            String[] entryDetails = AddEntryDialogBox.showFormDialog(parentFrame);

            if (entryDetails != null) {
                model.addRow(new Object[]{entryDetails[0], entryDetails[1], entryDetails[2], false});
            }
        });

        removeEntryBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.removeRow(row);
            }
        });

        // Full width, fixed height for titlePanel
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, titlePanel.getPreferredSize().height));
        // Full width, fixed height for textPanel
        textPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, textPanel.getPreferredSize().height));

        shoppingTable.setMaximumSize(shoppingTable.getPreferredSize());

        add(titlePanel);
        add(Box.createRigidArea(new Dimension(0, 3)));
        add(textPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(shoppingTable);
        add(Box.createVerticalGlue());

    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setPantry(Pantry pantry) {this.pantry = pantry;}
}
