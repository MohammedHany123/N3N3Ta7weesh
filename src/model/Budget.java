package model;

import java.io.*;

/**
 * Represents a budget for a specific category.
 * <p>
 * Each budget has a category name and a positive amount.
 * </p>
 */
public class Budget implements Serializable {
    private String category;
    private double amount;

    /**
     * Constructs a new Budget with the specified category and amount.
     *
     * @param category the category name for the budget
     * @param amount   the budget amount (must be positive)
     * @throws IllegalArgumentException if the category is invalid or amount is not positive
     */
    public Budget(String category, double amount) {
        if (!isValidCategory(category)) {
            throw new IllegalArgumentException("Invalid category name.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Budget amount must be positive.");
        }
        this.category = category;
        this.amount = amount;
    }

    /**
     * Validates the category name.
     *
     * @param category the category name to validate
     * @return true if the category is valid, false otherwise
     */
    private boolean isValidCategory(String category) {
        return category != null && category.matches("[a-zA-Z0-9 ]{3,50}");
    }

    /**
     * Gets the category name of this budget.
     *
     * @return the category name
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the budget amount.
     *
     * @return the budget amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns a string representation of the budget.
     *
     * @return a string describing the budget
     */
    @Override
    public String toString() {
        return "Category: " + category + ", Budget: " + String.format("%.2f", amount) + " EGP";
    }
}