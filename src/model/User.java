package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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



    public User(String email, String username, String password, String phone) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    // Getters
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public List<TrackingIncome> getIncomes() { return incomes; }
    public List<Expense> getExpenses() { return expenses; }
    public List<Reminder> getReminders() { return reminders; }
    public Map<String, Budget> getBudgets() { return budgets; }
    public List<SpendingRecord> getSpendingRecords() { return spendingRecords; }

}