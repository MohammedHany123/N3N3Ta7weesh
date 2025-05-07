import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IncomeList incomeList = new IncomeList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("=== Income Tracker ===");

        while (true) {
            System.out.print("\nEnter income source (or 'exit'): ");
            String source = scanner.nextLine();
            if (source.equalsIgnoreCase("exit")) break;

            System.out.print("Enter income amount: ");
            double amount;
            try {
                amount = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount format.");
                continue;
            }

            System.out.print("Enter income date (YYYY-MM-DD): ");
            LocalDate date;
            try {
                date = LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format.");
                continue;
            }

            TrackingIncome income = new TrackingIncome(source, amount, date);

            if (income.validateIncome()) {
                income.saveIncome(incomeList);
                System.out.println("Income saved!");
            } else {
                System.out.println("Validation failed. Please check the source, amount, or date.");
            }

            System.out.println("\nCurrent Income:");
            incomeList.displayAllIncomes();
            System.out.println("Total Income: " + incomeList.getTotalIncome() + " EGP");
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}
