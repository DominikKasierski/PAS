package com.mycompany.firstapplication.Babysitters;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
public class BabysittersManager implements Serializable {

    @Inject
    private BabysittersRepository babysittersRepository;


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

    public void deleteBabysitter(Babysitter babysitter) {
        babysittersRepository.deleteElement(babysitter);
    }

    public Babysitter[] getAllBabysittersArray() {
        return babysittersRepository.getBabysittersList().toArray(new Babysitter[0]);
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
