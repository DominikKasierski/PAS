package com.mycompany.firstapplication.Babysitters;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ConversationScoped
@Named
public class BabysittersManager implements Serializable {

    public BabysittersManager() {
    }

    @Inject
    private BabysittersRepository babysittersRepository;

    @Inject
    private Conversation conversation;

    private final Babysitter newBabysitter = new Babysitter();
    private final TeachingSitter newTeachingSitter = new TeachingSitter();
    private final TidingSitter newTidingSitter = new TidingSitter();

    public Babysitter getNewBabysitter() {
        return newBabysitter;
    }

    public TeachingSitter getNewTeachingSitter() {
        return newTeachingSitter;
    }

    public TidingSitter getNewTidingSitter() {
        return newTidingSitter;
    }

    public BabysittersRepository getBabysittersRepository() {
        return babysittersRepository;
    }

    //TODO:Sprobowac uproscic
    public String processNewBabysitter() {
        conversation.begin();
        return "NewBabysitterConfirm";
    }

    public String processNewTeachingSitter() {
        conversation.begin();
        return "NewTeachingSitterConfirm";
    }

    public String processNewTidingSitter() {
        conversation.begin();
        return "NewTidingSitterConfirm";
    }

    public String confirmNewBabysitter() {
        addBabysitter(newBabysitter);
        conversation.end();
        return "main";
    }

    public String confirmNewTeachingSitter() {
        addBabysitter(newTeachingSitter);
        conversation.end();
        return "main";
    }

    public String confirmNewTidingSitter() {
        addBabysitter(newTidingSitter);
        conversation.end();
        return "main";
    }

    public BabysittersManager(BabysittersRepository babysittersRepository) {
        this.babysittersRepository = babysittersRepository;
    }

    public void addBabysitter(Babysitter babysitter) {
        babysittersRepository.addElement(babysitter);
    }

    public void deleteBabysitter(Babysitter babysitter) {
        babysittersRepository.deleteElement(babysitter);
    }

    public int getNumberOfBabysitters() {
        return babysittersRepository.getNumberOfElements();
    }

    public List<Babysitter> getListWithAppropriateBabysitters(int minAge, int numberOfChildren) {

        List<Babysitter> allBabysittersInRepository = babysittersRepository.getElements();
        List<Babysitter> appropriateBabysitters = new ArrayList<>();

        for (Babysitter babysitter : allBabysittersInRepository) {
            if (babysitter.getMinChildAge() <= minAge &&
                    babysitter.getMaxNumberOfChildrenInTheFamily() >= numberOfChildren) {
                appropriateBabysitters.add(babysitter);
            }
        }

        return appropriateBabysitters;
    }

}
