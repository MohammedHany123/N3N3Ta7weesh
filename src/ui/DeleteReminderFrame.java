package ui;

import model.User;
import model.Reminder;
import repository.FileUserRepository;
import javax.swing.*;
import java.awt.*;

/**
 * A frame for deleting a reminder.
 * <p>
 * Allows the user to select and delete a reminder.
 * </p>
 */
public class DeleteReminderFrame extends JFrame {
    /**
     * Constructs the DeleteReminderFrame.
     * @param user the current user
     * @param repository the user repository for saving data
     */
    public DeleteReminderFrame(User user, FileUserRepository repository) {
        setTitle("Delete Reminder");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color buttonColor = new Color(0x2EC4B6);
        Color buttonHover = new Color(0xFFBF69);
        Color textColor = Color.BLACK;

        DefaultListModel<Reminder> model = new DefaultListModel<>();
        for (Reminder r : user.getReminders()) model.addElement(r);

        JList<Reminder> reminderList = new JList<>(model);
        reminderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
            int idx = reminderList.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Select a reminder to delete.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                user.getReminders().remove(idx);
                model.remove(idx);
                repository.saveUsers();
                JOptionPane.showMessageDialog(this, "Reminder deleted!");
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
        mainPanel.add(new JScrollPane(reminderList), BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        getContentPane().setBackground(bgColor);
    }
}