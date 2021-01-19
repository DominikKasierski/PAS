package com.mycompany.firstapplication.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.firstapplication.Exceptions.UserException;

import javax.validation.constraints.NotNull;

public abstract class User implements Cloneable {

    private boolean isActive = true;

    @NotNull
    private String login;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @JsonIgnore
    private String password;

    private String uniqueID;
    private String role;

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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
