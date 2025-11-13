import java.awt.Color;
import java.awt.Insets;
import javax.swing.*;


public class SidebarButton extends JButton {
	
	public boolean selected = false;
	
	public SidebarButton(String ButtonLabel) {
		
		setText(ButtonLabel);
		
//		// Remove the default styling
		setFocusPainted(false);  
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		
//		// Add custom styling
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setMargin(new Insets(5, 0, 5, 0));
	}
	
	public void setSelectedStyle(boolean selected) {
        if (selected) {
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
        } else {
        	setBackground(new Color(75, 75, 75));     
            setForeground(new Color(200, 200, 200)); 
        }
    }
	
}
