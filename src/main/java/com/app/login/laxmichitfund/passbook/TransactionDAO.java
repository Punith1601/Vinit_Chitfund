package com.app.login.laxmichitfund.passbook;

import com.app.login.laxmichitfund.connection.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: Vinit Kelginmane
 * @project: Bank System
 * @Date: 19-07-2024
 */
public class TransactionDAO {

    // Method to add a transaction to the database
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction(fromAccountNumber, toAccountNumber, type, amount, transactionDate, balance) VALUES(?,?,?,?,?,?)";
        try (Connection connection = DataBaseConnection.getConnection()) {
            // Start transaction
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, transaction.getFromAccountNumber());
                preparedStatement.setLong(2, transaction.getToAccountNumber());
                preparedStatement.setString(3, transaction.getType());
                preparedStatement.setDouble(4, transaction.getAmount());
                preparedStatement.setTimestamp(5, transaction.getTimestamp());
                preparedStatement.setDouble(6, transaction.getBalance());

                preparedStatement.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve transactions by account number
    public List<Transaction> getTransactionByAccNumber(long accountNumber) {
        List<Transaction> transactionList = new ArrayList<>();
        String sql = "SELECT * FROM banksystem.transaction \n" +
                "WHERE (fromAccountNumber = ? AND (type = 'transfer-dr' OR type = 'deposit' OR type = 'withdrawal'))\n" +
                "   OR (toAccountNumber = ? AND (type = 'transfer-cr' OR type = 'deposit' OR type = 'withdrawal'))\n" +
                "ORDER BY transactionDate DESC;\n";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, accountNumber);
            preparedStatement.setLong(2, accountNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setFromAccountNumber(resultSet.getLong("fromAccountNumber"));
                    transaction.setToAccountNumber(resultSet.getLong("toAccountNumber"));
                    transaction.setType(resultSet.getString("type"));
                    transaction.setAmount(resultSet.getDouble("amount"));
                    transaction.setTimestamp(resultSet.getTimestamp("transactionDate"));
                    transaction.setBalance(resultSet.getDouble("balance"));
                    transactionList.add(transaction);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transactionList;
    }
    public List<Transaction> getRecentTransactionByAccNumber(long account_number){

        List<Transaction> transactionList = new ArrayList<>();

        String sql = "SELECT * FROM TRANSACTION WHERE (fromAccountNumber = ? OR toAccountNumber = ?) ORDER BY transactionDate DESC LIMIT 5";

        try{
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, account_number);
            preparedStatement.setLong(2, account_number);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Transaction transaction = new Transaction();
                transaction.setFromAccountNumber(resultSet.getLong("fromAccountNumber"));
                transaction.setToAccountNumber(resultSet.getLong("toAccountNumber"));
                transaction.setType(resultSet.getString("type"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTimestamp(resultSet.getTimestamp("transactionDate"));
                transaction.setBalance(resultSet.getDouble("balance"));
                transactionList.add(transaction);
            }

            return transactionList;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

}
