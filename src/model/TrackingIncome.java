package model;

import java.io.*;
import repository.IncomeList;
import java.time.LocalDate;

/**
 * Represents a tracked income entry.
 */
public class TrackingIncome implements Serializable {
    private static int idCounter = 0;

    private int incomeId;
    private String incomeSource;
    private double incomeAmount;
    private LocalDate incomeDate;

     /**
     * Constructs a new TrackingIncome.
     *
     * @param incomeSource the source of the income
     * @param incomeAmount the amount of income
     * @param date         the date of the income
     */
    public TrackingIncome(String incomeSource, double incomeAmount, LocalDate date) {
        this.incomeId = idCounter++;
        this.incomeSource = incomeSource;
        this.incomeAmount = incomeAmount;
        this.incomeDate = date;
    }

    /**
     * Validates the income source.
     *
     * @return true if valid, false otherwise
     */
    private boolean validateSource(){
        return incomeSource != null &&
               incomeSource.length() >= 3 && 
               incomeSource.length() <= 50 &&
               incomeSource.matches("[a-zA-Z0-9]+");
    }
    
    /**
     * Validates the income amount.
     *
     * @return true if valid, false otherwise
     */
    private boolean validateAmount(){
        return incomeAmount > 0;
    }

    /**
     * Validates the income date.
     *
     * @return true if valid, false otherwise
     */    
    private boolean validateDate(){
        return incomeDate != null && !incomeDate.isAfter(LocalDate.now());
    }

    /**
     * Validates the entire income entry.
     *
     * @return true if all fields are valid, false otherwise
     */
    public boolean validateIncome() {
        return validateSource() && validateAmount() && validateDate();
    }

    /**
     * Displays the income details to the standard output.
     */
    public void displayIncome() {
        System.out.println("Income #" + incomeId + ": " + incomeSource + " - " + incomeAmount + " EGP on " + incomeDate);
    }
    
    /**
     * Gets the income amount.
     *
     * @return the income amount
     */
    public double getIncomeAmount() {
        return incomeAmount;
    }

    /**
     * Gets the income source.
     *
     * @return the income source
     */
    public String getIncomeSource() {
        return incomeSource;
    }
    
    /**
     * Gets the income date.
     *
     * @return the income date
     */
    public LocalDate getIncomeDate() {
        return incomeDate;
    }
    
    /**
     * Saves this income to the given IncomeList.
     *
     * @param incomeList the list to save to
     */
    public void saveIncome(IncomeList incomeList){
        incomeList.addIncome(this);
    }
}