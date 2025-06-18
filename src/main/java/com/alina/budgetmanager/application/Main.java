package com.alina.budgetmanager.application;

import com.alina.budgetmanager.domain.Transaction;
import com.alina.budgetmanager.domain.TransactionType;
import com.alina.budgetmanager.domain.Budget;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Budget budget = new Budget();
        boolean running = true;
        double balance = 0.0;

//        List<Transaction> transactions = new ArrayList<>();

        while (running) {
            printMenu();
            System.out.println("Выберите опцию: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": //добавить доход
                    System.out.print("Введите сумму дохода: ");
                    String input = scanner.nextLine();
                    try {
                        double income = Double.parseDouble(input);
                        budget.addTransaction(new Transaction(income, TransactionType.INCOME));
                        System.out.println("Доход добавлен");
                    } catch (NumberFormatException e){
                        System.out.println("Ошибка: введите корректное число.\n");
                    }
                    break;
                case "2":
                    System.out.println("Введите сумму расхода: ");
                    String input2 = scanner.nextLine();
                    try {
                        double expense = Double.parseDouble(input2);
                        balance -= expense;
                        budget.addTransaction(new Transaction(expense,TransactionType.EXPENSE));
                        System.out.println("Расход добавлен");
                    } catch (NumberFormatException e){
                        System.out.println("Ошибка: введите корректное число.\n");
                    }
                    break;
                case "3":
                    System.out.println("Текущий баланс: " + balance);
                    break;
                case "4":
                    balance = 0.0;
                    System.out.println("Баланс сброшен. Текущий баланс: " + balance);
                    break;

                case "5":
                    if (budget.getTransactions().isEmpty()){
                        System.out.println("История операций пуста.");
                    } else {
                        System.out.println("История операций: ");
                        for (Transaction transaction : budget.getTransactions()) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case "6":
                    System.out.println("Введите 1 для показа только доходов, 2 - только расходов:");
                    String filterChoice = scanner.nextLine();

                    if (filterChoice.equals("1")) {
                        System.out.println("Доходы: ");
                        for (Transaction t : budget.getTransactions()) {
                            if (t.getType() == TransactionType.INCOME)
                                System.out.println(t);
                        }
                    } else if (filterChoice.equals("2")) {
                        System.out.println("Расходы: ");
                            for (Transaction t : budget.getTransactions()) {
                                if (t.getType() == TransactionType.EXPENSE) {
                                    System.out.println(t);
                                }
                            }
                    } else {
                        System.out.println("Неверный выбор. Возврат в главное меню.");
                    }
                    break;
                case "7":
                    System.out.println("Введите имя файла для сохранения (например, transaction history.txt): ");
                    String filename = scanner.nextLine();
                    saveTransactionsToTXT(budget.getTransactions(), filename);
                    break;
                case "8":
                    System.out.println("Введите имя файла для сохранения (например, transaction history.csv)");
                    String filenameCSV = scanner.nextLine();
                    saveTransactionToCSV(budget.getTransactions(), filenameCSV);
                    break;
                case "0":
                    running = false;
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте ещё раз.");
            }
        }

        scanner.close();
    }

    private static void saveTransactionsToTXT(List<Transaction> transactions, String filename){
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
    private static void saveTransactionToCSV(List<Transaction> transactions, String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){

            writer.write('\uFEFF');
            writer.write("Тип,Сумма,Дата и время");
            writer.newLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (Transaction t : transactions){
                String line = String.format("%s,%.2f,%s",
                        t.getType(), t.getAmount(), t.getDateTime().format(formatter));
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Ошибка при записи в CSV: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n==== Budget Manager ====");
        System.out.println("1. Добавить доход");
        System.out.println("2. Добавить расход");
        System.out.println("3. Показать баланс");
        System.out.println("4. Сбросить баланс");
        System.out.println("5. Показать историю операций");
        System.out.println("6. Фильтровать историю (доходы/расходы)");
        System.out.println("7. Сохранить историю в файл txt");
        System.out.println("8. Сохранить историю в файл csv");
        System.out.println("0. Выйти");
        System.out.print("Выберите действие: ");
    }
}
