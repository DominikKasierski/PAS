package com.mycompany.firstapplication.Users;

public class Admin extends User {

    public Admin(String login, String name, String surname, String password) {
        super(login, name, surname, password);
        super.setRole("Admin");
    }

    public Admin() {
    }
}
