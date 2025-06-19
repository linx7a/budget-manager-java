# 💰 Budget Manager (Java + PostgreSQL)

A simple console-based application to manage personal budget. Built using **Java**, **Maven**, and **PostgreSQL**.

---

## 🚀 Features

- Add income and expenses
- Calculate current balance
- Filter transactions by type (income/expense)
- Export history to `.txt` or `.csv`
- Persist data using PostgreSQL

---

## 🛠 Technologies Used

- Java 18
- Maven
- PostgreSQL
- JDBC (java.sql)
- IntelliJ IDEA

---
## ⚙️ How to Run

### 1. Clone the repo
```bash
git clone https://github.com/linx7a/budget-manager-java.git
cd budget-manager-java
```

### 2. Set up PostgreSQL
Create a new database:
```sql
CREATE DATABASE budget_db;
Create transactions table:
```
```sql
CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    amount DOUBLE PRECISION,
    type VARCHAR(20),
    date_time TIMESTAMP
);
```

### 3. Update database credentials in DatabaseManager.java:
```java
String url = "jdbc:postgresql://localhost:5432/budget_db";
String user = "your_username";
String password = "your_password";
```

### 4. Run the program
``` bash
mvn compile
mvn exec:java -Dexec.mainClass="com.alina.budgetmanager.application.Main"
```

## 📤 Export Formats

.csv — for Excel/Google Sheets
.txt — for plain text archives

## 💡 Planned Enhancements

Add categories to transactions
Monthly budget goals
GUI using JavaFX or Spring Boot web version
User authentication (optional)

## 📜 License
This project is open source and distributed under the MIT License.

## 🙌 Contributing
Contributions are welcome! Feel free to submit issues, fork the repository, or create pull requests to enhance Budget Manager. Check out the GitHub repository for more details.

✨ Created with ❤️ by linx7a
Happy budgeting!
