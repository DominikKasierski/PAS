package com.mycompany.firstapplication.Babysitters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
public class BabysittersManager implements Serializable {

    @Inject
    private BabysittersRepository babysittersRepository;

    private List<Babysitter> currentBabysitters;

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


    public List<Babysitter> getAllBabysitters() {
        return currentBabysitters;
    }

    public Babysitter[] getAllBabysittersArray() {
        Babysitter[] babysittersArray = new Babysitter[getAllBabysitters().size()];
        getAllBabysitters().toArray(babysittersArray);
        return babysittersArray;
//        return currentBabysitters.toArray(Babysitter[]::new);
    }

    public void valueChanged(ValueChangeEvent event) {
        System.out.println("klik");
    }

    @PostConstruct
    public void initCurrentPersons() {
        currentBabysitters = babysittersRepository.getElements();
    }

}
