import model.User;
import model.TrackingIncome;
import repository.FileUserRepository;
import service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FileUserRepository repository = new FileUserRepository();
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
                            repository.addUser(newUser); // Save user to file
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
                    currentUser = repository.findByEmail(email);
                    if (currentUser != null && currentUser.getPassword().equals(password)) {
                        System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
                        dashboard(scanner, currentUser, repository);
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

    private static void dashboard(Scanner scanner, User currentUser, FileUserRepository repository) {
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
                                    currentUser.getIncomes().add(income);
                                    repository.saveUsers();
                                    System.out.println("Income saved successfully!");
                                } catch (DateTimeParseException | NumberFormatException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }
                            case 2 -> {
                                System.out.println("\nAll Incomes:");
                                double total = 0;
                                int i = 1;
                                for (TrackingIncome income : currentUser.getIncomes()) {
                                    System.out.println(i++ + ". " + income.getIncomeSource() + " | " + income.getIncomeAmount() + " | " + income.getIncomeDate());
                                    total += income.getIncomeAmount();
                                }
                                System.out.println("Total Income: " + total + " EGP");
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
                    ExpenseTracker expenseTracker = new ExpenseTracker();
                    expenseTracker.getEntries().addAll(currentUser.getExpenses());

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
                                currentUser.getExpenses().clear();
                                currentUser.getExpenses().addAll(expenseTracker.getEntries());
                                repository.saveUsers();
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
                                currentUser.getExpenses().clear();
                                currentUser.getExpenses().addAll(expenseTracker.getEntries());
                                repository.saveUsers();
                            }
                            case 4 -> {
                                expenseTracker.displayAll();
                                expenseTracker.deleteEntry();
                                currentUser.getExpenses().clear();
                                currentUser.getExpenses().addAll(expenseTracker.getEntries());
                                repository.saveUsers();
                            }
                            case 5 -> {
                                expenseTracker.categorizeExpenses();
                            }
                            case 0 -> {
                                currentUser.getExpenses().clear();
                                currentUser.getExpenses().addAll(expenseTracker.getEntries());
                                repository.saveUsers();
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