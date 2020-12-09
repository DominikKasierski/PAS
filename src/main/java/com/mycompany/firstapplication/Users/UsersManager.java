package com.mycompany.firstapplication.Users;

import com.mycompany.firstapplication.Babysitters.Babysitter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class UsersManager implements Serializable {

    @Inject
    private UsersRepository usersRepository;

    private List<User> currentUsers;

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

    public List<Client> getClientList() {
        return usersRepository.getClientList();
    }

    public User[] getAllUsersArray() {
        return usersRepository.getUsersList().toArray(new User[0]);
    }

    public void addUser(User user) {
        usersRepository.addElement(user);
    }

    public void deleteUser(User user) {
        usersRepository.deleteElement(user);
    }

    public void changeActive (User user) {
        usersRepository.changeActiveForUser(user);
    }

    public int getNumberOfClients() {
        return usersRepository.getNumberOfElements();
    }

    @PostConstruct
    public void initCurrentPersons() {
        currentUsers = usersRepository.getElements();
    }
}
