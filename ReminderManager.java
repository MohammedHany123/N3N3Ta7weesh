import java.time.*;
import java.util.*;

public class ReminderManager {
    private List<Reminder> reminders = new ArrayList<>();

    public void createReminder(Scanner sc) {
        System.out.print("Enter reminder title (3-50 chars): ");
        String title = sc.nextLine().trim();
        if (!title.matches("[A-Za-z0-9 ]{3,50}")) {
            System.out.println("Invalid title. Must be 3-50 chars.");
            return;
        }

        System.out.print("Enter reminder date (YYYY-MM-DD): ");
        String dateInput = sc.nextLine().trim();
        LocalDate date;
        try {
            date = LocalDate.parse(dateInput);
            if (date.isBefore(LocalDate.now())&& !date.equals(LocalDate.now())) {
                System.out.println("Date must be in the future.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.print("Enter reminder time (HH:MM): ");
        String timeInput = sc.nextLine().trim();
        LocalTime time;
        try {
            time = LocalTime.parse(timeInput);
            if (time.isBefore(LocalTime.now())) {
                System.out.println("Time must be in the future.");
                return;
            }
        } catch (Exception e) {
            System.out.println(" Invalid time format.");
            return;
        }

        Reminder r = new Reminder(title, date, time);
        reminders.add(r);
        System.out.println(" Reminder saved: " + r);
        scheduleNotification(r);
    }

    private void scheduleNotification(Reminder r) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderTime = LocalDateTime.of(r.getDate(), r.getTime());
        long delay = Duration.between(now, reminderTime).toMillis();

        if (delay <= 0) {
            System.out.println(" Reminder time has already passed. Cannot schedule.");
            return;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("\n Reminder: " + r.getTitle() + " (Now: " + LocalDateTime.now() + ")");
                timer.cancel();
            }
        }, delay);

        System.out.println(" Notification scheduled in " + (delay / 1000) + " seconds.");
    }
}
