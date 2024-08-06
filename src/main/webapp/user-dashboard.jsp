<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Laxmi Chit Fund</title>
        <link rel="stylesheet" href="css/user-dashboard.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
        />
    </head>
    <body>
        <nav>
            <div class="menu">
                <div class="logo">
                    <a href="#">Laxmi Chit Fund</a>
                </div>
                <ul>
                    <li><a href="logout">Log Out</a></li>
                </ul>
            </div>
        </nav>

        <div class="dashboard-container">
            <aside class="sidebar">
                <ul>
                    <li>
                        <a href="home.jsp" class="active"
                            ><i class="fas fa-home"></i> Home</a
                        >
                    </li>
                    <li>
                        <a href="accounts.jsp"
                            ><i class="fas fa-user"></i> Accounts</a
                        >
                    </li>
                    <li>
                        <a href="transactions.jsp"
                            ><i class="fas fa-exchange-alt"></i> Transactions</a
                        >
                    </li>
                    <li>
                        <a href="payments.jsp"
                            ><i class="fas fa-money-check-alt"></i> Payments</a
                        >
                    </li>
                    <li>
                        <a href="settings.jsp"
                            ><i class="fas fa-cogs"></i> Settings</a
                        >
                    </li>
                </ul>
            </aside>

            <iframe
                id="content-frame"
                src="accounts.jsp"
                width="100%"
                height="100%"
            ></iframe>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", () => {

                const navLinks = document.querySelectorAll(".sidebar ul a");

                function activateSection(link) {
                    const iframe = document.getElementById("content-frame");
                    const sectionId = link.getAttribute("href");
                    iframe.src = sectionId;

                    navLinks.forEach((link) => {
                        link.classList.remove("active");
                    });
                    link.classList.add("active");
                }

                navLinks.forEach((link) => {
                    link.addEventListener("click", (event) => {
                        event.preventDefault();
                        activateSection(link);
                    });
                });

                // Optionally, you can set the default active section if needed
                const activeLink = document.querySelector(
                    ".sidebar ul a.active"
                );
                if (activeLink) {
                    activateSection(activeLink);
                }
            });
        </script>
    </body>
</html>
