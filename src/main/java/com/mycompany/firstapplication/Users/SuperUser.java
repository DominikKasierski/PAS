package com.mycompany.firstapplication.Users;

public class SuperUser extends User {

    public SuperUser(String login, String name, String surname, String password) {
        super(login, name, surname, password);
        super.setRole("SuperUser");
    }

    public SuperUser() {
    }
}
