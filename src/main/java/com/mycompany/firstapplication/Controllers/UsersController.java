package com.mycompany.firstapplication.Controllers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mycompany.firstapplication.Exceptions.UserException;
import com.mycompany.firstapplication.Users.Admin;
import com.mycompany.firstapplication.Users.Client;
import com.mycompany.firstapplication.Users.SuperUser;
import com.mycompany.firstapplication.Users.UsersManager;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ConversationScoped
@Named
public class UsersController implements Serializable {

    @Inject
    private UsersManager usersManager;

    @Inject
    private Conversation conversation;

    private final Client newClient = new Client();
    private final SuperUser newSuperUser = new SuperUser();
    private final Admin newAdmin = new Admin();


    public Client getNewClient() {
        return newClient;
    }

    public SuperUser getNewSuperUser() {
        return newSuperUser;
    }

    public Admin getNewAdmin() {
        return newAdmin;
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public String processNewClient() {
        conversation.begin();
        return "NewClientConfirm";
    }

    public String processNewSuperUser() {
        conversation.begin();
        return "NewSuperUserConfirm";
    }

    public String processNewAdmin() {
        conversation.begin();
        return "NewAdminConfirm";
    }

    public String confirmNewClient() {
        usersManager.addUser(newClient);
        conversation.end();
        return "main";
    }

    public String confirmNewSuperUser() {
        usersManager.addUser(newSuperUser);
        conversation.end();
        return "main";
    }

    //TODO:Pododawać do kazdego message i odpowiednie przejścia
    public String confirmNewAdmin() {
        try {
            usersManager.addUser(newAdmin);
            conversation.end();
            return "main";
        } catch (UserException e) {
            FacesContext.getCurrentInstance().addMessage("newAdminConfirm:login",
                    new FacesMessage("Taki login już istnieje"));
        }
        return "";
    }

    public boolean checkAndUseAdminToken(String token) {
        if (!usersManager.getCurrentAdminTokens().containsValue(token))
            throw new RuntimeException("Token provided is not a valid Admin token");
        usersManager.getCurrentAdminTokens().inverse().remove(token); // reversing map view so that values are keys now
        return true;
    }

    public String reject() {
        conversation.end();
        return "NewAdmin";
    }
}
