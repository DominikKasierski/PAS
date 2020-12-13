package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.TypeOfBabysitter;
import com.mycompany.firstapplication.Exceptions.UserException;
import com.mycompany.firstapplication.Users.*;

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

    private TypeOfUser typeOfUser;

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public Object getSomeUser(TypeOfUser type) {
        switch (type) {
            case ADMIN:
                return newAdmin;
            case SUPERUSER:
                return newSuperUser;
            default:
                return newClient;
        }
    }

    public TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    public String userType(String string) {
        conversation.begin();
        if (string.equals("ADMIN")) {
            this.typeOfUser = TypeOfUser.ADMIN;
        } else if (string.equals("SUPERUSER")) {
            this.typeOfUser = TypeOfUser.SUPERUSER;
        } else {
            this.typeOfUser = TypeOfUser.CLIENT;
        }
        return "NewUser";
    }

    public String processNewUser() {
        return "NewUserConfirm";
    }

    //TODO:Pododawać do kazdego message i odpowiednie przejścia

    public String confirmNewUser(TypeOfUser typeOfUser) {
        try {
            switch (typeOfUser) {
                case ADMIN:
                    usersManager.addUser(newAdmin);
                    break;
                case SUPERUSER:
                    usersManager.addUser(newSuperUser);
                    break;
                case CLIENT:
                    usersManager.addUser(newClient);
                    break;
            }
            return backToMain();
        } catch (UserException e) {
            FacesContext.getCurrentInstance().addMessage("newAdminConfirm:login",
                    new FacesMessage("Taki login już istnieje"));
        }
        return "";
    }

    public String backToMain() {
        conversation.end();
        return "main";
    }


    public String reject() {
        conversation.end();
        return userType(typeOfUser.toString());
    }
}
