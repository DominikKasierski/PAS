package com.mycompany.firstapplication.Babysitters;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ApplicationScoped
@Named
public class BabysitterListController {
    @Inject
    private BabysittersRepository babysittersRepository;

    private List<Babysitter> currentBabysitter;

    public List<Babysitter> getAllBabysitter() {
        return currentBabysitter;
    }

    @PostConstruct
    public void initCurrentBabysitter() {
        currentBabysitter = babysittersRepository.getElements();
    }
}
