package com.mycompany.firstapplication.Users;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UsersManager implements Serializable {

    @Inject
    private UsersRepository usersRepository;
    private List<User> currentUsers;
    private TypeOfUser typeOfUser = TypeOfUser.USER;

    private final BiMap<Admin, String> currentAdminTokens = HashBiMap.create();

    public UsersManager() {
        currentAdminTokens.put(new Admin("michu", "rysiu", "jako", "jajko2"), "zajebisty token");
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
            String id = event.getNewValue().toString();
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

    public String getUserRole(User user) {
        return user.getRole();
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

    public BiMap<Admin, String> getCurrentAdminTokens() {
        return currentAdminTokens;
    }

    public String requestOneTimeToken(String login, String password) {

        User user = usersRepository.findUserByLogin(login);
        if (!user.getRole().equals("Admin")) throw new RuntimeException("User is not an admin");
        if (!password.equals(user.getPassword()))
            throw new IllegalArgumentException("Password provided does not match actual password");
        String token = UUID.randomUUID().toString();
        currentAdminTokens.put((Admin) user, token); // adding token to tokens list, overriding current Admin's token if assigned
        return token;
    }
}
