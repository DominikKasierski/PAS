package com.mycompany.firstapplication.Users;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
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
    private List<User> currentUsers;
    private TypeOfUser typeOfUser = TypeOfUser.ADMIN;

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

    public List<User> getCurrentUsers() {
        return currentUsers;
    }

    public List<Client> getClientList() {
        return usersRepository.getClientList();
    }

    public TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    public void valueChangedId(ValueChangeEvent event) {
        if (!event.getNewValue().toString().equals("0")) {
            String id = event.getNewValue().toString();
            showSelectedUser(id, true);
        }
    }

    public void valueChangedLogin(ValueChangeEvent event) {
        if (!event.getNewValue().toString().equals("0")) {
            String login = event.getNewValue().toString();
            showSelectedUser(login, false);
        }
    }

    private void showSelectedUser(String key, boolean checkById) {
        List<User> temporaryUsersList = new ArrayList<>();
        if (checkById) {
            temporaryUsersList.add(usersRepository.findUserByUuid(key));
        } else {
            temporaryUsersList.add(usersRepository.findUserByLogin(key));
        }
        currentUsers = temporaryUsersList;
        setType();
    }

    private void setType() {
        if (currentUsers.get(0) instanceof Client) {
            typeOfUser = TypeOfUser.CLIENT;
        } else {
            typeOfUser = TypeOfUser.ADMIN;
        }
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

    @PostConstruct
    public void initCurrentPersons() {
        typeOfUser = TypeOfUser.ADMIN;
        currentUsers = usersRepository.getUsersList();
    }
}
