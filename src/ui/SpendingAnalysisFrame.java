package ui;

import model.User;
import model.SpendingRecord;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpendingAnalysisFrame extends JFrame {
    public SpendingAnalysisFrame(User user) {
        setTitle("Spending Analysis");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color welcomeColor = new Color(0x2A4759);

        // Calculate total spending per category
        Map<String, Double> totalSpending = new HashMap<>();
        for (SpendingRecord record : user.getSpendingRecords()) {
            totalSpending.put(record.getCategory(),
                totalSpending.getOrDefault(record.getCategory(), 0.0) + record.getAmount());
        }

        String[] columns = {"Category", "Total Spending (EGP)"};
        Object[][] data = new Object[totalSpending.size()][2];
        int i = 0;
        for (Map.Entry<String, Double> entry : totalSpending.entrySet()) {
            data[i][0] = entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
            data[i][1] = entry.getValue();
            i++;
        }

        JTable table = new JTable(data, columns);
        table.setBackground(bgColor);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setGridColor(new Color(0x2EC4B6));

        // Set table preferred height to fit all rows (plus header)
        int rowCount = table.getRowCount();
        int rowHeight = table.getRowHeight();
        int tableHeaderHeight = table.getTableHeader().getPreferredSize().height;
        int totalTableHeight = rowCount * rowHeight + tableHeaderHeight;
        table.setPreferredScrollableViewportSize(new Dimension(
            table.getPreferredScrollableViewportSize().width,
            totalTableHeight
        ));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(bgColor);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);

        JLabel titleLabel = new JLabel("Spending Analysis", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(welcomeColor);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);
    }
}