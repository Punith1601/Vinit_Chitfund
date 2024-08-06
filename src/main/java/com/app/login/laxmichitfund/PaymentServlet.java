package com.app.login.laxmichitfund;

/**
 * @author: Vinit Kelginmane
 * @project: LaxmiChitFund
 * @Date: 02-08-2024
 */


import com.app.login.laxmichitfund.account.Account;
import com.app.login.laxmichitfund.passbook.Transaction;
import com.app.login.laxmichitfund.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
        
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private AccountService accountService = new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String message;

        try {
            switch (action) {
                case "deposit":
                    message = handleDeposit(request);
                    break;
                case "withdraw":
                    message = handleWithdraw(request);
                    break;
                case "transfer":
                    message = handleTransfer(request);
                    break;
                case "schedule":
                    message = handleSchedule(request);
                    break;
                default:
                    message = "Invalid action!";
            }

            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("account") != null) {
                long accountNumber = ((Account) session.getAttribute("account")).getAccountNumber();
                Account updatedAccount = accountService.getAccount(accountNumber);
                session.setAttribute("account", updatedAccount);
                List<Transaction> transactions = accountService.getPassBook(accountNumber);
                session.setAttribute("transactions", transactions);
            }

            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print(message);

        } catch (Exception e) {
            e.printStackTrace();  // Print stack trace for debugging
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print("An error occurred: " + e.getMessage());
        }

        // Redirect should be carefully considered
        // response.sendRedirect(request.getContextPath() + "/user-dashboard");
    }


    private String handleDeposit(HttpServletRequest request) {
        double amount = Double.parseDouble(request.getParameter("amount"));
        long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
        long password = Long.parseLong(request.getParameter("password"));

        // Process deposit logic here
        if (accountService.authenticate(accountNumber, password)) {
            accountService.deposit(accountNumber, amount);
            return "Deposit of " + amount + " to account " + accountNumber + " successful.";
        } else {
            return "Invalid credentials.";
        }
    }

    private String handleWithdraw(HttpServletRequest request) {
        double amount = Double.parseDouble(request.getParameter("amount"));
        long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
        long password = Long.parseLong(request.getParameter("password"));

        // Process withdraw logic here
        if (accountService.authenticate(accountNumber, password)) {
            accountService.withdraw(accountNumber, amount);
            return "Withdrawal of " + amount + " from account " + accountNumber + " successful.";
        } else {
            return "Invalid credentials.";
        }
    }

    private String handleTransfer(HttpServletRequest request) {
        double amount = Double.parseDouble(request.getParameter("amount"));
        long fromAccountNumber = Long.parseLong(request.getParameter("fromAccountNumber"));
        long toAccountNumber = Long.parseLong(request.getParameter("toAccountNumber"));
        long password = Long.parseLong(request.getParameter("password"));

        // Process transfer logic here
        if (accountService.authenticate(fromAccountNumber, password)) {
            accountService.transfer(fromAccountNumber, toAccountNumber, amount);
            return "Transfer of " + amount + " from account " + fromAccountNumber + " to " + toAccountNumber + " successful.";
        } else {
            return "Invalid credentials.";
        }
    }

    private String handleSchedule(HttpServletRequest request) {
        double amount = Double.parseDouble(request.getParameter("amount"));
        long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
        long password = Long.parseLong(request.getParameter("password"));

        // Process schedule logic here
        if (accountService.authenticate(accountNumber, password)) {
            // Schedule payment logic here
            return "Scheduled payment of " + amount + " from account " + accountNumber + " successful.";
        } else {
            return "Invalid credentials.";
        }
    }
}
