package com.mycompany.firstapplication.Babysitters;

import com.mycompany.firstapplication.Users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@ViewScoped
public class BabysittersManager implements Serializable {

    @Inject
    private BabysittersRepository babysittersRepository;

    private List<Babysitter> currentBabysitters;

    private String id;

    private TypeOfBabysitter typeOfBabysitter = TypeOfBabysitter.NORMAL;

//    private int type;

    public BabysittersManager() {
    }

    public BabysittersRepository getBabysittersRepository() {
        return babysittersRepository;
    }

    public BabysittersManager(BabysittersRepository babysittersRepository) {
        this.babysittersRepository = babysittersRepository;
    }

    public void addBabysitter(Babysitter babysitter) {
        babysittersRepository.addElement(babysitter);
    }

    public String deleteBabysitter(Babysitter babysitter) {
        babysittersRepository.deleteElement(babysitter);
        return "BabysitterList";
    }

    public List<Babysitter> getBabysittersList() {
        return babysittersRepository.getBabysittersList();
    }

    public Babysitter[] getAllBabysittersArray() {
        return babysittersRepository.getBabysittersList().toArray(new Babysitter[0]);
    }

    public List<Babysitter> getCurrentBabysitters() {
        return currentBabysitters;
    }

    public int getNumberOfBabysitters() {
        return babysittersRepository.getNumberOfElements();
    }

    public TypeOfBabysitter getTypeOfBabysitter() {
        return typeOfBabysitter;
    }
    //    public int getType() {
//        return type;
//    }

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

    public void valueChanged(ValueChangeEvent event) {
        if (!event.getNewValue().toString().equals("0")) {
            id = event.getNewValue().toString();
            showSelectedBabysitter(id);
        }
    }

    private void showSelectedBabysitter(String id) {
        List<Babysitter> temporaryBabysittersList = new ArrayList<>();
        temporaryBabysittersList.add(babysittersRepository.findByKey(id));
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

    @PostConstruct
    public void initCurrentPersons() {
        typeOfBabysitter = TypeOfBabysitter.NORMAL;
        currentBabysitters = babysittersRepository.getBabysittersList();
    }

}
