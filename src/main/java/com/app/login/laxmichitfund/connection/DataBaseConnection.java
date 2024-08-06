package com.app.login.laxmichitfund.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: Vinit Kelginmane
 * @project: Bank System
 * @Date: 19-07-2024
 */
public class DataBaseConnection {
    private static String URL = "jdbc:mysql://localhost:3306/BankSystem";
    private static String USER = "root";
    private static String PASSWORD = "zxcvbnm,./";



    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
