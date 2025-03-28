public class Account {
    private static int nextId = 1;
    private int id;
    private String name;
    private String password;

    public Account(String name, String password) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Username cannot be empty");
        }
        if (password == null || password.length() < 4) {
            throw new Exception("Password must be at least 4 characters");
        }
        this.id = nextId++;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return name;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }
}