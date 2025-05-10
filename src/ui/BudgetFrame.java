package ui;

import model.User;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;

public class BudgetFrame extends JFrame {
    public BudgetFrame(User user, FileUserRepository repository) {
        setTitle("Budget Page");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color buttonColor = new Color(0x2EC4B6);
        Color buttonHover = new Color(0xFFBF69);
        Color textColor = Color.BLACK;

        JButton setBudgetBtn = new JButton("Set Budget");
        JButton addSpendingBtn = new JButton("Add Spending");
        JButton displayBudgetsBtn = new JButton("Display Budgets");
        JButton spendingAnalysisBtn = new JButton("Generate Spending Analysis");
        JButton backBtn = new JButton("Back");

        for (JButton btn : new JButton[]{setBudgetBtn, addSpendingBtn, displayBudgetsBtn, spendingAnalysisBtn, backBtn}) {
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            btn.setFont(new Font("SansSerif", Font.BOLD, 16));
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

        JPanel panel = new JPanel(new GridLayout(5, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);

        panel.add(setBudgetBtn);
        panel.add(addSpendingBtn);
        panel.add(displayBudgetsBtn);
        panel.add(spendingAnalysisBtn);
        panel.add(backBtn);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);

        setBudgetBtn.addActionListener(e -> new SetBudgetFrame(user, repository).setVisible(true));
        addSpendingBtn.addActionListener(e -> new AddSpendingFrame(user, repository).setVisible(true));
        displayBudgetsBtn.addActionListener(e -> new DisplayBudgetsFrame(user).setVisible(true));
        spendingAnalysisBtn.addActionListener(e -> new SpendingAnalysisFrame(user).setVisible(true));
        backBtn.addActionListener(e -> {
            new DashboardFrame(user, repository).setVisible(true);
            dispose();
        });
    }
}