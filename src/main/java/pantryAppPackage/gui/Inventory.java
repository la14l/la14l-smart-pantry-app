package pantryAppPackage.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pantryAppPackage.filters.LowStockFilter;
import pantryAppPackage.filters.ExpiryFilter;

public class Inventory extends JPanel {

    // SAMPLE DATA (LATER ON WE READ FROM FILES) (DELETE THIS LATER ON) -------------
    String[] columnNames = {"Item ID", "Name", "Category", "Quantity", "Unit", "Threshold", "Expiry Date"};

    String[][] data = {
            {"ITM001", "Apples", "Fruit", "50", "kg", "10", "2025-11-20"},
            {"ITM002", "Milk", "Dairy", "30", "liters", "5", "2025-11-10"},
            {"ITM003", "Rice", "Grains", "100", "kg", "20", "2026-02-01"},
            {"ITM003", "Rice", "Grains", "100", "kg", "20", "2026-02-01"},
            {"ITM003", "Rice", "Grains", "100", "kg", "20", "2026-02-01"},
            {"ITM003", "Rice", "Grains", "100", "kg", "20", "2026-02-01"}
    };
    // ------------------------------------------------------------------------------

    private JTable table;
    private DefaultTableModel model;
    private int editableRow = -1;

    // Filters
    private TableRowSorter<DefaultTableModel> sorter;
    List<RowFilter<DefaultTableModel, Object>> activeFilters = new ArrayList<>(
            Arrays.asList(null, null, null)  // 0=text, 1=expiry, 2=low stock
    );

    Inventory () {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // ------------ TABLE CREATION ------------
        // Data model -> JTable + Sorter
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int col) {
                // Only allow editing on the row currently being edited
                return row == editableRow;
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // ------------ TABLE STYLING (Start) ------------
        int visibleRows = 10;
        int rowHeight = table.getRowHeight();
        int headerHeight = table.getTableHeader().getPreferredSize().height;
        int height = visibleRows * rowHeight + headerHeight;

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, height));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Color the column heads black
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        // ------------ TABLE STYLING (End) ------------

        // ------------ BUTTON PANE (Start) ------------
        JPanel buttonsPane = new JPanel();
        buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.Y_AXIS));
        buttonsPane.setBackground(Color.WHITE);

        JButton editItemBtn = new JButton("Edit");
        JButton saveChangesBtn = new JButton("Save Changes");
        JButton deleteItemBtn = new JButton("Delete");

        editItemBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, editItemBtn.getPreferredSize().height));
        saveChangesBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, saveChangesBtn.getPreferredSize().height));
        deleteItemBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, deleteItemBtn.getPreferredSize().height));

        saveChangesBtn.setEnabled(false);

        buttonsPane.add(editItemBtn);
        buttonsPane.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonsPane.add(saveChangesBtn);
        buttonsPane.add(Box.createVerticalGlue());
        buttonsPane.add(deleteItemBtn);

        editItemBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                editableRow = row;
                table.repaint();
            }
            saveChangesBtn.setEnabled(true);
        });

        saveChangesBtn.addActionListener(e -> {
            editableRow = -1;
            table.repaint();
            saveChangesBtn.setEnabled(false);
        });

        deleteItemBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.removeRow(row);
            }

        });
        // ------------ BUTTON PANE (End) ------------

        add(scrollPane);
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(buttonsPane);
    }

    public void filterTableWithText(String text, String col) {
        if (text.isEmpty()) {
            activeFilters.set(0, null);
        } else {
            if (col.equals("Name")) {
                activeFilters.set(0, RowFilter.regexFilter(text, 1));
            }
            else if (col.equals("Category")) {
                activeFilters.set(0, RowFilter.regexFilter(text, 2));
            }
        }

        List<RowFilter<DefaultTableModel, Object>> nonNullFilters = new ArrayList<>();
        for (RowFilter<DefaultTableModel, Object> f : activeFilters) {
            if (f != null) nonNullFilters.add(f);
        }
        sorter.setRowFilter(nonNullFilters.isEmpty() ? null : RowFilter.andFilter(nonNullFilters));

    }

    public void filterTableWithCheckBoxes(String filter, boolean selected) {
        if (filter.equals("About To Expire")) {
            if (selected) {
                activeFilters.set(1, new ExpiryFilter());
            } else {
                activeFilters.set(1, null);
            }
        } else if (filter.equals("Low Stock Items")) {
            if (selected) {
                activeFilters.set(2, new LowStockFilter());
            } else {
                activeFilters.set(2, null);
            }
        }

        List<RowFilter<DefaultTableModel, Object>> nonNullFilters = new ArrayList<>();
        for (RowFilter<DefaultTableModel, Object> f : activeFilters) {
            if (f != null) nonNullFilters.add(f);
        }
        sorter.setRowFilter(nonNullFilters.isEmpty() ? null : RowFilter.andFilter(nonNullFilters));

    }

    // TEST - REMOVE LATER
    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory");
        frame.add(new Inventory());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
