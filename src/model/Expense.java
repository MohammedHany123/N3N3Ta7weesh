package model;

import java.io.*;
import java.time.LocalDate;

public class Expense implements Serializable {
    private String category;
    private String paymentMethod;
    private double amount;
    private LocalDate date;
    
    public Expense(String category, String paymentMethod, double amount, LocalDate date) {
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.date = date;
    }

    public double getAmount(){
        return amount;
    }

    public String getCategory(){
        return category;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }

    public LocalDate getDate(){
        return date;
    }

    public void display(){
        System.out.println(category + " - " + amount + " EGP via " + paymentMethod + " on " + date);
    }
}