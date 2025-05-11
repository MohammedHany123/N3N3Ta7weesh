package model;

import java.io.*;
import java.time.LocalDate;

/**
 * Represents an expense record.
 * <p>
 * Each expense has a category, payment method, amount, and date.
 * </p>
 */
public class Expense implements Serializable {
    private String category;
    private String paymentMethod;
    private double amount;
    private LocalDate date;

    /**
     * Constructs a new Expense with the specified details.
     *
     * @param category      the category of the expense
     * @param paymentMethod the payment method used
     * @param amount        the amount spent
     * @param date          the date of the expense
     */
    public Expense(String category, String paymentMethod, double amount, LocalDate date) {
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Gets the amount of the expense.
     *
     * @return the amount spent
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the category of the expense.
     *
     * @return the expense category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the payment method used for the expense.
     *
     * @return the payment method
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Gets the date of the expense.
     *
     * @return the date of the expense
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Displays the expense details to the standard output.
     */
    public void display() {
        System.out.println(category + " - " + amount + " EGP via " + paymentMethod + " on " + date);
    }

    /**
     * Returns a string representation of the expense.
     *
     * @return a string describing the expense
     */
    @Override
    public String toString() {
        return category + " - " + amount + " EGP via " + paymentMethod + " on " + date;
    }
}