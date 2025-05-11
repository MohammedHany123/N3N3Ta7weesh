package repository;

import model.Budget;
import model.SpendingRecord;
import java.util.*;

/**
 * Manages budgets and spending records for different categories.
 * <p>
 * Provides methods to set budgets, add spending, display budgets, and generate spending analysis.
 * </p>
 */
public class BudgetManager {
    private Map<String, Budget> budgets = new HashMap<>();
    private List<SpendingRecord> spendingRecords = new ArrayList<>();

    /**
     * Constructs a BudgetManager with the given budgets and spending records.
     *
     * @param budgets         the map of budgets by category
     * @param spendingRecords the list of spending records
     */
    public BudgetManager(Map<String, Budget> budgets, List<SpendingRecord> spendingRecords) {
        this.budgets = budgets;
        this.spendingRecords = spendingRecords;
    }

    /**
     * Sets a budget for a specific category.
     *
     * @param category the category name
     * @param amount   the budget amount
     */
    public void setBudget(String category, double amount) {
        Budget budget = new Budget(category, amount);
        budgets.put(category.toLowerCase(), budget);
        System.out.println(" Budget saved: " + budget);
    }

    /**
     * Adds a spending record for a specific category.
     *
     * @param category the category name
     * @param amount   the amount spent
     */
    public void addSpending(String category, double amount) {
        spendingRecords.add(new SpendingRecord(category.toLowerCase(), amount));
    }

    /**
     * Displays all budgets on the dashboard.
     */
    public void displayBudgets() {
        System.out.println("\n  Budgets on Dashboard:");
        for (Budget budget : budgets.values()) {
            System.out.println(" - " + budget);
        }
    }

    /**
     * Generates and displays a spending analysis by category for the current month.
     */
    public void generateSpendingAnalysis() {
        System.out.println("\n Spending Analysis:");
        Map<String, Double> totalSpending = new HashMap<>();

        for (SpendingRecord record : spendingRecords) {
            totalSpending.put(record.getCategory(),
                totalSpending.getOrDefault(record.getCategory(), 0.0) + record.getAmount());
        }

        for (Map.Entry<String, Double> entry : totalSpending.entrySet()) {
            System.out.printf("You have spent %.2fEGP on %s this month.%n", entry.getValue(), entry.getKey());
        }
    }
}