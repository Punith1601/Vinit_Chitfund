<%--
  Created by IntelliJ IDEA.
  User: vinit
  Date: 29-07-2024
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="signup.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
</head>
<body>
<div class="content">
    <div class="text">
        Sign Up Form
    </div>
    <form id="signupForm" action="signup" method="post">
        <div class="input">
            <div class="field">
                <input type="text" required name="firstName">
                <span class="fas fa-user"></span>
                <label>First Name</label>
            </div>
            <div class="field">
                <input type="text" required name="lastName">
                <span class="fas fa-user"></span>
                <label>Last Name</label>
            </div>
            <div class="field">
                <input type="text" required name="email">
                <span class="fas fa-user"></span>
                <label>Email</label>
            </div>
            <div class="field">
                <input type="text" required name="phone">
                <span class="fas fa-user"></span>
                <label>Phone</label>
            </div>
            <div class="field">
                <input type="password" required name="password" id="password">
                <span class="fas fa-lock"></span>
                <label>Password</label>
            </div>
            <div class="field">
                <input type="password" required name="confirmPassword" id="confirmPassword">
                <span class="fas fa-lock"></span>
                <label>Confirm Password</label>
            </div>
        </div>
        <div>
            <div class="forgot-pass">
                <a href="#">Forgot Password?</a>
                <p id="error" class="error">Passwords do not match!</p>
            </div>

        </div>
        <button type="submit">Sign Up</button>
        <div class="login">
            Already a Member?
            <a href="login">Login now</a>
        </div>
    </form>
</div>

<script>
    document.getElementById('signupForm').addEventListener('submit', function(event) {
        var password = document.getElementById('password').value;
        var confirmPassword = document.getElementById('confirmPassword').value;
        var errorSpan = document.getElementById('error');

        if (password !== confirmPassword) {
            event.preventDefault(); // prevent form submission
            errorSpan.style.display = 'block'; // show error message
        } else {
            errorSpan.style.display = 'none'; // hide error message
        }
    });
</script>
</body>
</html>
