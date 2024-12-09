public class Accounts {
    private String name;
    private String number;
    private String password;
    private double balance;

    public Accounts(String name, String number, String password, double balance) {
        if (name.length() > 50) {
            throw new IllegalArgumentException("Account name length limit is 50 characters.");
        }
        if (number.length() != 13) {
            throw new IllegalArgumentException("Account number must be 13 digit.");
        }
        if (password.length() != 4) {
            throw new IllegalArgumentException("Password must be 4 digit.");
        }
        if (balance < 0 || balance > 1000000.0) {
            throw new IllegalArgumentException("Maximum balance is 1,000,000 Baht.");
        }
        this.name = name;
        this.number = number;
        this.password = password;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getPassword() {
        return password;
    }
}
