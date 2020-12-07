package com.mycompany.firstapplication.Users;

import com.mycompany.firstapplication.Exceptions.UserException;
import com.mycompany.firstapplication.Template.Repository;

import javax.annotation.PostConstruct;

public class UsersRepository extends Repository<User> {

    @Override public void addElement(User user) {
        if (isLoginUnique(user.getLogin())) {
            super.addElement(user);
        } else {
            throw new UserException("Login is not unique");
        }
    }

    public boolean isLoginUnique(String login) {
        for (User user : getElements()) {
            if (user.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    public User findUserByUuid(String uuid) {
        for (User user : getElements()) {
            if (user.getUuid().equals(uuid)) {
                return user;
            }
        }
        throw new UserException("Element not found");
    }

    public User findUserByLogin(String login) {
        for (User user : getElements()) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        throw new UserException("Element not found");
    }

    public void changeActiveForUser(User user) {
        findUserByUuid(user.getUuid()).changeActive();
    }

    @PostConstruct
    private void initUsersList() {
        addElement(new Admin("Admin1", "Adam", "Adamski"));
        addElement(new SuperUser("Manager1", "Tomek", "Tomkowski"));
        addElement(new Client("Client1", "Tomasz", "Hajto", 3, 4));
    }
}
