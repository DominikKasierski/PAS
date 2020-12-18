package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.TypeOfBabysitter;
import com.mycompany.firstapplication.Exceptions.UserException;
import com.mycompany.firstapplication.Users.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ConversationScoped
@Named
public class UsersController extends Conversational implements Serializable {

    @Inject
    private UsersManager usersManager;

    private final Client newClient = new Client();
    private final SuperUser newSuperUser = new SuperUser();
    private final Admin newAdmin = new Admin();

    private TypeOfUser typeOfUser = TypeOfUser.ADMIN;
    private List<User> currentUsers;

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
        beginNewConversation();
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
            FacesContext.getCurrentInstance().addMessage("newUserConfirm:login",
                    new FacesMessage("Taki login już istnieje"));
        }
        return "";
    }

    public String backToMain() {
        endCurrentConversation();
        return "main";
    }


    public String reject() {
        endCurrentConversation();
        return userType(typeOfUser.toString());
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
            temporaryUsersList.add(usersManager.getUsersRepository().findUserByUuid(key));
        } else {
            temporaryUsersList.add(usersManager.getUsersRepository().findUserByLogin(key));
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

    public List<User> getCurrentUsers() {
        return currentUsers;
    }

    @PostConstruct
    public void initCurrentPersons() {
        typeOfUser = TypeOfUser.ADMIN;
        currentUsers = usersManager.getUsersRepository().getUsersList();
    }
}
