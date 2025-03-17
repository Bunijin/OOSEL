public class Account {
    private static int idCounter = 0;
    private int id;
    private String username;
    private String password;

    public Account(String username, String password) {
        this.id = idCounter++;
        this.username = username;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }
}