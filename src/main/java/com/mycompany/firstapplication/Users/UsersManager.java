package com.mycompany.firstapplication.Users;

import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Babysitters.TeachingSitter;
import com.mycompany.firstapplication.Babysitters.TidingSitter;
import com.mycompany.firstapplication.Babysitters.TypeOfBabysitter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsersManager implements Serializable {

    @Inject
    private UsersRepository usersRepository;
    private List<User> currentUsers;
    private String id;
    private TypeOfUser typeOfUser = TypeOfUser.USER;

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

    public void valueChanged(ValueChangeEvent event) {
        if (!event.getNewValue().toString().equals("0")) {
            id = event.getNewValue().toString();
            showSelectedUser(id);
        }
    }

    private void showSelectedUser(String id) {
        List<User> temporaryUsersList = new ArrayList<>();
        temporaryUsersList.add(usersRepository.findUserByUuid(id));
        currentUsers = temporaryUsersList;
        setType();
    }

    private void setType() {
        if (currentUsers.get(0) instanceof Client) {
            typeOfUser = TypeOfUser.CLIENT;
        } else {
            typeOfUser = TypeOfUser.USER;
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
        typeOfUser = TypeOfUser.USER;
        currentUsers = usersRepository.getUsersList();
    }
}
