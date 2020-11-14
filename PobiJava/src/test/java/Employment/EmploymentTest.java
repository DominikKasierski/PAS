package Employment;

import Babysitters.Babysitter;
import Babysitters.TeachingSitter;
import Babysitters.TidingSitter;
import Exceptions.EmploymentException;
import Family.Child;
import Family.Family;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class EmploymentTest {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Test
    void endEmployment() {
        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        assertNull(employment.getEndOfEmployment());

        employment.endEmployment();

        assertNotNull(employment.getEndOfEmployment());
    }

    @Test
    void isEnded() {
        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        assertFalse(employment.isEnded());

        employment.endEmployment();

        assertTrue(employment.isEnded());
    }

    @Test
    void employmentDurationInHours() {
        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 20, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);
        employment.endEmployment();

        //Zmiana w Employment na potrzeby testów
        assertEquals(2, employment.employmentDurationInHours());
    }

    @Test
    void employmentCost_Case1() {
        Babysitter babysitter = new Babysitter("Anna", "Kowalska", 15, 4, 5);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        assertThrows(EmploymentException.class, employment::employmentCost);

        employment.endEmployment();

        assertEquals(30, employment.employmentCost());
    }

    @Test
    void employmentCost_Case2() {
        Babysitter babysitter = new TeachingSitter("Anna", "Kowalska", 10, 4, 5, 10);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        assertThrows(EmploymentException.class, employment::employmentCost);

        employment.endEmployment();

        assertEquals(40, employment.employmentCost());
    }

    @Test
    void employmentCost_Case3() {
        Babysitter babysitter = new TidingSitter("Anna", "Kowalska", 20, 4, 5, 500);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);

        assertThrows(EmploymentException.class, employment::employmentCost);

        employment.endEmployment();

        assertEquals(80, employment.employmentCost());
    }

    @Test
    void testToString() {

        Babysitter babysitter = new TidingSitter("Anna", "Kowalska", 20, 4, 5, 500);

        Family family = new Family();
        Child child = new Child("Tomek", "Kowalski", 4);
        family.addChild(child);

        Employment employment = new Employment(babysitter, family);
        employment.endEmployment();

        log.info(employment.toString());
    }
}