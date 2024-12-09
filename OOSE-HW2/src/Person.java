public class Person {
    private String id;
    private String name;
    private String gender;
    public Person (String id,String name,String gender) {
        if(id.length() != 13) {
            throw new IllegalArgumentException("ID must be 13 digit.");
        }
        if(gender == null || (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female"))) {
            throw new IllegalArgumentException("Gender must be Male or Female.");
        }
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public String getId() {
        return id;
    }
}
