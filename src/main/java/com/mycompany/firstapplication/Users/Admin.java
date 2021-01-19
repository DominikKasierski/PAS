package com.mycompany.firstapplication.Users;

public class Admin extends User {

    private final String role = "Admin";

    public Admin(String login, String name, String surname, String password) {
        super(login, name, surname, password);
    }

    public Admin() {
    }
}
