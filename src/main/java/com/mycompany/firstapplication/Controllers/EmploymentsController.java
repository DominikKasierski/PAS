package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Employment.Employment;
import com.mycompany.firstapplication.Employment.EmploymentsManager;
import com.mycompany.firstapplication.Users.Client;
import com.mycompany.firstapplication.Users.UsersManager;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ConversationScoped
@Named
public class EmploymentsController implements Serializable {

    @Inject
    private EmploymentsManager employmentsManager;

    private Babysitter babysitter;
    private Client client;

    @Inject
    private Conversation conversation;

    private final Employment employment = new Employment();

    public String processNewEmployment() {
        conversation.begin();
        return "NewEmploymentConfirm";
    }

    public String confirmNewEmployment() {
        employmentsManager.employBabysitter(client, babysitter);
        conversation.end();
        return "main";
    }

    public void setBabysitter(Babysitter babysitter) {
        this.babysitter = babysitter;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
