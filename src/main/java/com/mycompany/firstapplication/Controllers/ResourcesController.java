package com.mycompany.firstapplication.Controllers;

import com.mycompany.firstapplication.Babysitters.*;

import javax.annotation.PostConstruct;
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

    @PostConstruct
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

    public Babysitter getNewBabysitter() {
        return newBabysitter;
    }

    public TeachingSitter getNewTeachingSitter() {
        return newTeachingSitter;
    }

    public TidingSitter getNewTidingSitter() {
        return newTidingSitter;
    }

    public BabysittersManager getBabysittersManager() {
        return babysittersManager;
    }

    //TODO:Sprobowac uproscic
    public String processNewBabysitter() {
//        conversation.begin();
        return "NewBabysitterConfirm";
    }

    public String processNewTeachingSitter() {
//        conversation.begin();
        return "NewTeachingSitterConfirm";
    }

    public String processNewTidingSitter() {
//        conversation.begin();
        return "NewTidingSitterConfirm";
    }

    public String confirmNewBabysitter() {
        babysittersManager.addBabysitter(newBabysitter);
        conversation.end();
        return "main";
    }

    public String confirmNewTeachingSitter() {
        babysittersManager.addBabysitter(newTeachingSitter);
        conversation.end();
        return "main";
    }

    public String confirmNewTidingSitter() {
        babysittersManager.addBabysitter(newTidingSitter);
        conversation.end();
        return "main";
    }
}
