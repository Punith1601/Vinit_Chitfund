package com.app.login.laxmichitfund.passbook;

import java.sql.Timestamp;

/**
 * @author: Vinit Kelginmane
 * @project: Bank System
 * @Date: 19-07-2024
 */
public class Transaction {
    private long fromAccountNumber;
    private long toAccountNumber;
    private String type; // "deposit", "withdrawal", "transfer-dr", "transfer-cr"
    private double amount;
    private Timestamp timestamp;
    private double balance; // Balance after the transaction

    // Default constructor
    public Transaction() {}

    // Constructor
    public Transaction(long fromAccountNumber, long toAccountNumber, double amount, String type, Timestamp timestamp, double balance) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.balance = balance;
    }

    // Getters and Setters
    public long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "fromAccountNumber=" + fromAccountNumber +
                ", toAccountNumber=" + toAccountNumber +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", balance=" + balance +
                '}';
    }
}
