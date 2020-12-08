package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.Babysitter;

import com.mycompany.firstapplication.Employment.EmploymentsManager;
import com.mycompany.firstapplication.Users.Client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Babysitter currentBabysitter;
    private Client currentClient;

    @Inject
    private Conversation conversation;

    public String processNewEmployment() {
        conversation.begin();
        return "NewEmploymentConfirm";
    }

    public String confirmNewEmployment() {
        employmentsManager.employBabysitter(currentClient, currentBabysitter);
        conversation.end();
        return "main";
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
}
