package Users;

import Exceptions.UserException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsersData {

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
}
