package com.mycompany.firstapplication.Users;

public class SuperUser extends User {

    private final String role = "SuperUser";

    public SuperUser(String login, String name, String surname) {
        super(login, name, surname);
    }

    public SuperUser() {
    }
}
