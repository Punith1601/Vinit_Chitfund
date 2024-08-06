package com.app.login.laxmichitfund;

import com.app.login.laxmichitfund.account.Account;
import com.app.login.laxmichitfund.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Vinit Kelginmane
 * @project: LaxmiChitFund
 * @Date: 29-07-2024
 */


public class SignupServlet extends HttpServlet {

    private AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String userName = request.getParameter("firstName") + " " + request.getParameter("lastName");
        long phone = Long.parseLong(request.getParameter("phone"));
        String email = request.getParameter("email");
        long password = Long.parseLong(request.getParameter("password"));
        long confirmPassword = Long.parseLong(request.getParameter("confirmPassword"));



        if (password !=confirmPassword) {
            response.sendRedirect("signup.jsp?error=Passwords do not match");
            return;
        }
        String acc = accountService.createAccount(userName,phone,email,password);
        response.sendRedirect("login.jsp?success=true");



    }
}
