package com.mycompany.firstapplication.Family;

import com.mycompany.firstapplication.Exceptions.FamilyException;

import java.util.UUID;

public class Child {

    private String name;
    private String surname;
    private int age;
    private UUID uniqueID;

    public Child(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        uniqueID = UUID.randomUUID();
        if (age < 0) {
            throw new FamilyException("Invalid argument");
        }
    }

    public int getAge() {
        return age;
    }

    public UUID getUuid() {
        return uniqueID;
    }

    @Override public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("name", name)
                .append("surname", surname)
                .append("age", age)
                .toString();
    }
}
