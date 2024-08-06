<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="user-dashboard.css">
    <title>Home</title>
</head>
<body>
    <main class="content" id="Home">
        <h4 id="greeting"></h4>
        <h1 id="username"><span>${account.accountHolderName}</span></h1>
        <p>Overview of your financial status and quick actions.</p>

        <div class="overview">
            <div class="card">
                <h2>Account Details</h2>
                <p>Account Number: <span>${account.accountNumber}</span></p>
                <p>Phone Number: <span>${account.phone}</span></p>
                <p>Email: <span>${account.email}</span></p>
            </div>
            <div class="card">
                <h2>Balance</h2>
                <p>₹<span>${account.balance}</span></p>
            </div>
            <div class="card">
                <h2>Upcoming Payments</h2>
                <ul>
                    <li>₹<span>7500.00 - Credit Card Payment</span></li>
                    <li>₹<span>500.00 - Gym Membership</span></li>
                </ul>
            </div>
            <div class="card transaction">
                <h2>Recent Transactions</h2>
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
                        <!-- Check if the index is within the last 5 transactions -->
                        <c:if test="${status.index < 5}">
                            <tr>
                                <td>${status.index + 1}</td> <!-- Displaying index starting from 1 -->
                                <td>${transaction.timestamp}</td>
                                <td>${transaction.fromAccountNumber}</td>
                                <td>${transaction.toAccountNumber}</td>
                                <td>${transaction.type}</td>
                                <td>${transaction.amount}</td>
                                <td>${transaction.balance}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </main>

    <script>
        document.addEventListener("DOMContentLoaded", () =>{
            const greetingElement = document.getElementById("greeting");

            function getGreeting() {
                const now = new Date();
                const hours = now.getHours();
                let greeting;

                if (hours < 12) {
                    greeting = "Good Morning";
                } else if (hours < 18) {
                    greeting = "Good Afternoon";
                } else {
                    greeting = "Good Evening";
                }

                return greeting + " ,";
            }

            if (greetingElement) {
                greetingElement.textContent = getGreeting();
            }
        })
    </script>
</body>
</html>