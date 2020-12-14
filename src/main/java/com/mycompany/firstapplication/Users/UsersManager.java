package com.mycompany.firstapplication.Users;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
public class UsersManager implements Serializable {

    @Inject
    private UsersRepository usersRepository;

    public UsersManager() {
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public UsersManager(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getUsersList() {
        return usersRepository.getUsersList();
    }

    public User[] getAllUsersArray() {
        return usersRepository.getUsersList().toArray(new User[0]);
    }

    public List<Client> getClientList() {
        return usersRepository.getClientList();
    }

    public void addUser(User user) {
        usersRepository.addElement(user);
    }

    public void deleteUser(User user) {
        usersRepository.deleteElement(user);
    }

    public void changeActive(User user) {
        usersRepository.changeActiveForUser(user);
    }

    public int getNumberOfClients() {
        return usersRepository.getNumberOfElements();
    }
}
