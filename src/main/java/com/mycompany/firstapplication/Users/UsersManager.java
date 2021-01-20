package com.mycompany.firstapplication.Users;

import com.mycompany.firstapplication.Exceptions.UserException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Password;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
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

    public User findByKey(String uuid) {
        return usersRepository.findUserByUuid(uuid);
    }

    public User findByLogin(String login) {
        return usersRepository.findUserByLogin(login);
    }

    //TODO: SPRAWDZIC CZY DZIALA JAK NIE MA TAKIEGO LOGINU
    public User findByLoginPasswordActive(String login, String password) {
        User user = null;
        try {
           user = findByLogin(login);
           if (user.getPassword().equals(password) && user.isActive()) {
               return user;
           }
        } catch (UserException e) {
            e.printStackTrace();
        }
        return user;
    }
}
