<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Payments</title>
    <link rel="stylesheet" href="payments.css" />
</head>
<body>
<main class="content" id="Payments">
    <h1>Payments</h1>
    <p>Make and schedule payments.</p>
    <div class="payment-actions">
        <button class="open-popup" data-popup="deposit">Deposit Money</button>
        <button class="open-popup" data-popup="withdraw">Withdraw Money</button>
        <button class="open-popup" data-popup="transfer">Transfer Money</button>
        <button class="open-popup" data-popup="schedule">Schedule a Payment</button>
    </div>

    <!-- Deposit Popup -->
    <div class="popup deposit">
        <div class="close-btn">&times;</div>
        <div class="form">
            <h2>Deposit Money</h2>
            <div class="form-elements">
                <label for="deposit-amount">Enter Amount</label>
                <input type="text" id="deposit-amount" placeholder="Enter Amount" />
            </div>
            <div class="form-elements">
                <label for="deposit-accountNumber">Account Number</label>
                <input
                        type="text"
                        id="deposit-accountNumber"
                        placeholder="Enter Account Number"
                />
            </div>
            <div class="form-elements">
                <label for="deposit-password">Password</label>
                <input
                        type="password"
                        id="deposit-password"
                        placeholder="Enter the password"
                />
            </div>
            <div class="form-elements">
                <button onclick="sendPaymentData('deposit')">Deposit Money</button>
            </div>
        </div>
    </div>

    <!-- Withdraw Popup -->
    <div class="popup withdraw">
        <div class="close-btn">&times;</div>
        <div class="form">
            <h2>Withdraw Money</h2>
            <div class="form-elements">
                <label for="withdraw-amount">Enter Amount</label>
                <input type="text" id="withdraw-amount" placeholder="Enter Amount" />
            </div>
            <div class="form-elements">
                <label for="withdraw-accountNumber">Account Number</label>
                <input
                        type="text"
                        id="withdraw-accountNumber"
                        placeholder="Enter Account Number"
                />
            </div>
            <div class="form-elements">
                <label for="withdraw-password">Password</label>
                <input
                        type="password"
                        id="withdraw-password"
                        placeholder="Enter the password"
                />
            </div>
            <div class="form-elements">
                <button onclick="sendPaymentData('withdraw')">Withdraw Money</button>
            </div>
        </div>
    </div>

    <!-- Transfer Popup -->
    <div class="popup transfer">
        <div class="close-btn">&times;</div>
        <div class="form">
            <h2>Transfer Money</h2>
            <div class="form-elements">
                <label for="transfer-amount">Enter Amount</label>
                <input type="text" id="transfer-amount" placeholder="Enter Amount" />
            </div>
            <div class="form-elements">
                <label for="transfer-fromAccountNumber">From Account Number</label>
                <input
                        type="text"
                        id="transfer-fromAccountNumber"
                        placeholder="Enter Account Number"
                />
            </div>
            <div class="form-elements">
                <label for="transfer-toAccountNumber">To Account Number</label>
                <input
                        type="text"
                        id="transfer-toAccountNumber"
                        placeholder="Enter Account Number"
                />
            </div>
            <div class="form-elements">
                <label for="transfer-password">Password</label>
                <input
                        type="password"
                        id="transfer-password"
                        placeholder="Enter the password"
                />
            </div>
            <div class="form-elements">
                <button onclick="sendPaymentData('transfer')">Transfer Money</button>
            </div>
        </div>
    </div>

    <!-- Schedule Popup -->
    <div class="popup schedule">
        <div class="close-btn">&times;</div>
        <div class="form">
            <h2>Schedule Payment</h2>
            <div class="form-elements">
                <label for="schedule-amount">Enter Amount</label>
                <input type="text" id="schedule-amount" placeholder="Enter Amount" />
            </div>
            <div class="form-elements">
                <label for="schedule-accountNumber">Account Number</label>
                <input
                        type="text"
                        id="schedule-accountNumber"
                        placeholder="Enter Account Number"
                />
            </div>
            <div class="form-elements">
                <label for="schedule-password">Password</label>
                <input
                        type="password"
                        id="schedule-password"
                        placeholder="Enter the password"
                />
            </div>
            <div class="form-elements">
                <button onclick="sendPaymentData('schedule')">Schedule Payment</button>
            </div>
        </div>
    </div>
    <script src="payments.js"></script>
</main>
</body>
</html>
