package ui;

import model.User;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;

/**
 * The main expense management frame.
 * <p>
 * Allows the user to add, view, edit, delete, and categorize expenses.
 * </p>
 */
public class ExpenseFrame extends JFrame {
    /**
     * Constructs the ExpenseFrame.
     * @param user the current user
     * @param repository the user repository for saving data
     */
    public ExpenseFrame(User user, FileUserRepository repository) {
        setTitle("Expense Page");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color buttonColor = new Color(0x2EC4B6);
        Color buttonHover = new Color(0xFFBF69);
        Color textColor = Color.BLACK;

        JButton addBtn = new JButton("Add Expense");
        JButton viewBtn = new JButton("View Expense Summary");
        JButton editBtn = new JButton("Edit Expense");
        JButton deleteBtn = new JButton("Delete Expense");
        JButton categorizeBtn = new JButton("Categorize Expenses");
        JButton backBtn = new JButton("Back");

        for (JButton btn : new JButton[]{addBtn, viewBtn, editBtn, deleteBtn, categorizeBtn, backBtn}) {
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            btn.setFont(new Font("SansSerif", Font.BOLD, 14));
            btn.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
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

        JPanel panel = new JPanel(new GridLayout(6, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(categorizeBtn);
        panel.add(backBtn);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);

        addBtn.addActionListener(e -> new AddExpenseFrame(user, repository).setVisible(true));
        viewBtn.addActionListener(e -> new ExpenseSummaryFrame(user).setVisible(true));
        editBtn.addActionListener(e -> new EditExpenseFrame(user, repository).setVisible(true));
        deleteBtn.addActionListener(e -> new DeleteExpenseFrame(user, repository).setVisible(true));
        categorizeBtn.addActionListener(e -> new CategorizeExpenseFrame(user).setVisible(true));
        backBtn.addActionListener(e -> {
            new DashboardFrame(user, repository).setVisible(true);
            dispose();
        });
    }
}