import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Expense Tracker Menu ===");
            System.out.println("1. Add Expense");
            System.out.println("2. Delete Expense");
            System.out.println("3. Update Expense");
            System.out.println("4. View All Expenses");
            System.out.println("5. Categorize Expenses");
            System.out.println("6. Total Expenses");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> tracker.addEntry();
                case 2 -> tracker.deleteEntry();
                case 3 -> {
                    System.out.print("Enter index to update: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    tracker.updateEntry(index);
                }
                case 4 -> tracker.displayAll();
                case 5 -> tracker.categorizeExpenses();
                case 6 -> System.out.println("Total: " + tracker.calculateTotal() + " EGP");
                case 0 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
