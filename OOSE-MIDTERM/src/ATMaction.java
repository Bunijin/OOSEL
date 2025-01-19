public interface ATMaction {
    void checkBalance(double btc_rate);

    void withdraw(double btc_rate);

    void deposit(double btc_rate);

    void transfer(Account account, double btc_rate);
}
