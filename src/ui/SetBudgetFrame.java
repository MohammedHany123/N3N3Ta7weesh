package ui;

import model.User;
import model.Budget;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;

public class SetBudgetFrame extends JFrame {
    public SetBudgetFrame(User user, FileUserRepository repository) {
        setTitle("Set Budget");
        setSize(350, 225);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color buttonColor = new Color(0x2EC4B6);
        Color buttonHover = new Color(0xFFBF69);
        Color textColor = Color.BLACK;

        JTextField categoryField = new JTextField();
        JTextField amountField = new JTextField();

        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        for (JButton btn : new JButton[]{saveBtn, cancelBtn}) {
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            btn.setFont(new Font("SansSerif", Font.BOLD, 16));
            btn.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(buttonHover);
                    btn.setForeground(textColor);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(buttonColor);
                    btn.setForeground(textColor);
                }
            });
        }

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(bgColor);
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryField);
        formPanel.add(new JLabel("Budget Amount:"));
        formPanel.add(amountField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(bgColor);
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);

        saveBtn.addActionListener(e -> {
            try {
                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());
                user.getBudgets().put(category, new Budget(category, amount));
                repository.saveUsers();
                JOptionPane.showMessageDialog(this, "Budget saved!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> dispose());
    }
}