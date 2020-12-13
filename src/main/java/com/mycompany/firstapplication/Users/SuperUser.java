package com.mycompany.firstapplication.Users;

public class SuperUser extends User {

    public SuperUser(String login, String password, String name, String surname) {
        super(login, password, name, surname);
        this.role = "SuperUser";
    }

    public SuperUser() {
    }
}
