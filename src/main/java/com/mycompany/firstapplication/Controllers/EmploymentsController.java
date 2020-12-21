package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Employment.Employment;
import com.mycompany.firstapplication.Employment.EmploymentsManager;
import com.mycompany.firstapplication.Exceptions.EmploymentException;
import com.mycompany.firstapplication.Exceptions.RepositoryException;
import com.mycompany.firstapplication.Users.Client;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@ConversationScoped
@Named
public class EmploymentsController extends Conversational implements Serializable {

    @Inject
    private EmploymentsManager employmentsManager;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Babysitter currentBabysitter;
    private Client currentClient;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(
            "bundles/messages", FacesContext.getCurrentInstance().getViewRoot().getLocale()); //getting current locale from FacesContext

    public String processNewEmployment() {
        beginNewConversation();
        return "NewEmploymentConfirm";
    }

    public String confirmNewEmployment() {
        try {
            employmentsManager.checkIfBabysitterExists(currentBabysitter);
            employmentsManager.employBabysitter(currentClient, currentBabysitter);
        } catch (EmploymentException e) {
//            throw new ValidatorException(new FacesMessage(resourceBundle.getString("ValidatorMessageEmploymentRequirements")));
            FacesContext.getCurrentInstance().addMessage(
                    "newEmploymentConfirm:validationLabel",
                    new FacesMessage(resourceBundle.getString("ValidatorMessageEmploymentRequirements")));
            return "";
        } catch (RepositoryException e) {
            FacesContext.getCurrentInstance().addMessage(
                    "newEmploymentConfirm:validationLabel",
                    new FacesMessage(resourceBundle.getString("ValidatorMessageBabysitterDoesNotExist")));
            return "";
        }
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

    public void employmentValidation(FacesContext context, UIComponent component, Object value) {
        int errorNumber = 1;
        try {
            employmentsManager.checkIfBabysitterMeetsRequirements(currentBabysitter, currentClient.getAgeOfTheYoungestChild(),
                    currentClient.getNumberOfChildren());
            errorNumber++;
            employmentsManager.checkIfBabysitterIsCurrentlyEmployed(currentBabysitter);
        } catch (EmploymentException exception) {
            if (errorNumber == 1)
                throw new ValidatorException(new FacesMessage(resourceBundle.getString("ValidatorMessageEmploymentRequirements")));

            throw new ValidatorException(new FacesMessage(resourceBundle.getString("ValidatorMessageBabysitterAlreadyEmployed")));
        }
    }

    public void deleteEmployment(Employment employment) {
        employmentsManager.deleteEmployment(employment);
    }

    public String reject() {
        endCurrentConversation();
        return "main";
    }
}
