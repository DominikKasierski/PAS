package com.mycompany.firstapplication.Users;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Client extends User {

    @NotNull
    @Min(0)
    @Max(15)
    private Integer numberOfChildren;

    @NotNull
    @Min(0)
    @Max(15)
    private Integer ageOfTheYoungestChild;

    public Client() {
    }

    public Client(String login, String name, String surname, String password, int numberOfChildren,
                  int ageOfTheYoungestChild) {
        super(login, name, surname, password);
        this.numberOfChildren = numberOfChildren;
        this.ageOfTheYoungestChild = ageOfTheYoungestChild;
        super.setRole("Client");
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setAgeOfTheYoungestChild(Integer ageOfTheYoungestChild) {
        this.ageOfTheYoungestChild = ageOfTheYoungestChild;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public Integer getAgeOfTheYoungestChild() {
        return ageOfTheYoungestChild;
    }

}
