package com.alina.budgetmanager.cli;

import com.alina.budgetmanager.db.DatabaseManager;
import com.alina.budgetmanager.domain.*;

import java.util.List;
import java.util.Scanner;

public class MenuHandler {
    private final Budget budget;
    private final Scanner scanner;
    public MenuHandler(){
    this.scanner = new Scanner(System.in);
    this.budget = new Budget();
    }
    public void run() {
        loadInitialTransaction();

        boolean running = true;
        while (running) {
            printMenu();
            System.out.println("Выберите опцию: ");

        }
        System.out.println("До свидания!");
        scanner.close();
    }
    private void loadInitialTransaction() {
        List<Transaction> pastTransactions = DatabaseManager.getAllTransactions();
        for (Transaction t : pastTransactions) {
            budget.addTransaction(t);
        }
        System.out.println("История транзакция загружена.");
    }

    private void handleAddTransaction(TransactionType type) {
        System.out.printf("Введите сумму (%s): ", type == TransactionType.INCOME ? "доход" : "расход");
        String input = scanner.nextLine();
        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                System.out.println("Сумма должна быть положительной.");
                return;
            }
            Transaction transaction = new Transaction(amount, type);
            budget.addTransaction(transaction);
            DatabaseManager.insertTransaction(transaction);
            System.out.println((type == TransactionType.INCOME ? "Доход" : "Расход") + "добавлен");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректное число.");
        }
    }
    private void showBalace(){
        System.out.printf("Текущий баланс: %2f\n", budget.getBalance());
    }

    private void handleReset() {
        System.out.println("Вы уверены, что хотите сбросить баланс и удалить все транзакции? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("y")) {
            budget.clear();
            // TO DO: DatabaseManager.deleteAllTransactions();
            System.out.println("Баланс и история транзакций сброшены.");
        } else {
            System.out.println("Сброс отменен.");
        }
    }
    private void showTransactionsHistory() {
      List<Transaction> transactions = budget.getTransactions();

      if (transactions.isEmpty()) {
          System.out.println("История операций пуста");
      } else {
          System.out.println("История операций:");
          transactions.forEach(System.out::println);
      }

   }

   private void handleFilter() {
       System.out.println("Введите 1 для доходов, 2 для рассходов");
       String choice = scanner.nextLine();

       TransactionType type;

       if (choice.equals("1")) {
           type = TransactionType.INCOME;
           System.out.println("Фильтр: доходы");
       } else if (choice.equals("2")) {
           type = TransactionType.EXPENSE;
           System.out.println("Фильтр: расходы");
       } else {
           System.out.println("Неверный выбор.");
           return;
       }

       budget.getTransactions().stream()
               .filter(t -> t.getType() == type)
               .forEach(System.out::println);

   }
    private void printMenu() {
        System.out.println("\n==== Budget Manager ====");
        System.out.println("1. Добавить доход");
        System.out.println("2. Добавить расход");
        System.out.println("3. Показать баланс");
        System.out.println("4. Сбросить баланс и историю");
        System.out.println("5. Показать историю операций");
        System.out.println("6. Фильтровать историю (доходы/расходы)");
        System.out.println("7. Сохранить историю в файл TXT");
        System.out.println("8. Сохранить историю в файл CSV");
        System.out.println("0. Выйти");
    }
}
