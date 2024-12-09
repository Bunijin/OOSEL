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
    public void checkBalance() {
        System.out.println("Balance : " + balance + " Baht");
    }

    @Override
    public void withdraw() {
        System.out.println("Amount to withdraw : ");
        int amount;
        while (true) {
            try {
                amount = scanner.nextInt();
                scanner.nextLine();
                if (getBalance() - amount < 0) {
                    System.out.println("Invalid amount.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input a number.");
            }
        }
        setBalance(getBalance() - amount);
    }

    @Override
    public void deposit() {
        System.out.println("Amount to deposit : ");
        int amount;
        while (true) {
            try {
                amount = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please input a number.");
            }
        }
        setBalance(getBalance() + amount);
    }

    @Override
    public void transfer(Account acc) {
        while (true) {
            System.out.println("Amount to transfer : ");
            int amount;
            while (true) {
                try {
                    amount = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please input a number.");
                }
            }
            if (getBalance() - amount >= 0) {
                acc.setBalance(acc.getBalance() + amount);
                setBalance(getBalance() - amount);
                return;
            } else {
                System.out.println("Insufficent amount.");
            }
        }
    }
}
