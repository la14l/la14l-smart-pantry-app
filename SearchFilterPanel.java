import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;

import javax.swing.*;

public class SearchFilterPanel extends JPanel{
	public JTextField searchBar;
	public JCheckBox aboutToExpireItems, lowStockItems;
	
	private String[] categories = {"Name", "Category"};
	
	public SearchFilterPanel() {
		
		// Setting some base properties for the panel
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
		JComboBox searchFilter = new JComboBox(categories);
		searchFilter.setBackground(Color.WHITE);
//		JButton searchBtn = new JButton("Search");
		
		JLabel bottomSectionTitle = new JLabel("Filter by");
		
		aboutToExpireItems = new JCheckBox("About To Expire");
		aboutToExpireItems.addActionListener(e -> {
			if (aboutToExpireItems.isSelected()) {
				InventoryPanel.filterTableWithCheckBoxes("About To Expire", true);
		    } else {
		    	InventoryPanel.filterTableWithCheckBoxes("About To Expire", false);
		    }
		});
		
		lowStockItems = new JCheckBox("Low Stock Items");
		lowStockItems.addActionListener(e -> {
			if (lowStockItems.isSelected()) {
				InventoryPanel.filterTableWithCheckBoxes("Low Stock Items", true);
		    } else {
		    	InventoryPanel.filterTableWithCheckBoxes("Low Stock Items", false);
		    }
		});
		
		aboutToExpireItems.setBackground(Color.WHITE);
		lowStockItems.setBackground(Color.WHITE);
		
		// Adding the components to each section
		topSection.add(searchBar);
		topSection.add(Box.createRigidArea(new Dimension(5, 0)));
		topSection.add(searchFilter);
//		topSection.add(Box.createRigidArea(new Dimension(5, 0)));
//		topSection.add(searchBtn);

		
		bottomSection.add(bottomSectionTitle);
		bottomSection.add(Box.createHorizontalGlue());
		bottomSection.add(aboutToExpireItems);
		bottomSection.add(Box.createRigidArea(new Dimension(5, 0)));
		bottomSection.add(lowStockItems);
		
		add(topSection);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(bottomSection);
		
		
		searchBar.addActionListener(e -> {
			String filterCategory = (String) searchFilter.getSelectedItem();
			InventoryPanel.filterTableWithText(searchBar.getText(), filterCategory);
		});

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Inventory");
		frame.add(new SearchFilterPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
