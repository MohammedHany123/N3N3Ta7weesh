package ui;

import model.User;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;

/**
 * The main income management frame.
 * <p>
 * Allows the user to add income and view income summary.
 * </p>
 */
public class IncomeFrame extends JFrame {
    /**
     * Constructs the IncomeFrame.
     * @param user the current user
     * @param repository the user repository for saving data
     */
    public IncomeFrame(User user, FileUserRepository repository) {
        setTitle("Income Page");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);        // Light Aqua
        Color buttonColor = new Color(0x2EC4B6);    // Teal
        Color buttonHover = new Color(0xFFBF69);    // Light Orange
        Color textColor = Color.BLACK;

        JButton addIncomeBtn = new JButton("Add Income");
        JButton viewSummaryBtn = new JButton("View Income Summary");
        JButton backBtn = new JButton("Back");

        for (JButton btn : new JButton[]{addIncomeBtn, viewSummaryBtn, backBtn}) {
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

        JPanel panel = new JPanel(new GridLayout(3, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);
        panel.add(addIncomeBtn);
        panel.add(viewSummaryBtn);
        panel.add(backBtn);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);

        addIncomeBtn.addActionListener(e -> {
            new AddIncomeFrame(user, repository).setVisible(true);
        });

        viewSummaryBtn.addActionListener(e -> {
            new IncomeSummaryFrame(user).setVisible(true);
        });

        backBtn.addActionListener(e -> {
            new DashboardFrame(user, repository).setVisible(true);
            dispose();
        });
    }
}