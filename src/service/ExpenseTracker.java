package service;

import repository.Tracker;
import model.Expense;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Tracks and manages a list of expenses, supporting add, update, delete, display, and categorization.
 */
public class ExpenseTracker implements Tracker {
    private List<Expense> expensesList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Adds a new expense entry from user input.
     */
    @Override
    public void addEntry() {
        try {
            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            System.out.print("Enter payment method: ");
            String method = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

            Expense expense = new Expense(category, method, amount, date);
            expensesList.add(expense);

            System.out.println("Expense added.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Updates an existing expense entry at the specified index.
     * @param index the index of the expense to update
     */
    public void updateEntry(int index) {
        if (index < 0 || index >= expensesList.size()) {
            System.out.println("Invalid index.");
            return;
        }

        try {
            System.out.print("Enter new category: ");
            String category = scanner.nextLine();

            System.out.print("Enter new payment method: ");
            String method = scanner.nextLine();

            System.out.print("Enter new amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter new date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

            Expense updated = new Expense(category, method, amount, date);
            expensesList.set(index, updated);
            System.out.println("Expense updated.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Deletes an expense entry based on user input.
     */
    @Override
    public void deleteEntry() {
        System.out.print("Enter index to delete: ");
        int index = Integer.parseInt(scanner.nextLine());

        if (index >= 0 && index < expensesList.size()) {
            expensesList.remove(index);
            System.out.println("Expense deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Gets all expense entries.
     * @return the list of expenses
     */
    @Override
    public List<Expense> getEntries() {
        return expensesList;
    }

    /**
     * Calculates the total amount of all expenses.
     * @return the total expense amount
     */
    @Override
    public double calculateTotal() {
        return expensesList.stream().mapToDouble(Expense::getAmount).sum();
    }

    /**
     * Displays all expenses in the list.
     */
    public void displayAll() {
        for (int i = 0; i < expensesList.size(); i++) {
            System.out.print("[" + i + "] ");
            expensesList.get(i).display();
        }
    }

    /**
     * Categorizes and displays total expenses by category.
     */
    public void categorizeExpenses() {
        System.out.println("\nExpenses by Category:");
        expensesList.stream()
            .map(Expense::getCategory)
            .distinct()
            .forEach(cat -> {
                double total = expensesList.stream()
                        .filter(e -> e.getCategory().equals(cat))
                        .mapToDouble(Expense::getAmount)
                        .sum();
                System.out.println(cat + ": " + total + " EGP");
            });
    }
}
