package ui;

import model.User;
import model.Reminder;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddReminderFrame extends JFrame {
    public AddReminderFrame(User user, FileUserRepository repository) {
        setTitle("Add Reminder");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color buttonColor = new Color(0x2EC4B6);
        Color buttonHover = new Color(0xFFBF69);
        Color textColor = Color.BLACK;

        JTextField descField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();

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

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(bgColor);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(descField);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Time (HH:MM):"));
        formPanel.add(timeField);

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
                String desc = descField.getText();
                LocalDate date = LocalDate.parse(dateField.getText());
                LocalTime time = LocalTime.parse(timeField.getText());
                user.getReminders().add(new Reminder(desc, date, time));
                repository.saveUsers();
                JOptionPane.showMessageDialog(this, "Reminder saved!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> dispose());
    }
}