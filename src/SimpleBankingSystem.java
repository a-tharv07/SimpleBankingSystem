import java.io.*;
import java.util.Scanner;

class Account {
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    // Constructor
    public Account(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Deposit Method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw Method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Check Balance Method
    public double getBalance() {
        return balance;
    }

    // Save Account Details to File
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(accountNumber + ".txt"))) {
            writer.write("Account Holder: " + accountHolderName + "\n");
            writer.write("Account Number: " + accountNumber + "\n");
            writer.write("Balance: " + balance + "\n");
            System.out.println("Account details saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving account details: " + e.getMessage());
        }
    }
}

public class SimpleBankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Simple Banking System!");

        // Creating an account
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        Account account = new Account(name, accountNumber, initialBalance);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Save and Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 3:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 4:
                    account.saveToFile();
                    System.out.println("Thank you for using the banking system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
