import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String number)
            throws AccountNotFoundException {

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(number)) {
                return account;
            }
        }

        throw new AccountNotFoundException("Account not found.");
    }

    public void showAllAccounts() {
        System.out.println("\n========== ALL ACCOUNTS ==========");

        for (Account account : accounts) {
            System.out.printf(
                "Account: %s | Name: %s | Type: %s | Balance: $%.2f | Locked: %s%n",
                account.getAccountNumber(),
                account.getName(),
                account.getAccountType(),
                account.getBalance(),
                account.isLocked()
            );
        }
    }

    public void createAccount(Scanner sc) {
        System.out.print("Account number: ");
        String number = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("4-digit PIN: ");
        String pin = sc.nextLine();

        if (!pin.matches("\\d{4}")) {
            System.out.println("PIN must contain 4 digits.");
            return;
        }

        System.out.print("Initial balance: ");
        double balance = ATMSystemInput.readDouble(sc);

        System.out.println("1. Savings");
        System.out.println("2. Checking");
        System.out.print("Choose type: ");

        int type = ATMSystemInput.readInt(sc);

        if (type == 1) {
            accounts.add(
                new SavingsAccount(
                    number, pin, name, balance, 0.02
                )
            );
            System.out.println("Savings account created.");

        } else if (type == 2) {
            accounts.add(
                new CheckingAccount(
                    number, pin, name, balance, 1000
                )
            );
            System.out.println("Checking account created.");

        } else {
            System.out.println("Invalid account type.");
        }
    }
}
