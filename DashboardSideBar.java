import javax.swing.*;
import java.awt.*;

public class DashboardSideBar extends JPanel {
	
	private SidebarButton[] menuButtons;
	private JLabel profile;
	private JLabel logo;
	
	DashboardSideBar (String username) {
		
		// Base properties (Layout and Background Color)
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);
        
        logo = new JLabel("Smart Pantry");
        logo.setHorizontalAlignment(SwingConstants.CENTER); 
        logo.setMaximumSize(new Dimension(Integer.MAX_VALUE, logo.getPreferredSize().height)); 
        add(logo);
        add(Box.createRigidArea(new Dimension(0, 20)));
        
		// Creating the menu buttons
		SidebarButton inventory = new SidebarButton("Inventory");
		SidebarButton shoppingList = new SidebarButton("Shopping List");
		
		// Adding the menu buttons
		menuButtons = new SidebarButton[2];
        menuButtons[0] = inventory;
        menuButtons[1] = shoppingList;
		
        // Styling the menu buttons
        for (SidebarButton btn : menuButtons) {
        	
        	// Enabling the buttons to take maximum width
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn.getPreferredSize().height));
            
            // Adding the toggle functionality
            btn.addActionListener(e -> selectButton(btn));
            
            // Adding the buttons to the sideBar component
            add(btn);
            if (!btn.equals(menuButtons[menuButtons.length - 1])) {
                add(Box.createRigidArea(new Dimension(0, 5)));
            }
            
        }
        
        // Selecting the inventory menu at start (default behavior)
        selectButton(inventory);
		
        // Creating the profile (icon and user name)
        profile = new JLabel(username);
		ImageIcon icon = new ImageIcon("src/user.png");
		Image image = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		profile.setIcon(new ImageIcon(image));
		profile.setIconTextGap(10); 
		profile.setHorizontalTextPosition(SwingConstants.RIGHT); 
		
		add(Box.createVerticalGlue());
		
		// Adding the profile component to the sideBar component
		add(profile);	
	}
	
    private void selectButton(SidebarButton selectedBtn) {
        for (SidebarButton btn : menuButtons) {
            btn.setSelectedStyle(btn == selectedBtn);
        }
    }
	
    // TEST - REMOVE LATER
	public static void main(String[] args) {
		JFrame frame = new JFrame("Dashboard");
		frame.add(new DashboardSideBar("Luffy"));
		frame.setSize(200, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

