package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.*;
import com.mycompany.firstapplication.Exceptions.BabysitterException;
import com.mycompany.firstapplication.Exceptions.RepositoryException;
import org.apache.commons.beanutils.BeanUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ConversationScoped
@Named
public class ResourcesController extends Conversational implements Serializable {

    @Inject
    private BabysittersManager babysittersManager;

    private final Babysitter newBabysitter = new Babysitter();
    private final TeachingSitter newTeachingSitter = new TeachingSitter();
    private final TidingSitter newTidingSitter = new TidingSitter();

//    private List<Babysitter> currentBabysitters;
    private TypeOfBabysitter typeOfBabysitter = TypeOfBabysitter.NORMAL;

    private Babysitter copyOfBabysitter;
    private Babysitter originalBabysitter;

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(
            "bundles/messages", FacesContext.getCurrentInstance().getViewRoot().getLocale()); //getting current locale from FacesContext


    public BabysittersManager getBabysittersManager() {
        return babysittersManager;
    }

    public String modifyBabysitter(Babysitter babysitter) {
        beginNewConversation();
        try {
            copyOfBabysitter = (Babysitter)babysitter.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        originalBabysitter = babysitter;
        return "ModifyBabysitter";
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
        babysittersManager.setCurrentBabysitters(temporaryBabysittersList);
        setType();
    }

    private void setType() {
        if (getCurrentBabysitters().get(0) instanceof TeachingSitter) {
            typeOfBabysitter = TypeOfBabysitter.TEACHING;
        } else if (getCurrentBabysitters().get(0) instanceof TidingSitter) {
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
        beginNewConversation();
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

    public String processModifiedBabysitter() {
        return "ModifyBabysitterConfirm";
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

    public Babysitter getOriginalBabysitter() {
        return originalBabysitter;
    }

    public Babysitter getCopyOfBabysitter() {
        return copyOfBabysitter;
    }

    public String deleteBabysitter(Babysitter babysitter) {
        try {
            babysittersManager.deleteBabysitter(babysitter);
            babysittersManager.deleteBabysitterFromEmploymentList(babysitter);
            return "BabysitterList";
        } catch (BabysitterException | RepositoryException exception) {
            throw new ValidatorException(new FacesMessage(resourceBundle.getString("babysitterOccupied")));
        }
    }

    public String modificationBackToMain() {
        try {
            BeanUtils.copyProperties(originalBabysitter, copyOfBabysitter);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        endCurrentConversation();
        return "main";
    }

    public String backToMain() {
        endCurrentConversation();
        return "main";
    }

    public List<Babysitter> getCurrentBabysitters() {
        return babysittersManager.getCurrentBabysitters();
    }

    public void refreshCurrent() {
        babysittersManager.initCurrentPersons();
    }

}
