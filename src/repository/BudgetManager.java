package repository;

import model.Budget;
import model.SpendingRecord;
import java.util.*;
public class BudgetManager {
    private Map<String, Budget> budgets = new HashMap<>();
    private List<SpendingRecord> spendingRecords = new ArrayList<>();

    public BudgetManager(Map<String, Budget> budgets, List<SpendingRecord> spendingRecords) {
        this.budgets = budgets;
        this.spendingRecords = spendingRecords;
    }


    public void setBudget(String category, double amount) {
        Budget budget = new Budget(category, amount);
        budgets.put(category.toLowerCase(), budget);
        System.out.println(" Budget saved: " + budget);
    }

    public void addSpending(String category, double amount) {
        spendingRecords.add(new SpendingRecord(category.toLowerCase(), amount));
    }

    public void displayBudgets() {
        System.out.println("\n  Budgets on Dashboard:");
        for (Budget budget : budgets.values()) {
            System.out.println(" - " + budget);
        }
    }

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
