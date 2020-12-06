package com.mycompany.firstapplication.Users;

public class Client extends User {

    private int numberOfChildren;
    private int ageOfTheYoungestChild;

    public Client() {
    }

    public Client(String login, String name, String surname, int numberOfChildren,
                  int ageOfTheYoungestChild) {
        super(login, name, surname);
        this.numberOfChildren = numberOfChildren;
        this.ageOfTheYoungestChild = ageOfTheYoungestChild;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setAgeOfTheYoungestChild(int ageOfTheYoungestChild) {
        this.ageOfTheYoungestChild = ageOfTheYoungestChild;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public int getAgeOfTheYoungestChild() {
        return ageOfTheYoungestChild;
    }
}
