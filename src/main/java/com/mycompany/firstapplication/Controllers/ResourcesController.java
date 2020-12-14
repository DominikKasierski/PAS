package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.*;
import com.mycompany.firstapplication.Exceptions.BabysitterException;
import com.mycompany.firstapplication.Exceptions.RepositoryException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ConversationScoped
@Named
public class ResourcesController implements Serializable {

    @Inject
    private BabysittersManager babysittersManager;

    @Inject
    private Conversation conversation;

    private final Babysitter newBabysitter = new Babysitter();
    private final TeachingSitter newTeachingSitter = new TeachingSitter();
    private final TidingSitter newTidingSitter = new TidingSitter();

    private List<Babysitter> currentBabysitters;
    private TypeOfBabysitter typeOfBabysitter = TypeOfBabysitter.NORMAL;

    public BabysittersManager getBabysittersManager() {
        return babysittersManager;
    }

    public void valueChanged(ValueChangeEvent event) {
        if (!event.getNewValue().toString().equals("0")) {
            String id = event.getNewValue().toString();
            showSelectedBabysitter(id);
        }
    }

    private void showSelectedBabysitter(String id) {
        List<Babysitter> temporaryBabysittersList = new ArrayList<>();
        temporaryBabysittersList.add(babysittersManager.getBabysittersRepository().findByKey(id));
        currentBabysitters = temporaryBabysittersList;
        setType();
    }

    private void setType() {
        if (currentBabysitters.get(0) instanceof TeachingSitter) {
            typeOfBabysitter = TypeOfBabysitter.TEACHING;
        } else if (currentBabysitters.get(0) instanceof TidingSitter) {
            typeOfBabysitter = TypeOfBabysitter.TIDING;
        } else {
            typeOfBabysitter = TypeOfBabysitter.NORMAL;
        }
    }

    public Object getSomeBabysitter(TypeOfBabysitter type) {
        switch (type) {
            case TIDING:
                return newTidingSitter;
            case TEACHING:
                return newTeachingSitter;
            default:
                return newBabysitter;
        }
    }

    public TypeOfBabysitter getTypeOfBabysitter() {
        return typeOfBabysitter;
    }

    public String sitterType(String string) {
        conversation.begin();
        if (string.equals("TEACHING")) {
            this.typeOfBabysitter = TypeOfBabysitter.TEACHING;
        } else if (string.equals("TIDING")) {
            this.typeOfBabysitter = TypeOfBabysitter.TIDING;
        } else {
            this.typeOfBabysitter = TypeOfBabysitter.NORMAL;
        }
        return "NewBabysitter";
    }

    public String processNewBabysitter() {
        return "NewBabysitterConfirm";
    }

    public String confirmNewBabysitter(TypeOfBabysitter typeOfBabysitter) {
        switch (typeOfBabysitter) {
            case NORMAL:
                babysittersManager.addBabysitter(newBabysitter);
                break;
            case TEACHING:
                babysittersManager.addBabysitter(newTeachingSitter);
                break;
            case TIDING:
                babysittersManager.addBabysitter(newTidingSitter);
                break;
        }
        return backToMain();
    }

    public String deleteBabysitter(Babysitter babysitter) {
        try {
            babysittersManager.deleteBabysitter(babysitter);
            return "BabysitterList";
        } catch (BabysitterException | RepositoryException exception) {
            FacesContext.getCurrentInstance().addMessage("BabysitterList:delete",
                    new FacesMessage("Opiekunka jest zatrudniona"));
        }
        return "";
    }

    public String backToMain() {
        conversation.end();
        return "main";
    }

        public List<Babysitter> getCurrentBabysitters() {
        return currentBabysitters;
    }

    @PostConstruct
    public void initCurrentPersons() {
        typeOfBabysitter = TypeOfBabysitter.NORMAL;
        currentBabysitters = babysittersManager.getBabysittersRepository().getBabysittersList();
    }
}
