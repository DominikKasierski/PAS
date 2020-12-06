package com.mycompany.firstapplication.Users;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ConversationScoped
@Named
public class UsersManager implements Serializable {

    public UsersManager() {
    }

    @Inject
    private UsersRepository usersRepository;

    @Inject
    private Conversation conversation;

    private final Client newClient = new Client();
    private final ResourceManager newResourceManager = new ResourceManager();
    private final Admin newAdmin = new Admin();

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public String processNewClient() {
        conversation.begin();
        return "NewBabysitterConfirm";
    }

    public String processNewResourceManager() {
        conversation.begin();
        return "NewTeachingSitterConfirm";
    }

    public String processNewAdmin() {
        conversation.begin();
        return "NewTidingSitterConfirm";
    }

    public String confirmNewClient() {
        addUser(newClient);
        conversation.end();
        return "main";
    }

    public String confirmNewResourceManager() {
        addUser(newResourceManager);
        conversation.end();
        return "main";
    }

    public String confirmNewAdmin() {
        addUser(newAdmin);
        conversation.end();
        return "main";
    }

    public UsersManager(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void addUser(User user) {
        usersRepository.addElement(user);
    }

    public void deleteUser(User user) {
        usersRepository.deleteElement(user);
    }

    public int getNumberOfClients() {
        return usersRepository.getNumberOfElements();
    }
}
