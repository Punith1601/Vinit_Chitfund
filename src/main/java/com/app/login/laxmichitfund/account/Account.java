package com.app.login.laxmichitfund.account;

/**
 * @author: Vinit Kelginmane
 * @project: Bank System
 * @Date: 19-07-2024
 */
public class Account {
    private long accountNumber;
    private String accountHolderName;
    private long phone;
    private String email;
    private double balance;
    private long password;

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public Account() {
    }

    public Account(String accountHolderName, long phone, String email,  long password) {
        this.accountHolderName = accountHolderName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Account(long accountNumber, String accountHolderName, long phone, String email, double balance, long password) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.phone = phone;
        this.email = email;
        this.balance = balance;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_number=" + accountNumber +
                ", account_holder_name='" + accountHolderName + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", password=" + password +
                '}';
    }
}
