package repository;

import java.util.List;

/**
 * Interface for tracking entries such as incomes or expenses.
 */
public interface Tracker {
    /**
     * Adds a new entry.
     */
    void addEntry();

    /**
     * Deletes an entry.
     */
    void deleteEntry();

    /**
     * Gets all entries.
     * @return a list of entries
     */
    List<?> getEntries();

    /**
     * Calculates the total of all entries.
     * @return the total value
     */
    double calculateTotal();
}