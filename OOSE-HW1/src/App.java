import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static List<Accounts> ACCOUNT = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        initiateAccount();
        B1 : while (true) {
            System.out.println("ATM ComputerThanyaburi Bank");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("\namount of account must be number.");
                }
            }
            switch (choice) {
                case 1:
                    login();    
                    break;
                case 2:
                    break B1;
                default:
                    break;
            }
        }
        scanner.close();
    }

    private static void initiateAccount() {
        System.out.print("Step.1 Enter amount of all acount = ");
        int account_amount;
        while (true) {
            try {
                account_amount = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.print("amount of account must be number.");
                scanner.next();
            }
        }
        for (int i = 1; i <= account_amount; i++) {
            System.out.println("No." + i);
            ACCOUNT.add(createAccounts());
        }
    }

    private static Accounts createAccounts() {
        System.out.print("Account ID \t= ");
        String number = scanner.nextLine();
        System.out.print("Account Name \t= ");
        String name = scanner.nextLine();
        System.out.print("Password \t= ");
        String password = scanner.nextLine();
        System.out.print("Balance \t= ");
        double balance;
        while (true) {
            try {
                balance = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
            }
        }
        try {
            return new Accounts(name, number, password, balance);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR : " + e.getMessage());
            return createAccounts();
        }
    }

    private static void login() {
        while (true) {
            System.out.println("ATM ComputerThanyaburi Bank");
            System.out.println("Account ID : ");
            String number = scanner.nextLine();
            System.out.println("Account Password : ");
            String password = scanner.nextLine();
            for (Accounts account : ACCOUNT) {
                if (account.getNumber().equals(number) && account.isCorrectPassword(password)) {
                    System.out.println("Login successfully.");
                    accountInfo(account);
                    return;
                }
            }
            System.out.println("Invalid ID or Password. Please try again");
        }

    }

    private static void accountInfo(Accounts account) {
        A1 : while (true) {
            System.out.println("ATM ComputerThanyaburi Bank");
            System.out.println("Account ID : " + account.getNumber());
            System.out.println("Menu Service");
            System.out.println("1. Account Balance");
            System.out.println("2. Withdrawal");
            System.out.println("3. Exit");
            System.out.println("Choose : ");
            int choices = scanner.nextInt();
            switch (choices) {
                case 1:
                    System.out.println("Balance : " + account.getBalance() + " Baht");
                    break;
                case 2:
                    System.out.println("Please enter amount to withdrawal : ");
                    double amount;
                    while (true) {
                        try {
                            amount = scanner.nextDouble();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a number.");
                            scanner.next();
                        }
                    }
                    if (account.getBalance() - amount < 0) {
                        System.out.println("Insufficient Balance.");
                    } else {
                        account.setBalance(account.getBalance() - amount);
                    }
                    break;
                case 3:
                    break A1;
                default:
                    break;
            }
        }
    }
}