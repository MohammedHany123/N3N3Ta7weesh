
package model;

import java.io.*;
/**
 * Represents a record of spending for a specific category.
 */
public class SpendingRecord implements Serializable{
    private String category;
    private double amount;

    /**
     * Constructs a new SpendingRecord.
     *
     * @param category the spending category
     * @param amount   the amount spent
     */
    public SpendingRecord(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    /**
     * Gets the spending category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the amount spent.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
}


