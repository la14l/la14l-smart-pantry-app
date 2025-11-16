package pantryAppPackage.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Shopping extends JPanel {

    // SAMPLE DATA (LATER ON WE READ FROM FILES) (DELETE THIS LATER ON) -------------
    String[] columnNames = {"Item Name", "Quantity", "Unit", "Status"};

    Object[][] data = {
            {"Rice", "5", "kg", true},
            {"Milk", "2", "L", false},
            {"Eggs", "12", "pcs", true},
            {"Chicken Breast", "1", "kg", false},
            {"Butter", "0", "g", true},
            {"Pasta", "3", "packs", true},
            {"Tomatoes", "4", "pcs", false},
            {"Cheese", "1", "block", true},
    };
    // ------------------------------------------------------------------------------


    Shopping (JFrame parentFrame) {
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
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Boolean.class; // last column as checkbox
                return String.class;
            }
        };

        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // ------------ TABLE CREATION (End) ------------

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

        JButton addEntryBtn = new JButton("Add Entry");
        JButton removeEntryBtn = new JButton("Remove Entry");

        addEntryBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, addEntryBtn.getPreferredSize().height));
        removeEntryBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, removeEntryBtn.getPreferredSize().height));

        buttonsPane.add(addEntryBtn);
        buttonsPane.add(Box.createVerticalGlue());
        buttonsPane.add(removeEntryBtn);

        shoppingTable.add(scrollPane);
        shoppingTable.add(Box.createRigidArea(new Dimension(10, 0)));
        shoppingTable.add(buttonsPane);

        addEntryBtn.addActionListener(e -> {
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

    // TEST - REMOVE LATER
    public static void main(String[] args) {
        JFrame frame = new JFrame("Shopping");
        frame.add(new Shopping(frame));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
