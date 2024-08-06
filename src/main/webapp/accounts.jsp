<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/user-dashboard.css">
    <title>Accounts</title>
</head> 
<body>
    <main class="content" id="Accounts">
        <h1>Accounts</h1>
        <p>Manage your accounts and view balances.</p>
        <div class="account-details">
            <div class="account-card">
                <h2>User Details</h2>
                <p>User Name: <span>${account.accountHolderName}</span></p>
                <p>Account Number: <span>${account.accountNumber}</span></p>
                <p>Phone Number: <span>${account.phone}</span></p>
                <p>Email: <span>${account.email}</span></p>
                <p>Balance: <span>${account.balance}</span></p>

            </div>
            <div class="account-card">
                <h2>Checking Account</h2>
                <p>Balance: <span>${account.balance}</span></p>
            </div>
            <div class="account-card">
                <h2>Savings Account</h2>
                <p>You don't have a current account</p>
            </div>
            <div class="account-card">
                <h2>Current Account</h2>
                <p>You don't have a current account</p>
            </div>
        </div>
    </main>
</body>
</html>