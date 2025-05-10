package ui;

import model.User;
import model.TrackingIncome;

import javax.swing.*;
import java.awt.*;

public class IncomeSummaryFrame extends JFrame {
    public IncomeSummaryFrame(User user) {
        setTitle("Income Summary");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color welcomeColor = new Color(0x2A4759);

        // Table data
        String[] columns = {"#", "Source", "Amount", "Date"};
        Object[][] data = new Object[user.getIncomes().size()][4];
        int i = 0;
        for (TrackingIncome income : user.getIncomes()) {
            data[i][0] = i + 1;
            data[i][1] = income.getIncomeSource();
            data[i][2] = income.getIncomeAmount();
            data[i][3] = income.getIncomeDate();
            i++;
        }

        // Calculate total income
        double totalIncome = 0;
        for (TrackingIncome income : user.getIncomes()) {
            totalIncome += income.getIncomeAmount();
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
        scrollPane.setBackground(bgColor);


        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);

        JLabel titleLabel = new JLabel("Income Summary", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(welcomeColor);

        // Total income label
        JLabel totalLabel = new JLabel("Total Income: " + totalIncome + " EGP", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        totalLabel.setForeground(welcomeColor);
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panel.add(titleLabel, BorderLayout.NORTH);

        if (user.getIncomes().size() > 0) {
            panel.add(scrollPane, BorderLayout.CENTER);
        } else {
            JLabel emptyLabel = new JLabel("No income records found.", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
            emptyLabel.setForeground(welcomeColor);
            panel.add(emptyLabel, BorderLayout.CENTER);
        }

        panel.add(totalLabel, BorderLayout.SOUTH);

        setContentPane(panel);
        getContentPane().setBackground(bgColor); 
    }
}