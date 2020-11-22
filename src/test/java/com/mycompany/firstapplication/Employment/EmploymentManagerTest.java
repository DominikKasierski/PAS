package com.mycompany.firstapplication.Employment;


import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Exceptions.EmploymentException;
import com.mycompany.firstapplication.Family.Child;
import com.mycompany.firstapplication.Family.Family;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmploymentManagerTest {

    @Test
    void employBabysitter_CorrectCase() {
        EmploymentManager employmentManager = new EmploymentManager(new EmploymentRepository());

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        assertEquals(0, employmentManager.numberOfCurrentFamilyEmployment(family));

        employmentManager.employBabysitter(family, babysitter);
        assertEquals(1, employmentManager.numberOfCurrentFamilyEmployment(family));
    }

    @Test
    void employBabysitter_IncorrectCase_BabysitterEmployed() {
        EmploymentManager employmentManager = new EmploymentManager(new EmploymentRepository());

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family1 = new Family();
        Child child1 = new Child("Tomek", "Kowalski", 4);
        family1.addChild(child1);

        employmentManager.employBabysitter(family1, babysitter);

        Family family2 = new Family();
        Child child2 = new Child("Albert", "Kowalski", 4);
        family2.addChild(child2);

        assertThrows(EmploymentException.class,
                () -> employmentManager.employBabysitter(family2, babysitter));
    }

    @Test
    void employBabysitter_IncorrectCase_BabysitterDoesNotMeetRequires() {
        EmploymentManager employmentManager = new EmploymentManager(new EmploymentRepository());

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 2);
        family.addChild(child);

        assertThrows(EmploymentException.class,
                () -> employmentManager.employBabysitter(family, babysitter));
    }

    @Test
    void endEmployment_CorrectCase() {
        EmploymentManager employmentManager = new EmploymentManager(new EmploymentRepository());

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        assertEquals(0, employmentManager.numberOfCurrentFamilyEmployment(family));

        employmentManager.employBabysitter(family, babysitter);

        assertEquals(1, employmentManager.numberOfCurrentFamilyEmployment(family));

        employmentManager
                .endEmployment(employmentManager.getActualEmploymentForBabysitter(babysitter));

        assertEquals(0, employmentManager.numberOfCurrentFamilyEmployment(family));
    }

    @Test
    void endEmployment_IncorrectCase_TryingToEndEndedEmployment() {
        EmploymentManager employmentManager = new EmploymentManager(new EmploymentRepository());

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        assertEquals(0, employmentManager.numberOfCurrentFamilyEmployment(family));

        employmentManager.employBabysitter(family, babysitter);

        assertEquals(1, employmentManager.numberOfCurrentFamilyEmployment(family));

        employmentManager
                .endEmployment(employmentManager.getActualEmploymentForBabysitter(babysitter));

        assertThrows(EmploymentException.class, () -> employmentManager
                .endEmployment(employmentManager.getActualEmploymentForBabysitter(babysitter)));
    }

    @Test
    void numberOfCurrentAndEndedFamilyEmployment() {
        EmploymentManager employmentManager = new EmploymentManager(new EmploymentRepository());

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        assertEquals(0, employmentManager.numberOfCurrentFamilyEmployment(family));
        assertEquals(0, employmentManager.numberOfEndedFamilyEmployment(family));

        employmentManager.employBabysitter(family, babysitter);

        assertEquals(1, employmentManager.numberOfCurrentFamilyEmployment(family));
        assertEquals(0, employmentManager.numberOfEndedFamilyEmployment(family));

        employmentManager
                .endEmployment(employmentManager.getActualEmploymentForBabysitter(babysitter));

        assertEquals(0, employmentManager.numberOfCurrentFamilyEmployment(family));
        assertEquals(1, employmentManager.numberOfEndedFamilyEmployment(family));
    }
}