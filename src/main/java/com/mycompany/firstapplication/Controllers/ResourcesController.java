package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.*;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

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

    private TypeOfBabysitter typeOfBabysitter;

    public BabysittersManager getBabysittersManager() {
        return babysittersManager;
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

    public String sitterType(String string)  {
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

    public String backToMain() {
        conversation.end();
        return "main";
    }
}
