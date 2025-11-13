import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class InventoryPanel extends JPanel {
	
	String[] columnNames = {"Item ID", "Name", "Category", "Quantity", "Unit", "Threshold", "Expiry Date"};

	String[][] data = {
	    {"ITM001", "Apples", "Fruit", "50", "kg", "10", "2025-11-20"},
	    {"ITM002", "Milk", "Dairy", "30", "liters", "5", "2025-11-10"},
	    {"ITM003", "Rice", "Grains", "100", "kg", "20", "2026-02-01"},
	    {"ITM003", "Rice", "Grains", "100", "kg", "20", "2026-02-01"},
	    {"ITM003", "Rice", "Grains", "100", "kg", "20", "2026-02-01"},
	    {"ITM003", "Rice", "Grains", "100", "kg", "20", "2026-02-01"}
	};
	
    private JTable table;
    private DefaultTableModel model;
	private static TableRowSorter<DefaultTableModel> sorter;
	
	private int editableRow = -1;
	
//	// We have utmost 3 filters at a time
//	static RowFilter[] tableFilters = new RowFilter[3];
//	static RowFilter combinedFilters;
	
	static List<RowFilter<DefaultTableModel, Object>> activeFilters = new ArrayList<>(
		    Arrays.asList(null, null, null)  // 0=text, 1=expiry, 2=low stock
	);

	
	public InventoryPanel() {
		
		// Setting the layout of the inventory panel
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(Color.WHITE);
		
		// ------------ TABLE CREATION ------------
		// Creating the table to display inventory using the default table model
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
        
		// ------------ TABLE STYLING ------------
	    // Show how many rows are visible at a time
	 		int visibleRows = 10;
	 		int rowHeight = table.getRowHeight();
	 		int headerHeight = table.getTableHeader().getPreferredSize().height;
	 		int height = visibleRows * rowHeight + headerHeight;
	 		
	 		// Using a scroll pane which allows vertical scrolling when the number of rows exceeds 10
	 		JScrollPane scrollPane = new JScrollPane(table);
	 		scrollPane.setPreferredSize(new Dimension(600, height));
	    
		// Center the contents of the cells
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

		
		// ------------ BUTTON PANE ------------
		JPanel buttonsPane = new JPanel();
		buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.Y_AXIS));
		buttonsPane.setBackground(Color.WHITE);

//		InventoryButton editItemBtn = new InventoryButton("Edit", "src/edit.png");
//		InventoryButton saveChangesBtn = new InventoryButton("Save Changes", "src/check.png");
//		InventoryButton deleteItemBtn = new InventoryButton("Delete", "src/trash.png");
		
		JButton editItemBtn = new JButton("Edit");
		JButton saveChangesBtn = new JButton("Save Changes");
		JButton deleteItemBtn = new JButton("Delete");
		
		JButton[] inventoryButtons = new JButton[3];
		inventoryButtons[0] = editItemBtn;
		inventoryButtons[1] = saveChangesBtn;
		inventoryButtons[2] = deleteItemBtn;
		
		for (JButton btn: inventoryButtons) {
			btn.setFocusPainted(false);  
			btn.setContentAreaFilled(false);
			btn.setOpaque(true);
		}

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
		
		// Adding the components to the inventory panel
		add(scrollPane);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(buttonsPane);
		
	}
	
	 static public void filterTableWithText(String text, String col) {
		 if (text.equals("")) {
//			 sorter.setRowFilter(null); 
			 activeFilters.set(0, null);
	     } else {
	         if (col.equals("Name")) {
//	        	 sorter.setRowFilter(RowFilter.regexFilter(text, 1));
	        	 activeFilters.set(0, RowFilter.regexFilter(text, 1));
	         }
	         else if (col.equals("Category")) {
//	        	 sorter.setRowFilter(RowFilter.regexFilter(text, 2));
	        	 activeFilters.set(0, RowFilter.regexFilter(text, 2));
	         }
	     }
		 
		 List<RowFilter<DefaultTableModel, Object>> nonNullFilters = new ArrayList<>();
		 for (RowFilter<DefaultTableModel, Object> f : activeFilters) {
		     if (f != null) nonNullFilters.add(f);
		 }
		 sorter.setRowFilter(nonNullFilters.isEmpty() ? null : RowFilter.andFilter(nonNullFilters));

	}
	 
	static public void filterTableWithCheckBoxes(String filter, boolean selected) {
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
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Inventory");
		frame.add(new InventoryPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}


