import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReminderManager manager = new ReminderManager();

        while (true) {
            System.out.println("\n--- Reminder Menu ---");
            System.out.println("1. Set a new reminder");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            String option = sc.nextLine();

            if (option.equals("1")) {
                manager.createReminder(sc);
            } else if (option.equals("2")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
