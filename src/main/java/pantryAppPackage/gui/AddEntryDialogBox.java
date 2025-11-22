package pantryAppPackage.gui;

import javax.swing.*;
import java.awt.*;

public class AddEntryDialogBox {

    public static String[] showFormDialog(JFrame parent) {
        String[] data = new String[3];

        JDialog dialog = new JDialog(parent, "Add Item", true);
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField();
        JTextField qtyField = new JTextField();
        JTextField unitField = new JTextField();

        formPanel.add(new JLabel("Item Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(qtyField);
        formPanel.add(new JLabel("Unit:"));
        formPanel.add(unitField);

        dialog.add(formPanel);

        JPanel buttonPanel = new JPanel();
        JButton submitBtn = new JButton("Add");
        JButton cancelBtn = new JButton("Cancel");

        submitBtn.addActionListener(e -> {
            if (nameField.getText().isEmpty() | qtyField.getText().isEmpty() | unitField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields");
            } else {
                data[0] = nameField.getText();
                data[1] = qtyField.getText();
                data[2] = unitField.getText();
                dialog.dispose();
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(submitBtn);
        buttonPanel.add(cancelBtn);
        dialog.add(buttonPanel);

        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(parent); // center on parent
        dialog.setVisible(true);

        if (data[0] == null) return null;
        return(data);

    }
}
