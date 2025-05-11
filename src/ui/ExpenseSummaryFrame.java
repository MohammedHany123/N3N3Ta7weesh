package ui;

import model.User;
import model.Expense;
import javax.swing.*;
import java.awt.*;

/**
 * A frame for displaying the expense summary.
 * <p>
 * Shows a table of all expenses and the total.
 * </p>
 */
public class ExpenseSummaryFrame extends JFrame {
    /**
     * Constructs the ExpenseSummaryFrame.
     * @param user the current user
     */
    public ExpenseSummaryFrame(User user) {
        setTitle("Expense Summary");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);
        Color welcomeColor = new Color(0x2A4759);

        String[] columns = {"#", "Category", "Payment Method", "Amount", "Date"};
        Object[][] data = new Object[user.getExpenses().size()][5];
        double total = 0;
        int i = 0;
        for (Expense e : user.getExpenses()) {
            data[i][0] = i + 1;
            data[i][1] = e.getCategory();
            data[i][2] = e.getPaymentMethod();
            data[i][3] = e.getAmount();
            data[i][4] = e.getDate();
            total += e.getAmount();
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

        JLabel titleLabel = new JLabel("Expense Summary", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(welcomeColor);

        JLabel totalLabel = new JLabel("Total Expenses: " + total + " EGP", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        totalLabel.setForeground(welcomeColor);
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(totalLabel, BorderLayout.SOUTH);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);
    }
}