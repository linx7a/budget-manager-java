# Budget Manager (Console App)

**Budget Manager** is a simple Java console application for managing your personal budget. It allows users to track income and expenses, view the current balance, filter transactions, and save the transaction history.

---

## Features

- 💰 Add income and expenses  
- 📊 View current balance  
- 🧾 Display transaction history  
- 🔍 Filter transactions by type (income/expense)  
- ♻️ Reset balance  
- 🚪 Exit the application  
- 📁 Save transaction history to `.txt` and `.csv` files  

---

## How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/linx7a/budget-manager-java.git
````

2. Navigate to the project directory:

   ```bash
   cd budget-manager-java
   ```

3. Compile the project (using IDE or command line):

   ```bash
   javac -d out src/main/java/com/alina/application/Main.java src/main/java/com/alina/domain/*.java
   ```

4. Run the application:

   ```bash
   java -cp out com.alina.application.Main
   ```

---

## Technologies Used

* Java 17+
* Console input/output
* Java standard libraries for file handling and date/time

---

## TODO

* 📥 Load data from file on startup
* 💱 Support for multiple currencies
* 🖥️ Build a GUI version
* 📅 Enhanced date and time management for transactions

---

## License

This project is open source and available under the MIT License.

---

✨ Created with ❤️ by linx7a
