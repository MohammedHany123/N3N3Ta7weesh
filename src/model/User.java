package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a user of the N3N3 Ta7weesh application.
 * <p>
 * Stores user credentials and all financial records.
 * </p>
 */
public class User implements Serializable {
    private final String email;
    private final String username;
    private final String password;
    private final String phone;
    private List<TrackingIncome> incomes = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();
    private List<Reminder> reminders = new ArrayList<>();
    private Map<String, Budget> budgets = new HashMap<>();
    private List<SpendingRecord> spendingRecords = new ArrayList<>();


    /**
     * Constructs a new User.
     *
     * @param email    the user's email
     * @param username the user's username
     * @param password the user's password
     * @param phone    the user's phone number
     */
    public User(String email, String username, String password, String phone) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    // Getters
    /**
     * Gets the user's email.
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Gets the user's username.
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Gets the user's password.
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Gets the user's phone number.
     * @return the phone number
     */
    public String getPhone() { return phone; }

    /**
     * Gets the user's income records.
     * @return the list of incomes
     */
    public List<TrackingIncome> getIncomes() { return incomes; }

    /**
     * Gets the user's expense records.
     * @return the list of expenses
     */
    public List<Expense> getExpenses() { return expenses; }

    /**
     * Gets the user's reminders.
     * @return the list of reminders
     */
    public List<Reminder> getReminders() { return reminders; }

    /**
     * Gets the user's budgets.
     * @return the map of budgets
     */
    public Map<String, Budget> getBudgets() { return budgets; }

    /**
     * Gets the user's spending records.
     * @return the list of spending records
     */
    public List<SpendingRecord> getSpendingRecords() { return spendingRecords; }

}