//package com.app.login.laxmichitfund;
//
//
//import com.app.login.laxmichitfund.passbook.Transaction;
//import com.app.login.laxmichitfund.service.AccountService;
//
//import java.util.List;
//import java.util.Scanner;
//
///**
// * @author: Vinit Kelginmane
// * @project: Default (Template) Project
// * @Date: 19-07-2024
// */
//public class Bank {
//
//    private static AccountService accountService = new AccountService();
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//
//        while (true) {
//            System.out.println();
//            System.out.println("Welcome to people's money bank:");
//            System.out.println("================================");
//            System.out.println("1. Create Account");
//            System.out.println("2. Login to your account");
//            System.out.println("3. Recover Account");
//            System.out.println("4. Reset Pin");
//            System.out.println("5. Exit");
//            System.out.print("Enter your choice: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    createAccount();
//                    break;
//                case 2:
//                    loginMenu();
//                    break;
//                case 3:
//                    recoverAccount();
//                    break;
//                case 4:
//                    resetPin();
//                    break;
//                case 5:
//                    System.exit(0);
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
//
//    private static void createAccount() {
//        System.out.print("Enter account holder name: ");
//        String accountHolderName = scanner.nextLine();
//        System.out.print("Enter initial deposit: ");
//        double initialDeposit = scanner.nextDouble();
//        scanner.nextLine();
//        System.out.print("Enter phone number: ");
//        long phone = scanner.nextLong();
//        scanner.nextLine();
//        System.out.print("Enter your email: ");
//        String email = scanner.nextLine();
//        System.out.print("Enter password: ");
//        long password = scanner.nextLong();
//        System.out.print("Enter the confirm password: ");
//        long confirmPassword = scanner.nextLong();
//
//        if (password == confirmPassword) {
//            String res = accountService.createAccount(accountHolderName, phone, email, initialDeposit, password);
//            System.out.println(res);
//        } else {
//            System.out.println("Password does not match.");
//        }
//    }
//
//    private static void loginMenu() {
//        while (true) {
//            System.out.println("1. Deposit Amount");
//            System.out.println("2. Withdraw Amount");
//            System.out.println("3. Transfer Amount");
//            System.out.println("4. Check Balance");
//            System.out.println("5. View Passbook");
//            System.out.println("6. Account Details");
//            System.out.println("7. Account Update");
//            System.out.println("8. Exit");
//
//            System.out.print("Enter your choice: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    deposit();
//                    break;
//                case 2:
//                    withdraw();
//                    break;
//                case 3:
//                    transfer();
//                    break;
//                case 4:
//                    checkBalance();
//                    break;
//                case 5:
//                    viewPassbook();
//                    break;
//                case 6:
//                    accountDetails();
//                    break;
//                case 7:
//                    accountUpdate();
//                    break;
//                case 8:
//                    return; // Exit from the login menu loop
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
//
//    private static void accountDetails(){
//        System.out.print("Enter account number, phone number, or email to retrieve bank account details: ");
//        String input = scanner.nextLine();
//
//        long accountHolderNumber = 0;
//        long phone =0;
//
//        if(input.length() == 8 && !input.contains("@")){
//            accountHolderNumber= Long.parseLong(input);
//            System.out.println(accountService.getAccountByAccNumber(accountHolderNumber));
//
//        }
//
//        else if(!input.contains("@")){
//            phone = Long.parseLong(input);
//            System.out.println(accountService.getAccountByPhone(phone));
//        }
//        else {
//            System.out.println(accountService.getAccountByEmail(input));
//        }
//
//    }
//
//    private static void accountUpdate(){
//        System.out.print("Enter account holder number: ");
//        long accountHolderNumber = scanner.nextLong();
//        scanner.nextLine();
//        System.out.println("<=== Press Enter to skip option ===>");
//        System.out.print("Enter account holder name: ");
//        String accountHolderName = scanner.nextLine();
//        scanner.nextLine();
//        System.out.print("Enter phone number: ");
//        long phone = scanner.nextLong();
//        scanner.nextLine();
//        System.out.print("Enter your email: ");
//        String email = scanner.nextLine();
//        System.out.print("Enter the password: ");
//        long password = scanner.nextLong();
//        System.out.print("Enter the confirm password: ");
//        long confirmPassword = scanner.nextLong();
//
//        if(password == confirmPassword){
//            System.out.println("<=== Before ===> \n" +accountService.getAccountByAccNumber(accountHolderNumber));
//            System.out.println("<=== After ===> \n " +accountService.updateAccount(accountHolderNumber, accountHolderName, phone,email,password));
//
//        }
//        else {
//            System.out.println("Password not match.");
//        }
//
//
//    }
//
//    private static void deposit(){
//        System.out.print("Enter account number: ");
//        long accountNumber = scanner.nextLong();
//        System.out.print("Enter password: ");
//        long password = scanner.nextLong();
//        if (accountService.authenticate(accountNumber, password)) {
//            System.out.print("Enter deposit amount: ");
//            double amount = scanner.nextDouble();
//            System.out.println(accountService.deposit(accountNumber, amount));
//        } else {
//            System.out.println("Authentication failed.");
//        }
//    }
//
//    private static void withdraw(){
//        System.out.print("Enter account number: ");
//        long accountNumber = scanner.nextLong();
//        System.out.print("Enter password: ");
//        long password = scanner.nextLong();
//        if (accountService.authenticate(accountNumber, password)) {
//            System.out.print("Enter withdraw amount: ");
//            double amount = scanner.nextDouble();
//            System.out.println(accountService.withdraw(accountNumber, amount));
//        } else {
//            System.out.println("Authentication failed.");
//        }
//    }
//
//    private static void transfer(){
//        System.out.print("Enter from account number: ");
//        long fromAccountNumber = scanner.nextLong();
//        System.out.print("Enter password: ");
//        long password = scanner.nextLong();
//
//        if (accountService.authenticate(fromAccountNumber, password)) {
//            System.out.print("Enter to account number: ");
//            long toAccountNumber = scanner.nextLong();
//            System.out.print("Enter transfer amount: ");
//            double amount = scanner.nextDouble();
//            System.out.println(accountService.transfer(fromAccountNumber, toAccountNumber, amount));
//        } else {
//            System.out.println("Authentication failed.");
//        }
//    }
//
//    private static void checkBalance(){
//        System.out.print("Enter account number: ");
//        long accountNumber = scanner.nextLong();
//        System.out.print("Enter password: ");
//        long password = scanner.nextLong();
//
//        if (accountService.authenticate(accountNumber, password)) {
//            double balance = accountService.checkBalance(accountNumber);
//            System.out.println("Current balance: " + balance);
//        } else {
//            System.out.println("Authentication failed.");
//        }
//    }
//
//    private static void viewPassbook(){
//        System.out.print("Enter account number: ");
//        long accountNumber = scanner.nextLong();
//        System.out.print("Enter password: ");
//        long password = scanner.nextLong();
//
//        if (accountService.authenticate(accountNumber, password)) {
//            List<Transaction> transactionList = accountService.getPassBook(accountNumber);
//            for(Transaction transaction : transactionList){
//                System.out.println(transaction.toString());
//            }
//        } else {
//            System.out.println("Authentication failed.");
//        }
//    }
//
//    private static void recoverAccount(){
//        System.out.print("Enter phone number: ");
//        long phone = scanner.nextLong();
//        scanner.nextLine();
//        System.out.print("Enter email id: ");
//        String email = scanner.nextLine();
//
//        System.out.println(accountService.getRecAccount(phone, email));
//
//    }
//
//    private static void resetPin(){
//        System.out.print("Enter account number: ");
//        long accountNumber = scanner.nextLong();
//        System.out.print("Enter phone number: ");
//        long phone = scanner.nextLong();
//        scanner.nextLine();
//        System.out.print("Enter email id: ");
//        String email =scanner.nextLine();
//        System.out.print("Enter the password: ");
//        long password = scanner.nextLong();
//        System.out.print("Enter the confirm password: ");
//        long confirmPassword = scanner.nextLong();
//
//        if(password == confirmPassword){
//            System.out.println(accountService.resetPin(accountNumber,phone,email, password));
//        }
//        else {
//            System.out.println("Password not match.");
//        }
//    }
//}
