package com.mycompany.firstapplication.Employment;

import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Exceptions.EmploymentException;
import com.mycompany.firstapplication.Users.Client;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
public class EmploymentsManager implements Serializable {

    @Inject
    private EmploymentsRepository employmentsRepository;

    public EmploymentsManager(EmploymentsRepository employmentsRepository) {
        this.employmentsRepository = employmentsRepository;
    }

    public EmploymentsManager() {
    }

    public void employBabysitter(Client client, Babysitter babysitter) {
        checkIfBabysitterMeetRequires(babysitter, client.getAgeOfTheYoungestChild(),
                client.getNumberOfChildren());
        checkIfBabysitterIsCurrentlyEmployed(babysitter);

        Employment employment = new Employment(babysitter, client);
        employmentsRepository.addElement(employment);
    }

    private void checkIfBabysitterMeetRequires(Babysitter babysitter, int minAge,
                                               int numberOfChildren) {
        if (babysitter.getMinChildAge() > minAge) {
            throw new EmploymentException("Babysitter does not meet requirements");
        }
        if (babysitter.getMaxNumberOfChildrenInTheFamily() < numberOfChildren) {
            throw new EmploymentException("Babysitter does not meet requirements");
        }
    }

    private void checkIfBabysitterIsCurrentlyEmployed(Babysitter babysitter) {
        List<Employment> allEmploymentsList = employmentsRepository.getElements();

        for (Employment employment : allEmploymentsList) {
            if (employment.getBabysitter() == babysitter &&
                    employment.getEndOfEmployment() == null) {
                throw new EmploymentException("Babysitter already employed");
            }
        }
    }

    public void endEmployment(Employment employment) {
        if (employment.getEndOfEmployment() != null) {
            throw new EmploymentException("Employment already ended");
        }
        employment.endEmployment();
    }

    public int numberOfCurrentClientEmployment(Client client) {
        List<Employment> allEmploymentsList = employmentsRepository.getElements();
        int counter = 0;

        for (Employment employment : allEmploymentsList) {
            if (employment.getClient() == client &&
                    employment.getEndOfEmployment() == null) {
                counter++;
            }
        }
        return counter;
    }

    public int numberOfEndedClientEmployment(Client client) {
        List<Employment> allEmploymentsList = employmentsRepository.getElements();
        int counter = 0;

        for (Employment employment : allEmploymentsList) {
            if (employment.getClient() == client &&
                    employment.getEndOfEmployment() != null) {
                counter++;
            }
        }
        return counter;
    }

    public Employment getActualEmploymentForBabysitter(Babysitter babysitter) {
        List<Employment> allEmploymentsList = employmentsRepository.getElements();

        for (Employment employment : allEmploymentsList) {
            if (employment.getBabysitter() == babysitter &&
                    employment.getEndOfEmployment() == null) {
                return employment;
            }
        }
        throw new EmploymentException("Babysitter is not already employed");
    }

    public List<Employment> getAllEmploymentsForBabysitter(Babysitter babysitter) {
        List<Employment> allEmploymentsList = employmentsRepository.getElements();
        List<Employment> allEmploymentsForBabysitterList = new ArrayList<Employment>();

        for (Employment employment : allEmploymentsList) {
            if (employment.getBabysitter() == babysitter) {
                allEmploymentsForBabysitterList.add(employment);
            }
        }
        return allEmploymentsForBabysitterList;
    }

    public Employment getActualEmploymentForClient(Client client) {
        List<Employment> allEmploymentsList = employmentsRepository.getElements();

        for (Employment employment : allEmploymentsList) {
            if (employment.getClient() == client && employment.getEndOfEmployment() == null) {
                return employment;
            }
        }
        throw new EmploymentException("Client has not any actual employment");
    }

    public List<Employment> getAllEmploymentsForClient(Client client) {
        List<Employment> allEmploymentsList = employmentsRepository.getElements();
        List<Employment> allEmploymentsForFamilyList = new ArrayList<Employment>();

        for (Employment employment : allEmploymentsList) {
            if (employment.getClient() == client) {
                allEmploymentsForFamilyList.add(employment);
            }
        }
        return allEmploymentsForFamilyList;
    }

}
