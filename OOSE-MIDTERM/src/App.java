import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Manager manager;
    private static double btc_rate;
    private static List<Account> ACCOUNT = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        setupManager();
        ATM: while (true) {
            System.out.println("\nATM ComputerThanyaburi Bank");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose : ");
            int choice;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("\namount of account must be number.");
                    scanner.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    break ATM;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
        scanner.close();
    }

    private static void setupExchangeRate() {
        while (true) {
            System.out.print("Please enter 1 BTC rate\t: ");
            try {
                btc_rate = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please input number");
            }
        }
    }

    private static void setupManager() {
        while (true) {
            System.out.print("Manager ID\t: ");
            String id = scanner.nextLine();
            System.out.print("Manager name\t: ");
            String name = scanner.nextLine();
            System.out.print("Gender (M/F)\t: ");
            String gender = scanner.nextLine();
            System.out.print("Password\t: ");
            String password = scanner.nextLine();
            try {
                manager = new Manager(id, name, gender, password);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR : " + e.getMessage());
            }
        }
    }

    private static void login() {
        while (true) {
            System.out.println("\nATM ComputerThanyaburi Bank");
            System.out.print("Account ID\t : ");
            String number = scanner.nextLine();
            System.out.print("Account Password : ");
            String password = scanner.nextLine();
            if (manager.getId().equals(number) && manager.isCorrectPassword(password)) {
                System.out.println("Login successfully.");
                managerMenu();
                return;
            }
            for (Account account : ACCOUNT) {
                if (account.getId().equals(number) && account.isCorrectPassword(password)) {
                    System.out.println("Login successfully.");
                    accountInfo(account);
                    return;
                }
            }
            System.out.println("Invalid ID or Password. Please try again");
        }
    }

    private static void managerMenu() {
        System.out.println("=".repeat(30));
        MNG: while (true) {
            System.out.println("Manager Menu");
            System.out.println("1. Add Accounts");
            System.out.println("2. View Accounts");
            System.out.println("3. Exchange Rate");
            System.out.println("4. Logout");
            System.out.print("Choose : ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        addAccount();
                        break;
                    case 2:
                        for (Account acc : ACCOUNT) {
                            System.out.printf("ID : %s\t Name : %s\t Gender : %s\t Balance : %.2f THB, %.5f BTC%n",
                                    acc.getId(), acc.getName(), acc.getGender(), acc.getBalance(),
                                    acc.getBalance() / btc_rate);
                        }
                        break;
                    case 3:
                        setupExchangeRate();
                        break;
                    case 4:
                        System.out.println("=".repeat(30));
                        break MNG;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input number");
            }
        }
    }

    private static void addAccount() {
        System.out.print("Step.1 Enter amount of all acount = ");
        int account_amount;
        while (true) {
            try {
                account_amount = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.print("amount of account must be number.");
                scanner.nextLine();
            }
        }
        for (int i = 1; i <= account_amount; i++) {
            System.out.println("No." + i);
            ACCOUNT.add(createAccount());
        }
    }

    private static Account createAccount() {
        System.out.print("Account ID \t= ");
        String number = scanner.nextLine();
        System.out.print("Account Name \t= ");
        String name = scanner.nextLine();
        System.out.print("Gender \t\t= ");
        String gender = scanner.nextLine();
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
            return new Account(number, name, gender, password, balance);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR : " + e.getMessage());
            return createAccount();
        }
    }

    private static void accountInfo(Account account) {
        ACC: while (true) {
            System.out.println("=".repeat(30));
            System.out.println("Menu Service");
            System.out.println("1. Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Choose : ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please input number.");
            }
            switch (choice) {
                case 1:
                    account.checkBalance(btc_rate);
                    break;
                case 2:
                    account.withdraw(btc_rate);
                    break;
                case 3:
                    account.deposit(btc_rate);
                    break;
                case 4:
                    TFM: while (true) {
                        System.out.println("Which account would you like to transfer to?");
                        String accountTransfer = scanner.nextLine();

                        for (Account acc : ACCOUNT) {
                            if (acc.isCorrectId(accountTransfer)) {
                                account.transfer(acc, btc_rate);
                                break TFM;
                            }
                        }
                        System.out.println("Invalid account ID.");
                    }
                    break;
                case 5:
                    break ACC;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }
}