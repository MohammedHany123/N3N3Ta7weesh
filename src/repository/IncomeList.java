package repository;

import model.TrackingIncome;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of income entries.
 */
public class IncomeList {
    private List<TrackingIncome> incomeEntries = new ArrayList<>();

    /**
     * Adds an income entry to the list.
     * @param income the income entry to add
     */
    public void addIncome(TrackingIncome income) {
        incomeEntries.add(income);
    }

    /**
     * Displays all income entries.
     */
    public void displayAllIncomes() {
        for (TrackingIncome i : incomeEntries) {
            i.displayIncome();
        }
    }

    /**
     * Calculates the total income.
     * @return the total income amount
     */
    public double getTotalIncome() {
        return incomeEntries.stream().mapToDouble(TrackingIncome::getIncomeAmount).sum();
    }
}
