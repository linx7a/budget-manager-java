package com.alina.budgetmanager.domain;

import java.util.ArrayList;
import java.util.List;

public class Budget {
    private final List<Transaction> transactions;

    public Budget() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public void clear() {
        transactions.clear();
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions); // защищаем оригинальный список
    }

    public double getBalance() {
        double balance = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.INCOME) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }
}
