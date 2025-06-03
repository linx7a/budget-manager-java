import main.java.com.alina.domain.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private double amount;
    private TransactionType type;
    private LocalDateTime dateTime;

    public Transaction(double amount, TransactionType type){
        this.amount = amount;
        this.type = type;
        this.dateTime = LocalDateTime.now();
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
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return type + ": " + amount + " (" + dateTime.format(formatter) + ")";
    }
}
