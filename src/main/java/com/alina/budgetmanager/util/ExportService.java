package com.alina.budgetmanager.util;

import com.alina.budgetmanager.domain.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportService {
    public static void saveTransactionToTxt(List<Transaction> transactions, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            if (transactions.isEmpty()) {
                System.out.println("История транзакций пуста.\n");
            } else {
                for (Transaction t : transactions) {
                    writer.write(t.toString());
                    writer.newLine();
                }
            }
            System.out.println("История операций сохранена в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
