package ui;

import model.User;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;

/**
 * The main dashboard frame after user login.
 * <p>
 * Provides navigation to all main features.
 * </p>
 */
public class DashboardFrame extends JFrame {
    /**
     * Constructs the DashboardFrame.
     * @param user the current user
     * @param repository the user repository for saving data
     */
    public DashboardFrame(User user, FileUserRepository repository) {
        setTitle("Dashboard - " + user.getUsername());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);        // Light Aqua
        Color panelColor = new Color(0xFFFFFF);     // White
        Color buttonColor = new Color(0x2EC4B6);    // Teal
        Color buttonHover = new Color(0xFFBF69);    // Light Orange
        Color welcomeColor = new Color(0x2A4759);   // Orange
        Color textColor = Color.BLACK;

        JPanel panel = new JPanel(new GridLayout(6, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(panelColor);

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        welcomeLabel.setForeground(welcomeColor);

        JButton incomeBtn = new JButton("Income Page");
        JButton expenseBtn = new JButton("Expense Page");
        JButton reminderBtn = new JButton("Reminder Page");
        JButton budgetBtn = new JButton("Budget Page");
        JButton logoutBtn = new JButton("Logout");

        for (JButton btn : new JButton[]{incomeBtn, expenseBtn, reminderBtn, budgetBtn, logoutBtn}) {
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            btn.setFont(new Font("SansSerif", Font.BOLD, 14)); // Larger font for bigger button
            btn.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // More vertical padding
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


        panel.add(welcomeLabel);
        panel.add(incomeBtn);
        panel.add(expenseBtn);
        panel.add(reminderBtn);
        panel.add(budgetBtn);
        panel.add(logoutBtn);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);

        logoutBtn.addActionListener(e -> {
            new HomeFrame().setVisible(true);
            dispose();
        });

        incomeBtn.addActionListener(e -> {
            new IncomeFrame(user, repository).setVisible(true);
            dispose();
        });

        expenseBtn.addActionListener(e -> {
            new ExpenseFrame(user, repository).setVisible(true);
            dispose();
        });

        reminderBtn.addActionListener(e ->{
            new ReminderFrame(user, repository).setVisible(true);
            dispose();
        });

        budgetBtn.addActionListener(e -> {
            new BudgetFrame(user, repository).setVisible(true);
            dispose();
        });
    }
}
