package ui;

import model.User;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;

public class ReminderFrame extends JFrame {
    public ReminderFrame(User user, FileUserRepository repository) {
        setTitle("Reminder Page");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color buttonColor = new Color(0x2EC4B6);
        Color buttonHover = new Color(0xFFBF69);
        Color textColor = Color.BLACK;

        JButton addBtn = new JButton("Add Reminder");
        JButton viewBtn = new JButton("View Reminders");
        JButton deleteBtn = new JButton("Delete Reminder");
        JButton backBtn = new JButton("Back");

        for (JButton btn : new JButton[]{addBtn, viewBtn, deleteBtn, backBtn}) {
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

        JPanel panel = new JPanel(new GridLayout(4, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(deleteBtn);
        panel.add(backBtn);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);

        addBtn.addActionListener(e -> new AddReminderFrame(user, repository).setVisible(true));
        viewBtn.addActionListener(e -> new ViewRemindersFrame(user).setVisible(true));
        deleteBtn.addActionListener(e -> new DeleteReminderFrame(user, repository).setVisible(true));
        backBtn.addActionListener(e -> {
            new DashboardFrame(user, repository).setVisible(true);
            dispose();
        });
    }
}