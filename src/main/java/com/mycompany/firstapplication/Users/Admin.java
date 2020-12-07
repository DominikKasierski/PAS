package com.mycompany.firstapplication.Users;

public class Admin extends User {

    private final String role = "Admin";

    public Admin(String login, String name, String surname) {
        super(login, name, surname);
    }

    public Admin() {
    }
}
