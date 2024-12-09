public interface ATMaction {
    void checkBalance();

    void withdraw();

    void deposit();

    void transfer(Account account);
}
