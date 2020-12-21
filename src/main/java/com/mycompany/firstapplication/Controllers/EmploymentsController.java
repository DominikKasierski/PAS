package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Employment.Employment;
import com.mycompany.firstapplication.Employment.EmploymentsManager;
import com.mycompany.firstapplication.Users.Client;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@ConversationScoped
@Named
public class EmploymentsController extends Conversational implements Serializable {

    @Inject
    private EmploymentsManager employmentsManager;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Babysitter currentBabysitter;
    private Client currentClient;

    public String processNewEmployment() {
        beginNewConversation();
        return "NewEmploymentConfirm";
    }

    public String confirmNewEmployment() {
        employmentsManager.checkIfBabysitterExist(currentBabysitter);
        employmentsManager.employBabysitter(currentClient, currentBabysitter);
        return reject();
    }

    public EmploymentsManager getEmploymentsManager() {
        return employmentsManager;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public Babysitter getCurrentBabysitter() {
        return currentBabysitter;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentBabysitter(Babysitter currentBabysitter) {
        this.currentBabysitter = currentBabysitter;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public void deleteEmployment(Employment employment) {
        employmentsManager.deleteEmployment(employment);
    }

    public String reject() {
        endCurrentConversation();
        return "main";
    }
}
