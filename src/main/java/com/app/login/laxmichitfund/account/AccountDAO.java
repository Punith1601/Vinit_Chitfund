package com.app.login.laxmichitfund.account;

import com.app.login.laxmichitfund.connection.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO account(accountHolderName, phone, email, balance, password) VALUES(?,?,?,?,?)";
    private static final String SELECT_ACCOUNT_BY_ID_SQL = "SELECT * FROM ACCOUNT WHERE accountNumber = ?";
    private static final String SELECT_ACCOUNT_BY_PHONE_EMAIL_SQL = "SELECT * FROM ACCOUNT WHERE phone = ? AND email = ?";
    private static final String SELECT_ACCOUNT_BY_PHONE_SQL = "SELECT * FROM ACCOUNT WHERE phone = ?";
    private static final String SELECT_ACCOUNT_BY_EMAIL_SQL = "SELECT * FROM ACCOUNT WHERE email = ?";
    private static final String UPDATE_ACCOUNT_BALANCE_SQL = "UPDATE ACCOUNT SET balance = ? WHERE accountNumber = ?";
    private static final String UPDATE_ACCOUNT_SQL = "UPDATE ACCOUNT SET accountHolderName = ?, phone = ?, email = ?, password = ? WHERE accountNumber = ?";
    private static final String RESET_PIN_SQL = "UPDATE ACCOUNT SET password = ? WHERE accountNumber = ?";

    public Account createAccount(Account newAccount) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, newAccount.getAccountHolderName());
                preparedStatement.setLong(2, newAccount.getPhone());
                preparedStatement.setString(3, newAccount.getEmail());
                preparedStatement.setDouble(4, newAccount.getBalance());
                preparedStatement.setLong(5, newAccount.getPassword());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            newAccount.setAccountNumber(generatedKeys.getLong(1));
                        }
                    }
                    connection.commit(); // Commit transaction
                }
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction on failure
                e.printStackTrace();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newAccount;
    }

    public Account getAccountByAccID(long accountNumber) {
        return getAccount(SELECT_ACCOUNT_BY_ID_SQL, accountNumber);
    }

    public Account getAccountByPhoneEmail(long phone, String email) {
        return getAccount(SELECT_ACCOUNT_BY_PHONE_EMAIL_SQL, phone, email);
    }

    public Account getAccountByPhone(long phone) {
        return getAccount(SELECT_ACCOUNT_BY_PHONE_SQL, phone);
    }

    public Account getAccountByEmail(String email) {
        return getAccount(SELECT_ACCOUNT_BY_EMAIL_SQL, email);
    }

    private Account getAccount(String query, Object... params) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof Long) {
                    preparedStatement.setLong(i + 1, (Long) params[i]);
                } else if (params[i] instanceof String) {
                    preparedStatement.setString(i + 1, (String) params[i]);
                }
            }

            return extractAccount(preparedStatement.executeQuery());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Account extractAccount(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Account account = new Account();
            account.setAccountNumber(resultSet.getLong("accountNumber"));
            account.setAccountHolderName(resultSet.getString("accountHolderName"));
            account.setPhone(resultSet.getLong("phone"));
            account.setEmail(resultSet.getString("email"));
            account.setBalance(resultSet.getDouble("balance"));
            account.setPassword(resultSet.getLong("password"));
            return account;
        }
        return null;
    }

    public void updateAccountBalanceByAccID(Account account) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_BALANCE_SQL)) {
                preparedStatement.setDouble(1, account.getBalance());
                preparedStatement.setLong(2, account.getAccountNumber());
                preparedStatement.executeUpdate();
                connection.commit(); // Commit transaction
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction on failure
                e.printStackTrace();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Account updateAccount(Account account) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_SQL)) {
                preparedStatement.setString(1, account.getAccountHolderName());
                preparedStatement.setLong(2, account.getPhone());
                preparedStatement.setString(3, account.getEmail());
                preparedStatement.setLong(4, account.getPassword());
                preparedStatement.setLong(5, account.getAccountNumber());

                preparedStatement.executeUpdate();
                connection.commit(); // Commit transaction

                return getAccountByAccID(account.getAccountNumber());
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction on failure
                e.printStackTrace();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String resetPin(Account account) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement preparedStatement = connection.prepareStatement(RESET_PIN_SQL)) {
                preparedStatement.setLong(1, account.getPassword());
                preparedStatement.setLong(2, account.getAccountNumber());
                preparedStatement.executeUpdate();
                connection.commit(); // Commit transaction
                return "Password reset successful";

            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction on failure
                e.printStackTrace();
                return "Password reset failed";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Password reset failed";
        }
    }
}
