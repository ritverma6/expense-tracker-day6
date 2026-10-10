package com.expense;

import com.expense.model.Expense;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Expense> expenses = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("================================");
        System.out.println("     PERSONAL EXPENSE TRACKER");
        System.out.println("================================");
        while (running) {
            System.out.println("\n1. Add Expense\n2. View All Expenses\n3. Delete Expense\n4. Update Expense\n5. Exit");
            System.out.print("Choose an option: ");
            switch (scanner.nextLine()) {
                case "1": addExpense(scanner); break;
                case "2": viewExpenses(); break;
                case "3": deleteExpense(scanner); break;
                case "4": updateExpense(scanner); break;
                case "5": running = false; System.out.println("Thank you for using Expense Tracker!"); break;
                default: System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addExpense(Scanner scanner) {
        try {
            System.out.print("Enter expense amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) { System.out.println("Amount must be greater than zero."); return; }
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            Expense expense = new Expense(nextId++, amount, category, description, LocalDate.now());
            expenses.add(expense);
            System.out.println("Expense added successfully! ID: " + expense.getId());
        } catch (NumberFormatException e) { System.out.println("Invalid amount. Please enter a number."); }
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) { System.out.println("\nNo expenses found."); return; }
        System.out.println("\n========== ALL EXPENSES ==========");
        for (Expense expense : expenses) System.out.println(expense);
    }

    private static void deleteExpense(Scanner scanner) {
        if (expenses.isEmpty()) { System.out.println("No expenses available to delete."); return; }
        System.out.print("Enter the ID of the expense to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Iterator<Expense> iterator = expenses.iterator();
            while (iterator.hasNext()) {
                Expense expense = iterator.next();
                if (expense.getId() == id) { iterator.remove(); System.out.println("Expense ID " + id + " deleted successfully."); return; }
            }
            System.out.println("No expense found with ID " + id + ".");
        } catch (NumberFormatException e) { System.out.println("Invalid ID. Please enter a whole number."); }
    }

    private static void updateExpense(Scanner scanner) {
        if (expenses.isEmpty()) { System.out.println("No expenses available to update."); return; }
        System.out.print("Enter the ID of the expense to update: ");
        final int id;
        try { id = Integer.parseInt(scanner.nextLine()); }
        catch (NumberFormatException e) { System.out.println("Invalid ID. Please enter a whole number."); return; }

        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                try {
                    System.out.print("Enter new amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    if (amount <= 0) { System.out.println("Amount must be greater than zero."); return; }
                    System.out.print("Enter new category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String description = scanner.nextLine();
                    expense.setAmount(amount);
                    expense.setCategory(category);
                    expense.setDescription(description);
                    System.out.println("Expense ID " + id + " updated successfully.");
                    return;
                } catch (NumberFormatException e) { System.out.println("Invalid amount. Please enter a number."); return; }
            }
        }
        System.out.println("No expense found with ID " + id + ".");
    }
}
