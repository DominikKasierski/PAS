package com.mycompany.firstapplication.Users;

public class Client extends User {
    private Integer numberOfChildren;
    private Integer ageOfTheYoungestChild;

    public Client() {
    }

    public Client(String login, String password, String name, String surname, int numberOfChildren,
                  int ageOfTheYoungestChild) {
        super(login, password, name, surname);
        this.numberOfChildren = numberOfChildren;
        this.ageOfTheYoungestChild = ageOfTheYoungestChild;
        this.role = "Client";
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
