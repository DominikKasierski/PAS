package com.mycompany.firstapplication.Users;


import com.mycompany.firstapplication.Exceptions.UserException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsersRepository {

    private static List<Client> clients = new ArrayList<>();

    public static boolean isLoginUnique(String login) {
        for (Client client : clients) {
            if (client.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    public static void addToList(Client client) {
        clients.add(client);
    }

    public static Client findClient(UUID uuid) {
        for (Client client : clients) {
            if (client.getUuid() == uuid) {
                return client;
            }
        }
        throw new UserException("Element not found");
    }

    public static Client findClient(String login) {
        for (Client client : clients) {
            if (client.getLogin().equals(login)) {
                return client;
            }
        }
        throw new UserException("Element not found");
    }

    public static List<Client> getClients () {
        return new ArrayList<>(clients);
    }

    public void setActive(Client client) {
        findClient(client.getUuid()).setActive(true);
    }

    public void setInactive(Client client) {
        findClient(client.getUuid()).setActive(false);
    }
}
