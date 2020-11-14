package Employment;

import Babysitters.Babysitter;
import Family.Child;
import Family.Family;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class EmploymentRepositoryTest {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Test
    void getNumberOfElements() {
        EmploymentRepository employmentRepository = new EmploymentRepository();

        assertEquals(0, employmentRepository.getElements().size());
        assertEquals(employmentRepository.getElements().size(), employmentRepository.getNumberOfElements());

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        employmentRepository.addElement(employment);

        assertEquals(1, employmentRepository.getElements().size());
        assertEquals(employmentRepository.getElements().size(), employmentRepository.getNumberOfElements());
    }

    @Test
    void checkIfTheElementIsPresent() {
        EmploymentRepository employmentRepository = new EmploymentRepository();

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        employmentRepository.addElement(employment);

        assertTrue(employmentRepository.checkIfTheElementIsPresent(employment));
    }

    @Test
    void addElement() {
        EmploymentRepository employmentRepository = new EmploymentRepository();

        assertEquals(0, employmentRepository.getNumberOfElements());

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        employmentRepository.addElement(employment);

        assertEquals(1, employmentRepository.getNumberOfElements());
    }

    @Test
    void deleteElement() {
        EmploymentRepository employmentRepository = new EmploymentRepository();

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        employmentRepository.addElement(employment);

        assertEquals(1, employmentRepository.getNumberOfElements());

        employmentRepository.deleteElement(employment);

        assertEquals(0, employmentRepository.getNumberOfElements());
    }

    @Test
    void getElements() {
        EmploymentRepository employmentRepository = new EmploymentRepository();

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        employmentRepository.addElement(employment);

        List<Employment> employmentList = new ArrayList<>();
        employmentList.add(employment);

        assertEquals(employmentList, employmentRepository.getElements());
    }

    @Test
    void testToString() {
        EmploymentRepository employmentRepository = new EmploymentRepository();

        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        employmentRepository.addElement(employment);

        log.info(employmentRepository.toString());
    }
}