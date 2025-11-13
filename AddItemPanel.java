import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class AddItemPanel extends JPanel {
	
	public AddItemPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel form = new JPanel();
		form.setLayout(new GridLayout(5, 2, 10, 5));
		form.setBackground(Color.WHITE);

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(Color.WHITE);
		
		JLabel emptyBlockOne = new JLabel();
		JLabel emptyBlockTwo = new JLabel();
		JLabel sectionTitle = new JLabel("Add Item");
	
		JLabel ItemIDLabel = new JLabel("Item ID");
		JTextField ItemID = new JTextField("");
		
		JLabel NameLabel = new JLabel("Name");
		JTextField Name = new JTextField("");
		
		JLabel CategoryLabel = new JLabel("Category");
		JTextField Category = new JTextField("");
		
		JLabel QuantityLabel = new JLabel("Quantity");
		JTextField Quantity = new JTextField("");
		
		JLabel UnitLabel = new JLabel("Unit");
		JTextField Unit = new JTextField("");
		
		JLabel ThresholdLabel = new JLabel("Threshold");
		JTextField Threshold = new JTextField("");
		
		JLabel ExpiryDateLabel = new JLabel("Expiry Date");
		JTextField ExpiryDate = new JTextField("");
		
		JButton AddItemBtn = new JButton("Add Item");
		AddItemBtn.setFocusPainted(false);
		AddItemBtn.setContentAreaFilled(false);
		AddItemBtn.setOpaque(true);
		
		int labelWidth = 75;  // adjust this as you like
		int labelHeight = 25;

		ItemIDLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
		NameLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
		CategoryLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
		QuantityLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
		UnitLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
		ThresholdLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
		ExpiryDateLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
		
		JPanel InputElementOne = new JPanel();
		InputElementOne.setLayout(new BoxLayout(InputElementOne, BoxLayout.X_AXIS));
		InputElementOne.setBackground(Color.WHITE);
		InputElementOne.add(ItemIDLabel);
		InputElementOne.add(ItemID);
		
		JPanel InputElementTwo = new JPanel();
		InputElementTwo.setLayout(new BoxLayout(InputElementTwo, BoxLayout.X_AXIS));
		InputElementTwo.setBackground(Color.WHITE);
		InputElementTwo.add(NameLabel);
		InputElementTwo.add(Name);
		
		JPanel InputElementThree = new JPanel();
		InputElementThree.setLayout(new BoxLayout(InputElementThree, BoxLayout.X_AXIS));
		InputElementThree.setBackground(Color.WHITE);
		InputElementThree.add(CategoryLabel);
		InputElementThree.add(Category);
		
		JPanel InputElementFour = new JPanel();
		InputElementFour.setLayout(new BoxLayout(InputElementFour, BoxLayout.X_AXIS));
		InputElementFour.setBackground(Color.WHITE);
		InputElementFour.add(QuantityLabel);
		InputElementFour.add(Quantity);
		
		JPanel InputElementFive = new JPanel();
		InputElementFive.setLayout(new BoxLayout(InputElementFive, BoxLayout.X_AXIS));
		InputElementFive.setBackground(Color.WHITE);
		InputElementFive.add(UnitLabel);
		InputElementFive.add(Unit);
		
		JPanel InputElementSix = new JPanel();
		InputElementSix.setLayout(new BoxLayout(InputElementSix, BoxLayout.X_AXIS));
		InputElementSix.setBackground(Color.WHITE);
		InputElementSix.add(ThresholdLabel);
		InputElementSix.add(Threshold);
		
		JPanel InputElementSeven = new JPanel();
		InputElementSeven.setLayout(new BoxLayout(InputElementSeven, BoxLayout.X_AXIS));
		InputElementSeven.setBackground(Color.WHITE);
		InputElementSeven.add(ExpiryDateLabel);
		InputElementSeven.add(ExpiryDate);
		
		// Row 1
		form.add(sectionTitle);
		form.add(emptyBlockOne);

				
		// Row 2
		form.add(InputElementOne);
		form.add(InputElementFive);
				
		// Row 3
		form.add(InputElementTwo);
		form.add(InputElementSix);
		
		// Row 4
		form.add(InputElementThree);
		form.add(InputElementSeven);
		
		// Row 5
		form.add(InputElementFour);
		form.add(AddItemBtn);
		
		
		
		add(form);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(AddItemBtn);
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Add Item");
		frame.add(new AddItemPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
