package com.mycompany.firstapplication.Users;


import com.mycompany.firstapplication.Exceptions.UserException;

public abstract class User implements Cloneable {

    private boolean isActive = true;
    private String login;
    private String name;
    private String surname;
    private String password;
    private String uniqueID;

    public User() {
    }

    public User(String login, String name, String surname, String password) {
        if (login.isEmpty() || name.isEmpty() || surname.isEmpty() || password.isEmpty()) {
            throw new UserException("Empty login, name, surname or password");
        }
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public void changeActive() {
        isActive = !isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getNumberOfChildren() {
        return null;
    }

    public Integer getAgeOfTheYoungestChild() {
        return null;
    }

    public String getUuid() {
        return uniqueID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
