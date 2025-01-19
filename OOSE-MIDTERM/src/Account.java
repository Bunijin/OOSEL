import java.util.InputMismatchException;
import java.util.Scanner;

public class Account extends Person implements ATMaction {

    private String password;
    private double balance;

    private Scanner scanner = new Scanner(System.in);

    public Account(String id, String name, String gender, String password, double balance) {
        super(id, name, gender);
        if (password.length() != 4) {
            throw new IllegalArgumentException("Password must be 4 digit.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Invalid balance.");
        }
        this.password = password;
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isCorrectId(String id) {
        return super.getId().equals(id);
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public void checkBalance(double rate) {
        System.out.printf("Balance: %.2f THB, %.5f BTC%n", this.balance, this.balance / rate);
    }

    @Override
    public void withdraw(double rate) {
        while (true) {
            try {
                System.out.print("Amount to withdraw : ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("BTC or THB\t: ");
                String money_type = scanner.nextLine().toLowerCase();
                if ("thb".equals(money_type)) {
                    if (getBalance() >= amount) {
                        setBalance(getBalance() - amount);
                        break;
                    }
                    System.out.println("Invalid amount.");
                } else if ("btc".equals(money_type)) {
                    if (getBalance() >= amount * rate) {
                        setBalance(getBalance() - amount * rate);
                        break;
                    }
                    System.out.println("Invalid amount.");
                } else
                    System.out.println("Please input BTC or THB.");
            } catch (InputMismatchException e) {
                System.out.println("Please input a number.");
                scanner.nextLine();
            }
        }
    }

    @Override
    public void deposit(double rate) {
        while (true) {
            try {
                System.out.print("Amount to deposit : ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("BTC or THB\t: ");
                String money_type = scanner.nextLine().toLowerCase();
                if ("thb".equals(money_type)) {
                    setBalance(getBalance() + amount);
                    break;
                } else if ("btc".equals(money_type)) {
                    setBalance(getBalance() + (double) amount * rate);
                    break;
                } else
                    System.out.println("Please input BTC or THB.");
            } catch (InputMismatchException e) {
                System.out.println("Please input a number.");
            }
        }
    }

    @Override
    public void transfer(Account acc, double rate) {
        while (true) {
            System.out.print("Amount to transfer : ");
            double amount;
            try {
                amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("BTC or THB\t: ");
                String money_type = scanner.nextLine().toLowerCase();
                if ("thb".equals(money_type)) {
                    if (getBalance() >= amount) {
                        acc.setBalance(acc.getBalance() + amount);
                        setBalance(getBalance() - amount);
                        break;
                    }
                    System.out.println("Invalid amount.");
                } else if ("btc".equals(money_type)) {
                    if (getBalance() >= amount * rate) {
                        acc.setBalance(acc.getBalance() + amount * rate);
                        setBalance(getBalance() - amount * rate);
                        break;
                    }
                    System.out.println("Invalid amount.");
                } else
                    System.out.println("Please input BTC or THB.");
            } catch (InputMismatchException e) {
                System.out.println("Please input a number.");
            }
        }
    }
}
