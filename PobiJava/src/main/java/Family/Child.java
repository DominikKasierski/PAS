package Family;

import Exceptions.FamilyException;

public class Child {

    private String name;
    private String surname;
    private int age;

    public Child(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        if (age < 0) {
            throw new FamilyException("Invalid argument");
        }
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
