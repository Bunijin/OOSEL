public class Manager extends Person {

    private String password;

    public Manager(String id, String name, String gender, String password) {
        super(id, name, gender);
        if (password.length() != 4) {
            throw new IllegalArgumentException("Password must be 4 digit.");
        }
        this.password = password;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

}