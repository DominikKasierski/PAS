package Family;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class FamilyRepositoryTest {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Test
    void getNumberOfElements() {
        FamilyRepository repository = new FamilyRepository();

        assertEquals(0, repository.getElements().size());
        assertEquals(repository.getElements().size(), repository.getNumberOfElements());

        Family family = new Family();
        repository.addElement(family);

        assertEquals(1, repository.getElements().size());
        assertEquals(repository.getElements().size(), repository.getNumberOfElements());
    }

    @Test
    void addElement() {
        FamilyRepository repository = new FamilyRepository();

        assertEquals(0, repository.getNumberOfElements());

        Family family = new Family();
        repository.addElement(family);

        assertEquals(1, repository.getNumberOfElements());
    }

    @Test
    void checkIfTheElementIsPresent() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        repository.addElement(family);

        assertTrue(repository.checkIfTheElementIsPresent(family));
    }

    @Test
    void deleteElement() {
        FamilyRepository repository = new FamilyRepository();

        assertEquals(0, repository.getNumberOfElements());

        Family family = new Family();
        repository.addElement(family);

        assertEquals(1, repository.getNumberOfElements());

        repository.deleteElement(family);

        assertEquals(0, repository.getNumberOfElements());
    }

    @Test
    void getElements() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        repository.addElement(family);

        List<Family> familyList = new ArrayList<>();
        familyList.add(family);

        assertEquals(familyList, repository.getElements());
    }

    @Test
    void getNumberOfChildrenInTheFamily() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        Child child = new Child("Szymon", "Dubowski", 13);
        family.addChild(child);
        repository.addElement(family);

        assertEquals(1, repository.getNumberOfChildrenInTheFamily(family));
    }

    @Test
    void addChildToFamily() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        repository.addElement(family);

        assertEquals(0, repository.getNumberOfChildrenInTheFamily(family));

        Child child = new Child("Szymon", "Dubowski", 13);
        repository.addChildToFamily(family, child);

        assertEquals(1, repository.getNumberOfChildrenInTheFamily(family));
    }

    @Test
    void deleteChildFromFamily() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        repository.addElement(family);

        Child child = new Child("Szymon", "Dubowski", 13);
        repository.addChildToFamily(family, child);

        assertEquals(1, repository.getNumberOfChildrenInTheFamily(family));

        repository.deleteChildFromFamily(family, child);

        assertEquals(0, repository.getNumberOfChildrenInTheFamily(family));
    }

    @Test
    void testToString() {
        FamilyRepository repository = new FamilyRepository();

        Family family1 = new Family();
        repository.addElement(family1);
        Child child1 = new Child("Jan", "Dubowski", 13);
        repository.addChildToFamily(family1, child1);

        Family family2 = new Family();
        repository.addElement(family2);
        Child child2 = new Child("Jacek", "Dubowski", 17);
        repository.addChildToFamily(family2, child2);

        Family family3 = new Family();
        repository.addElement(family3);
        Child child3 = new Child("Wojtek", "Dubowski", 19);
        repository.addChildToFamily(family3, child3);

        log.config(repository.toString());
    }
}