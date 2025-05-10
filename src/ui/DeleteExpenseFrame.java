package ui;

import model.User;
import model.Expense;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;

public class DeleteExpenseFrame extends JFrame {
    public DeleteExpenseFrame(User user, FileUserRepository repository) {
        setTitle("Delete Expense");
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

        JButton deleteBtn = new JButton("Delete Selected");
        JButton backBtn = new JButton("Back");

        for (JButton btn : new JButton[]{deleteBtn, backBtn}) {
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

        deleteBtn.addActionListener(e -> {
            int idx = expenseList.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Select an expense to delete.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                user.getExpenses().remove(idx);
                model.remove(idx);
                repository.saveUsers();
                JOptionPane.showMessageDialog(this, "Expense deleted!");
            }
        });

        backBtn.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        btnPanel.setBackground(bgColor);
        btnPanel.add(deleteBtn);
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