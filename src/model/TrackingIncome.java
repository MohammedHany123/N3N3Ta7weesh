package model;

import repository.IncomeList;
import java.time.LocalDate;

public class TrackingIncome{
    private static int idCounter = 1;

    private int incomeId;
    private String incomeSource;
    private double incomeAmount;
    private LocalDate incomeDate;

    public TrackingIncome(String incomeSource, double incomeAmount, LocalDate date) {
        this.incomeId = idCounter++;
        this.incomeSource = incomeSource;
        this.incomeAmount = incomeAmount;
        this.incomeDate = date;
    }

    private boolean validateSource(){
        return incomeSource != null &&
               incomeSource.length() >= 3 && 
               incomeSource.length() <= 50 &&
               incomeSource.matches("[a-zA-Z0-9]+");
    }
    
    private boolean validateAmount(){
        return incomeAmount > 0;
    }

    private boolean validateDate(){
        return incomeDate != null && !incomeDate.isAfter(LocalDate.now());
    }

    public boolean validateIncome() {
        return validateSource() && validateAmount() && validateDate();
    }

    public void displayIncome() {
        System.out.println("Income #" + incomeId + ": " + incomeSource + " - " + incomeAmount + " EGP on " + incomeDate);
    }
    
    public double getIncomeAmount() {
        return incomeAmount;
    }
    
    public void saveIncome(IncomeList incomeList){
        incomeList.addIncome(this);
    }
}