package ui;

import model.User;
import model.Expense;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EditExpenseFrame extends JFrame {
    public EditExpenseFrame(User user, FileUserRepository repository) {
        setTitle("Edit Expense");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color buttonColor = new Color(0x2EC4B6);
        Color buttonHover = new Color(0xFFBF69);
        Color textColor = Color.BLACK;

        DefaultListModel<Expense> model = new DefaultListModel<>();
        for (Expense e : user.getExpenses()) model.addElement(e);

        JList<Expense> expenseList = new JList<>(model);
        expenseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton editBtn = new JButton("Edit Selected");
        JButton backBtn = new JButton("Back");

        for (JButton btn : new JButton[]{editBtn, backBtn}) {
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

        editBtn.addActionListener(e -> {
            int idx = expenseList.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Select an expense to edit.");
                return;
            }
            Expense oldExp = model.get(idx);

            JTextField categoryField = new JTextField(oldExp.getCategory());
            JTextField methodField = new JTextField(oldExp.getPaymentMethod());
            JTextField amountField = new JTextField(String.valueOf(oldExp.getAmount()));
            JTextField dateField = new JTextField(oldExp.getDate().toString());

            JPanel editPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            editPanel.add(new JLabel("Category:"));
            editPanel.add(categoryField);
            editPanel.add(new JLabel("Payment Method:"));
            editPanel.add(methodField);
            editPanel.add(new JLabel("Amount:"));
            editPanel.add(amountField);
            editPanel.add(new JLabel("Date (YYYY-MM-DD):"));
            editPanel.add(dateField);

            int result = JOptionPane.showConfirmDialog(this, editPanel, "Edit Expense", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String category = categoryField.getText();
                    String method = methodField.getText();
                    double amount = Double.parseDouble(amountField.getText());
                    LocalDate date = LocalDate.parse(dateField.getText());

                    Expense updated = new Expense(category, method, amount, date);
                    user.getExpenses().set(idx, updated); // Replace the old expense
                    model.set(idx, updated); // Update the list model
                    repository.saveUsers();
                    JOptionPane.showMessageDialog(this, "Expense updated!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
                }
            }
        });

        backBtn.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        btnPanel.setBackground(bgColor);
        btnPanel.add(editBtn);
        btnPanel.add(backBtn);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        mainPanel.setBackground(bgColor);
        mainPanel.add(new JScrollPane(expenseList), BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        getContentPane().setBackground(bgColor);
    }
}