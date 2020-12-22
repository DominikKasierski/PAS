package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Employment.Employment;
import com.mycompany.firstapplication.Employment.EmploymentsManager;
import com.mycompany.firstapplication.Exceptions.EmploymentException;
import com.mycompany.firstapplication.Exceptions.RepositoryException;
import com.mycompany.firstapplication.Users.Client;
import com.mycompany.firstapplication.Users.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ConversationScoped
@Named
public class EmploymentsController extends Conversational implements Serializable {

    @Inject
    private EmploymentsManager employmentsManager;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Babysitter currentBabysitter;
    private Client currentClient;

    private List<Employment> currentEmployments;

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(
            "bundles/messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    public String processNewEmployment() {
        beginNewConversation();
        return "NewEmploymentConfirm";
    }

    public String confirmNewEmployment() {
        try {
            employmentsManager.checkIfBabysitterExists(currentBabysitter);
            employmentsManager.employBabysitter(currentClient, currentBabysitter);
        } catch (EmploymentException e) {
            FacesContext.getCurrentInstance().addMessage(
                    "newEmploymentConfirm:validationLabel",
                    new FacesMessage(
                            resourceBundle.getString("ValidatorMessageEmploymentRequirements")));
            return "";
        } catch (RepositoryException e) {
            FacesContext.getCurrentInstance().addMessage(
                    "newEmploymentConfirm:validationLabel",
                    new FacesMessage(
                            resourceBundle.getString("ValidatorMessageBabysitterDoesNotExist")));
            return "";
        }
        return reject();
    }

    public void valueChangedUser(ValueChangeEvent event) {
        if (!event.getNewValue().toString().equals("0")) {
            String id = event.getNewValue().toString();
            showSelected(id);
        }
    }

    public void valueChangedBabysitter(ValueChangeEvent event) {
        if (!event.getNewValue().toString().equals("0")) {
            String id = event.getNewValue().toString();
            showSelected(id);
        }
    }

    private void showSelected(String id) {
        List<Employment> temporaryEmploymentList = new ArrayList<>();
        for (Employment employment : employmentsManager.getEmploymentsRepository()
                .getElements()) {
            if (employment.getClient().getUuid().equals(id) || employment.getBabysitter().getUuid().equals(id)) {
                temporaryEmploymentList.add(employment);
                break;
            }
        }
        currentEmployments = temporaryEmploymentList;
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

    public void employmentValidation(FacesContext context, UIComponent component, Object value) {
        int errorNumber = 1;
        try {
            employmentsManager.checkIfBabysitterMeetsRequirements(currentBabysitter,
                    currentClient.getAgeOfTheYoungestChild(),
                    currentClient.getNumberOfChildren());
            errorNumber++;
            employmentsManager.checkIfBabysitterIsCurrentlyEmployed(currentBabysitter);
        } catch (EmploymentException exception) {
            if (errorNumber == 1)
                throw new ValidatorException(new FacesMessage(
                        resourceBundle.getString("ValidatorMessageEmploymentRequirements")));

            throw new ValidatorException(new FacesMessage(
                    resourceBundle.getString("ValidatorMessageBabysitterAlreadyEmployed")));
        }
    }

    public void deleteEmployment(Employment employment) {
        employmentsManager.deleteEmployment(employment);
    }

    public String reject() {
        endCurrentConversation();
        return "main";
    }

    public Employment getActualEmploymentForClientOrNull (Client client) {
        try {
            return employmentsManager.getActualEmploymentForClient(client);
        } catch (EmploymentException e) {
            return null;
        }
    }

    public Employment getActualEmploymentForBabysitterOrNull (Babysitter babysitter) {
        try {
            return employmentsManager.getActualEmploymentForBabysitter(babysitter);
        } catch (EmploymentException e) {
            return null;
        }
    }

    @PostConstruct
    public void initCurrentPersons() {
        currentEmployments = employmentsManager.getEmploymentsRepository().getElements();
    }

    public List<Employment> getCurrentEmployments() {
        return currentEmployments;
    }

    public void refreshCurrent() {
        currentEmployments = getEmploymentsManager().getEmploymentsRepository().getElements();
    }
}
