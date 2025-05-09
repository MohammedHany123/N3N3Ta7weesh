package service;

import repository.Tracker;
import model.Expense;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseTracker implements Tracker {
    private List<Expense> expensesList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    @Override
    public List<Expense> getEntries() {
        return expensesList;
    }

    @Override
    public double calculateTotal() {
        return expensesList.stream().mapToDouble(Expense::getAmount).sum();
    }

    public void displayAll() {
        for (int i = 0; i < expensesList.size(); i++) {
            System.out.print("[" + i + "] ");
            expensesList.get(i).display();
        }
    }

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
