package pantryAppPackage.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemPantry extends JPanel {

    final private JTextField Name, Category, Quantity, Unit, Threshold, ExpiryDate;
    final private JButton AddItemBtn;

    AddItemPantry() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(5, 2, 10, 5));
        form.setBackground(Color.WHITE);

        JLabel emptyBlockOne = new JLabel();
        JLabel sectionTitle = new JLabel("Add Item");
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
        Name.addActionListener(addItemInputFieldsHandler);
        Category.addActionListener(addItemInputFieldsHandler);
        Quantity.addActionListener(addItemInputFieldsHandler);
        Unit.addActionListener(addItemInputFieldsHandler);
        Threshold.addActionListener(addItemInputFieldsHandler);
        ExpiryDate.addActionListener(addItemInputFieldsHandler);


        int labelWidth = 75;
        int labelHeight = 25;

        NameLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        CategoryLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        QuantityLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        UnitLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        ThresholdLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        ExpiryDateLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));

        JPanel InputElementOne = new JPanel();
        InputElementOne.setLayout(new BoxLayout(InputElementOne, BoxLayout.X_AXIS));
        InputElementOne.setBackground(Color.WHITE);
        InputElementOne.add(NameLabel);
        InputElementOne.add(Name);

        JPanel InputElementTwo = new JPanel();
        InputElementTwo.setLayout(new BoxLayout(InputElementTwo, BoxLayout.X_AXIS));
        InputElementTwo.setBackground(Color.WHITE);
        InputElementTwo.add(CategoryLabel);
        InputElementTwo.add(Category);

        JPanel InputElementThree = new JPanel();
        InputElementThree.setLayout(new BoxLayout(InputElementThree, BoxLayout.X_AXIS));
        InputElementThree.setBackground(Color.WHITE);
        InputElementThree.add(QuantityLabel);
        InputElementThree.add(Quantity);

        JPanel InputElementFour = new JPanel();
        InputElementFour.setLayout(new BoxLayout(InputElementFour, BoxLayout.X_AXIS));
        InputElementFour.setBackground(Color.WHITE);
        InputElementFour.add(UnitLabel);
        InputElementFour.add(Unit);

        JPanel InputElementFive = new JPanel();
        InputElementFive.setLayout(new BoxLayout(InputElementFive, BoxLayout.X_AXIS));
        InputElementFive.setBackground(Color.WHITE);
        InputElementFive.add(ThresholdLabel);
        InputElementFive.add(Threshold);

        JPanel InputElementSix = new JPanel();
        InputElementSix.setLayout(new BoxLayout(InputElementSix, BoxLayout.X_AXIS));
        InputElementSix.setBackground(Color.WHITE);
        InputElementSix.add(ExpiryDateLabel);
        InputElementSix.add(ExpiryDate);

        // Row 1
        form.add(sectionTitle);
        form.add(emptyBlockOne);

        // Row 2
        form.add(InputElementOne);
        form.add(InputElementFour);

        // Row 3
        form.add(InputElementTwo);
        form.add(InputElementFive);

        // Row 4
        form.add(InputElementThree);
        form.add(InputElementSix);

        // Row 5
        form.add(AddItemBtn);

        add(form);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(AddItemBtn);
    }

    private class AddItemInputFieldsHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Name.getText().isEmpty() || Category.getText().isEmpty() || Quantity.getText().isEmpty() || Unit.getText().isEmpty() || Threshold.getText().isEmpty() || ExpiryDate.getText().isEmpty()) {
                AddItemBtn.setEnabled(false);
            } else {
                AddItemBtn.setEnabled(true);
            }
        }
    }

    public String getItemName() {
        return Name.getText();
    }

    public String getItemCategory() {
        return Category.getText();
    }

    public String getItemQuantity() {
        return Quantity.getText();
    }

    public String getItemUnit() {
        return Unit.getText();
    }

    public String getItemThreshold() {
        return Threshold.getText();
    }

    public String getItemExpiryDate() {
        return ExpiryDate.getText();
    }

    public JButton getAddItemBtn() {
        return AddItemBtn;
    }

    public void setItemName(String value) {
        Name.setText(value);
    }

    public void setItemCategory(String value) {
        Category.setText(value);
    }

    public void setItemQuantity(String value) {
        Quantity.setText(value);
    }

    public void setItemUnit(String value) {
        Unit.setText(value);
    }

    public void setItemThreshold(String value) {
        Threshold.setText(value);
    }

    public void setItemExpiryDate(String value) {
        ExpiryDate.setText(value);
    }

}
