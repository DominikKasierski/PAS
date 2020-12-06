package com.mycompany.firstapplication.Users;


import com.mycompany.firstapplication.Exceptions.UserException;
import org.apache.commons.lang3.RandomStringUtils;

public abstract class User {

    private boolean isActive;
    private String login;
    private String name;
    private String surname;
    private final String uniqueID;
    final int SHORT_ID_LENGTH = 8;

    public User() {
        uniqueID = RandomStringUtils.randomAlphabetic(SHORT_ID_LENGTH);
    }

    public User(String login, String name, String surname) {
        if (login.isEmpty() || name.isEmpty() || surname.isEmpty()) {
            throw new UserException("Empty login, name or surname");
        }
        this.login = login;
        this.name = name;
        this.surname = surname;
        uniqueID = RandomStringUtils.randomAlphabetic(SHORT_ID_LENGTH);
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

    public String getUuid() {
        return uniqueID;
    }

    public String getLogin() {
        return login;
    }
}
