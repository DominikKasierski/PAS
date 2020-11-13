package Family;

public class Child {

    private String name;
    private String surname;
    private int age;

    public Child(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("name", name)
                .append("surname", surname)
                .append("age", age)
                .toString();
    }
}
