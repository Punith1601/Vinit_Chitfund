package com.app.login.laxmichitfund;

import com.app.login.laxmichitfund.account.Account;
import com.app.login.laxmichitfund.passbook.Transaction;
import com.app.login.laxmichitfund.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * @author: Vinit Kelginmane
 * @project: LaxmiChitFund
 * @Date: 29-07-2024
 */
public class LoginServlet extends HttpServlet {
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumberParam = request.getParameter("accountNumber");
        String passwordParam = request.getParameter("password");

        if (accountNumberParam == null || passwordParam == null) {
            request.setAttribute("error", "missing_credentials");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        try {
            long accountNumber = Long.parseLong(accountNumberParam);
            long password = Long.parseLong(passwordParam);

            if (accountService.authenticate(accountNumber, password)) {
                Account account = accountService.getAccount(accountNumber);
                List<Transaction> transactions = accountService.getPassBook(accountNumber);

                // Store account and transactions in session
                HttpSession session = request.getSession(true);
                session.setAttribute("account", account);
                session.setAttribute("transactions", transactions);

                // Redirect to the user dashboard
                response.sendRedirect(request.getContextPath() + "/user-dashboard");
            } else {
                // Handle login failure
                request.setAttribute("error", "invalid_credentials");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "invalid_format");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
