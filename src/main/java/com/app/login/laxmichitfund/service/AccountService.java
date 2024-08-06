package com.app.login.laxmichitfund.service;

import com.app.login.laxmichitfund.account.Account;
import com.app.login.laxmichitfund.account.AccountDAO;
import com.app.login.laxmichitfund.passbook.Transaction;
import com.app.login.laxmichitfund.passbook.TransactionDAO;

import java.sql.Timestamp;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();
    private long encryptPassword(long password){
        return password;
    }

    public String createAccount(String accountHolderName, long phone, String email, long password) {

        Account newAccount = new Account(accountHolderName, phone, email, encryptPassword(password));
        Account account = accountDAO.createAccount(newAccount);
        return "Account successfully created. \n Your account details are: " + account.toString();
    }
    public Account getAccount(long accountnumber) {
        return accountDAO.getAccountByAccID(accountnumber);
    }

    public String getAccountByAccNumber(long accountNumber) {
        Account account = accountDAO.getAccountByAccID(accountNumber);
        if (account == null) {
            return "Account not found.";
        }
        List<Transaction> transactions = transactionDAO.getTransactionByAccNumber(accountNumber);
        return account.toString() + "\n" + transactions.toString();
    }

    public String getAccountByPhoneEmail(long phone, String email) {
        Account account = accountDAO.getAccountByPhoneEmail(phone, email);
        if (account == null) {
            return "Account not found.";
        }
        List<Transaction> transactions = transactionDAO.getTransactionByAccNumber(account.getAccountNumber());
        return account.toString() + "\n" + transactions.toString();
    }

    public String deposit(long accountNumber, double amount) {
        Account account = accountDAO.getAccountByAccID(accountNumber);
        if (account == null) {
            return "Account not found.";
        }
        account.setBalance(account.getBalance() + amount);
        accountDAO.updateAccountBalanceByAccID(account);

        Transaction transaction = new Transaction();
        transaction.setFromAccountNumber(accountNumber);
        transaction.setToAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setType("deposit");
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transaction.setBalance(account.getBalance());
        transactionDAO.addTransaction(transaction);

        return amount + " deposited to account number " + accountNumber;
    }

    public String withdraw(long accountNumber, double amount) {
        Account account = accountDAO.getAccountByAccID(accountNumber);
        if (account == null) {
            return "Account not found.";
        }
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountDAO.updateAccountBalanceByAccID(account);

            Transaction transaction = new Transaction();
            transaction.setFromAccountNumber(accountNumber);
            transaction.setToAccountNumber(accountNumber);
            transaction.setAmount(amount);
            transaction.setType("withdrawal");
            transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
            transaction.setBalance(account.getBalance());
            transactionDAO.addTransaction(transaction);

            return amount + " withdrawn from account number " + accountNumber;
        } else {
            return "Insufficient funds. Current balance is: " + account.getBalance();
        }
    }

    public String transfer(long fromAccountNumber, long toAccountNumber, double amount) {
        Account fromAccount = accountDAO.getAccountByAccID(fromAccountNumber);
        Account toAccount = accountDAO.getAccountByAccID(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            return "One or both accounts not found.";
        }
        if (fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            accountDAO.updateAccountBalanceByAccID(fromAccount);
            accountDAO.updateAccountBalanceByAccID(toAccount);

            Transaction fromTransaction = new Transaction();
            fromTransaction.setFromAccountNumber(fromAccountNumber);
            fromTransaction.setToAccountNumber(toAccountNumber);
            fromTransaction.setAmount(amount);
            fromTransaction.setType("transfer-dr");
            fromTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
            fromTransaction.setBalance(fromAccount.getBalance());
            transactionDAO.addTransaction(fromTransaction);

            Transaction toTransaction = new Transaction();
            toTransaction.setFromAccountNumber(fromAccountNumber);
            toTransaction.setToAccountNumber(toAccountNumber);
            toTransaction.setAmount(amount);
            toTransaction.setType("transfer-cr");
            toTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
            toTransaction.setBalance(toAccount.getBalance());
            transactionDAO.addTransaction(toTransaction);


            return amount + " transferred from account number " + fromAccountNumber + " to account number " + toAccountNumber;
        } else {
            return "Insufficient funds. Current balance is: " + fromAccount.getBalance();
        }
    }

    public double checkBalance(long accountNumber) {
        Account account = accountDAO.getAccountByAccID(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        return 0.0;
    }

    public List<Transaction> getPassBook(long accountNumber) {
        return transactionDAO.getTransactionByAccNumber(accountNumber);
    }
    public List<Transaction> recentTransaction(long account_holder_number){
        return transactionDAO.getRecentTransactionByAccNumber(account_holder_number);
    }

    public String updateAccount(long accountNumber, String accountHolderName, long phone, String email, long password) {
        Account account = accountDAO.getAccountByAccID(accountNumber);
        if (account == null) {
            return "Account not found.";
        }
        if (accountHolderName != null) account.setAccountHolderName(accountHolderName);
        if (phone != 0) account.setPhone(phone);
        if (email != null) account.setEmail(email);
        if (password != 0) account.setPassword(encryptPassword(password));

        Account updatedAccount = accountDAO.updateAccount(account);
        return updatedAccount.toString();
    }

    public boolean authenticate(long accountNumber, long password) {
        Account account = accountDAO.getAccountByAccID(accountNumber);
        return account != null && encryptPassword(password) == account.getPassword();
    }

    public String resetPin(long accountNumber, long phone, String email, long newPassword) {
        Account account = accountDAO.getAccountByAccID(accountNumber);
        if (account != null && account.getPhone() == phone && account.getEmail().equals(email)) {
            account.setPassword(encryptPassword(newPassword));
            return accountDAO.resetPin(account);
        } else {
            return "Account details do not match.";
        }
    }

    public String getAccountByPhone(long phone) {
        return accountDAO.getAccountByPhone(phone).toString();
    }

    public String getAccountByEmail(String email) {
        return accountDAO.getAccountByEmail(email).toString();
    }

    public String getRecAccount(long phone, String email) {
        Account accEmail = accountDAO.getAccountByEmail(email);
        Account accPhone = accountDAO.getAccountByPhone(phone);

        if(accPhone.equals(accEmail)){
            return accEmail.toString();
        }
        return "Account Not Found With Email: " + email +", Phone:" + phone;
    }
}
