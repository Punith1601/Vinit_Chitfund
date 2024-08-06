<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction</title>
    <link rel="stylesheet" href="user-dashboard.css">
</head>
<body>
    <main class="content" id="Transactions">
        <h1>Transactions</h1>
        <p>View and search your transaction history.</p>
        <div class="transactions-list">
            <table class="transaction-table">
                <thead>
                <tr>
                    <th>SL No</th>
                    <th>Date And Time</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Balance</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="transaction" items="${transactions}" varStatus="status">

                    <tr>
                        <td>${status.index + 1}</td> <!-- Displaying index starting from 1 -->
                        <td>${transaction.timestamp}</td>
                        <td>${transaction.fromAccountNumber}</td>
                        <td>${transaction.toAccountNumber}</td>
                        <td>${transaction.type}</td>
                        <td>${transaction.amount}</td>
                        <td>${transaction.balance}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>