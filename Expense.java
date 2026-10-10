package com.expense.model;

import java.time.LocalDate;

public class Expense {
    private int id;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;

    public Expense(int id, double amount, String category, String description, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Expense{id=" + id + ", amount=" + amount + ", category='" + category +
                "', description='" + description + "', date=" + date + "}";
    }
}
