package com.mycompany.firstapplication.Users;


import com.mycompany.firstapplication.Exceptions.UserException;
import java.util.UUID;

public class Client {

    private boolean isActive;
    private String login;
    private UUID uniqueID;

    public Client(String login) {
        if (UsersRepository.isLoginUnique(login)) {
            this.login = login;
        } else {
            throw new UserException("This login is already reserved");
        }
        uniqueID = UUID.randomUUID();
        UsersRepository.addToList(this);
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public UUID getUuid() {
        return uniqueID;
    }

    public String getLogin() {
        return login;
    }
}
