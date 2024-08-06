<%--
  Created by IntelliJ IDEA.
  User: vinit
  Date: 29-07-2024
  Time: 21:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
</head>
<body>
<div class="content">
    <div class="text">
        Login Form
    </div>
    <form action="login" method="post">
        <div class="field">
            <input type="text" name="accountNumber" required>
            <span class="fas fa-user"></span>
            <label>Account Number</label>
        </div>
        <div class="field">
            <input type="password" name="password" required>
            <span class="fas fa-lock"></span>
            <label>Password</label>
        </div>
        <div class="forgot-pass">
            <a href="#">Forgot Password?</a>
            <% if (request.getAttribute("error") != null) { %>
            <p id="error" class="error">Invalid credentials!</p>
            <% } %>
        </div>
        <button type="submit" value="login">Sign in</button>
        <div class="sign-up">
            Not a member?
            <a href="signup">signup now</a>
        </div>
    </form>
</div>
</body>
</html>
