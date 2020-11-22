package com.mycompany.firstapplication.Employment;



import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Exceptions.EmploymentException;
import com.mycompany.firstapplication.Family.Family;

import java.util.ArrayList;
import java.util.List;

public class EmploymentManager {

    private EmploymentRepository employmentRepository;

    public EmploymentManager(EmploymentRepository employmentRepository) {
        this.employmentRepository = employmentRepository;
    }

    public void employBabysitter(Family family, Babysitter babysitter) {
        checkIfBabysitterMeetRequires(babysitter, family.getAgeOfTheYoungestChild(),
                family.getNumberOfChildren());
        checkIfBabysitterIsCurrentlyEmployed(babysitter);

        Employment employment = new Employment(babysitter, family);
        employmentRepository.addElement(employment);
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
        List<Employment> allEmploymentsList = employmentRepository.getElements();

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

    public int numberOfCurrentFamilyEmployment(Family family) {
        List<Employment> allEmploymentsList = employmentRepository.getElements();
        int counter = 0;

        for (Employment employment : allEmploymentsList) {
            if (employment.getFamily() == family &&
                    employment.getEndOfEmployment() == null) {
                counter++;
            }
        }
        return counter;
    }

    public int numberOfEndedFamilyEmployment(Family family) {
        List<Employment> allEmploymentsList = employmentRepository.getElements();
        int counter = 0;

        for (Employment employment : allEmploymentsList) {
            if (employment.getFamily() == family &&
                    employment.getEndOfEmployment() != null) {
                counter++;
            }
        }
        return counter;
    }

    public Employment getActualEmploymentForBabysitter(Babysitter babysitter) {
        List<Employment> allEmploymentsList = employmentRepository.getElements();

        for (Employment employment : allEmploymentsList) {
            if (employment.getBabysitter() == babysitter && employment.getEndOfEmployment() == null) {
                return employment;
            }
        }
        throw new EmploymentException("Babysitter is not already employed");
    }

    public List<Employment> getAllEmploymentsForBabysitter(Babysitter babysitter) {
        List<Employment> allEmploymentsList = employmentRepository.getElements();
        List<Employment> allEmploymentsForBabysitterList = new ArrayList<Employment>();

        for (Employment employment : allEmploymentsList) {
            if (employment.getBabysitter() == babysitter) {
                allEmploymentsForBabysitterList.add(employment);
            }
        }
        return allEmploymentsForBabysitterList;
    }

    public Employment getActualEmploymentForFamily(Family family) {
        List<Employment> allEmploymentsList = employmentRepository.getElements();

        for (Employment employment : allEmploymentsList) {
            if (employment.getFamily() == family && employment.getEndOfEmployment() == null) {
                return employment;
            }
        }
        throw new EmploymentException("Babysitter is not already employed");
    }

    public List<Employment> getAllEmploymentsForFamily(Family family) {
        List<Employment> allEmploymentsList = employmentRepository.getElements();
        List<Employment> allEmploymentsForFamilyList = new ArrayList<Employment>();

        for (Employment employment : allEmploymentsList) {
            if (employment.getFamily() == family) {
                allEmploymentsForFamilyList.add(employment);
            }
        }
        return allEmploymentsForFamilyList;
    }

}
