import model.User;
import model.TrackingIncome;
import repository.InMemoryUserRepository;
import repository.IncomeList;
import service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setup auth service and dependencies
        InMemoryUserRepository repository = new InMemoryUserRepository();
        AuthService authService = new AuthService(
            repository,
            new EmailValidation(),
            new UsernameValidation(),
            new PasswordValidation(),
            new PhoneValidation()
        );

        User currentUser = null;

        while (true) {
            System.out.println("\n=== Welcome to N3N3Ta7weesh ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();

                    User newUser = new User(email, username, password, phone);
                    if (authService.register(newUser)) {
                        String otp = authService.getCurrentOTP();
                        System.out.println("OTP sent: " + otp);
                        System.out.print("Enter OTP: ");
                        String inputOtp = scanner.nextLine();
                        if (authService.verifyOTP(inputOtp)) {
                            authService.completeRegistration(newUser);
                            System.out.println("Registration successful!");
                        } else {
                            System.out.println("Invalid OTP.");
                        }
                    } else {
                        System.out.println("Validation failed.");
                    }
                }
                case 2 -> {
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    currentUser = authService.login(email, password);
                    if (currentUser != null) {
                        System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
                        dashboard(scanner);
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                }
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void dashboard(Scanner scanner) {
    ExpenseTracker expenseTracker = new ExpenseTracker();
    IncomeList incomeList = new IncomeList();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    while (true) {
        System.out.println("\n=== Dashboard ===");
        System.out.println("1. Income Page");
        System.out.println("2. Expense Page");
        System.out.println("0. Logout");
        System.out.print("Choose: ");
        int mainChoice = Integer.parseInt(scanner.nextLine());

        switch (mainChoice) {
            case 1 -> {
                // Income Page
                while (true) {
                    System.out.println("\n--- Income Page ---");
                    System.out.println("1. Add Income");
                    System.out.println("2. View Income Summary");
                    System.out.println("0. Back");
                    System.out.print("Choose: ");
                    int incomeChoice = Integer.parseInt(scanner.nextLine());
                    switch (incomeChoice) {
                        case 1 -> {
                            try {
                                System.out.print("Enter income source: ");
                                String source = scanner.nextLine();

                                System.out.print("Enter income amount: ");
                                double amount = Double.parseDouble(scanner.nextLine());

                                System.out.print("Enter income date (YYYY-MM-DD): ");
                                LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

                                TrackingIncome income = new TrackingIncome(source, amount, date);

                                if (income.validateIncome()) {
                                    income.saveIncome(incomeList);
                                    System.out.println("Income saved successfully!");
                                } else {
                                    System.out.println("Invalid income data.");
                                }

                            } catch (DateTimeParseException | NumberFormatException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        case 2 -> {
                            incomeList.displayAllIncomes();
                            System.out.println("Total Income: " + incomeList.getTotalIncome() + " EGP");
                        }
                        case 0 -> {
                            break;
                        }
                        default -> System.out.println("Invalid option.");
                    }
                    if (incomeChoice == 0) break;
                }
            }
            case 2 -> {
                // Expense Page
                while (true) {
                    System.out.println("\n--- Expense Page ---");
                    System.out.println("1. Add Expense");
                    System.out.println("2. View Expense Summary");
                    System.out.println("3. Edit Expense");
                    System.out.println("4. Delete Expense");
                    System.out.println("5. Categorize Expenses");
                    System.out.println("0. Back");
                    System.out.print("Choose: ");
                    int expenseChoice = Integer.parseInt(scanner.nextLine());
                    switch (expenseChoice) {
                        case 1 -> {
                            expenseTracker.addEntry();
                        }
                        case 2 -> {
                            System.out.println("\nExpense History:");
                            expenseTracker.displayAll();
                            System.out.println("Total Expenses: " + expenseTracker.calculateTotal() + " EGP");
                        }
                        case 3 -> {
                            expenseTracker.displayAll();
                            System.out.print("Enter the expense number to edit: ");
                            int idx = Integer.parseInt(scanner.nextLine());
                            expenseTracker.updateEntry(idx);
                        }
                        case 4 -> {
                            expenseTracker.displayAll();
                            expenseTracker.deleteEntry();
                        }
                        case 5 -> {
                            expenseTracker.categorizeExpenses();
                        }
                        case 0 -> {
                            break;
                        }
                        default -> System.out.println("Invalid option.");
                    }
                    if (expenseChoice == 0) break;
                }
            }
            case 0 -> {
                System.out.println("Logged out.");
                return;
            }
            default -> System.out.println("Invalid option.");
        }
    }
}
}
