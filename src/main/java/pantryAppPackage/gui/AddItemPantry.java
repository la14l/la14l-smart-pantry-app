package pantryAppPackage.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemPantry extends JPanel {

    private JTextField ItemID, Name, Category, Quantity, Unit, Threshold, ExpiryDate;
    private JButton AddItemBtn;

    AddItemPantry () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(5, 2, 10, 5));
        form.setBackground(Color.WHITE);

        JLabel emptyBlockOne = new JLabel();
        JLabel sectionTitle = new JLabel("Add Item");
        JLabel ItemIDLabel = new JLabel("Item ID");
        ItemID = new JTextField("");
        JLabel NameLabel = new JLabel("Name");
        Name = new JTextField("");
        JLabel CategoryLabel = new JLabel("Category");
        Category = new JTextField("");
        JLabel QuantityLabel = new JLabel("Quantity");
        Quantity = new JTextField("");
        JLabel UnitLabel = new JLabel("Unit");
        Unit = new JTextField("");
        JLabel ThresholdLabel = new JLabel("Threshold");
        Threshold = new JTextField("");
        JLabel ExpiryDateLabel = new JLabel("Expiry Date");
        ExpiryDate = new JTextField("");
        AddItemBtn = new JButton("Add Item");
        AddItemBtn.setEnabled(false);

        AddItemInputFieldsHandler addItemInputFieldsHandler = new AddItemInputFieldsHandler();
        ItemID.addActionListener(addItemInputFieldsHandler);
        Name.addActionListener(addItemInputFieldsHandler);
        Category.addActionListener(addItemInputFieldsHandler);
        Quantity.addActionListener(addItemInputFieldsHandler);
        Unit.addActionListener(addItemInputFieldsHandler);
        Threshold.addActionListener(addItemInputFieldsHandler);
        ExpiryDate.addActionListener(addItemInputFieldsHandler);

        AddItemButtonHandler addItemButtonHandler = new AddItemButtonHandler();
        AddItemBtn.addActionListener(addItemButtonHandler);

        int labelWidth = 75;
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

    private class AddItemInputFieldsHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)  {
            if (ItemID.getText().equals("") || Name.getText().equals("") || Category.getText().equals("") || Quantity.getText().equals("") || Unit.getText().equals("") || Threshold.getText().equals("") || ExpiryDate.getText().equals("")) {
                AddItemBtn.setEnabled(false);
            } else {
                AddItemBtn.setEnabled(true);
            }
        }
    }

    private class AddItemButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)  {
            if (ItemID.getText().equals("") || Name.getText().equals("") || Category.getText().equals("") || Quantity.getText().equals("") || Unit.getText().equals("") || Threshold.getText().equals("") || ExpiryDate.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You have left one or more text fields.");
            } else {
                // Add Item Output Point
            }
        }
    }

    // TEST - REMOVE LATER
    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Item");
        frame.add(new AddItemPantry());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
