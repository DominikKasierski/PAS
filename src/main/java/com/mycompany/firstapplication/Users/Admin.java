package com.mycompany.firstapplication.Users;

public class Admin extends User {

    public Admin(String login, String password, String name, String surname) {
        super(login, password, name, surname);
        this.role = "Admin";
    }


    public Admin() {
    }

}
