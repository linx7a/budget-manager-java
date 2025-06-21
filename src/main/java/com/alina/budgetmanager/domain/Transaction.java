package com.alina.budgetmanager.domain;

import java.time.LocalDateTime;

public class Transaction {
    private double amount;
    private TransactionType type;
    private LocalDateTime dateTime;

    public Transaction(double amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
        this.dateTime = LocalDateTime.now();
    }

    public Transaction(double amount, TransactionType type, LocalDateTime dateTime) {
        this.amount = amount;
        this.type = type;
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f (%s)", type, amount, dateTime);
    }
}
