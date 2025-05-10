package ui;

import model.User;
import model.Expense;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CategorizeExpenseFrame extends JFrame {
    public CategorizeExpenseFrame(User user) {
        setTitle("Categorize Expenses");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color welcomeColor = new Color(0x2A4759);

        // Categorize expenses
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense e : user.getExpenses()) {
            categoryTotals.put(e.getCategory(),
                categoryTotals.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }

        String[] columns = {"Category", "Total Amount (EGP)"};
        Object[][] data = new Object[categoryTotals.size()][2];
        int i = 0;
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue();
            i++;
        }

        JTable table = new JTable(data, columns);
        table.setBackground(bgColor);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setGridColor(new Color(0x2EC4B6));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(bgColor);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);

        JLabel titleLabel = new JLabel("Categorized Expenses", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(welcomeColor);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);
    }
}