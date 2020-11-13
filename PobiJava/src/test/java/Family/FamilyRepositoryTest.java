package Family;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class FamilyRepositoryTest {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Test
    void getNumberOfFamiliesInRepository() {
        FamilyRepository repository = new FamilyRepository();

        assertEquals(0, repository.getFamiliesList().size());
        assertEquals(repository.getFamiliesList().size(), repository.getNumberOfFamiliesInRepository());

        Family family = new Family();
        repository.addFamilyToRepository(family);

        assertEquals(1, repository.getFamiliesList().size());
        assertEquals(repository.getFamiliesList().size(), repository.getNumberOfFamiliesInRepository());
    }

    @Test
    void addFamilyToRepository() {
        FamilyRepository repository = new FamilyRepository();

        assertEquals(0, repository.getNumberOfFamiliesInRepository());

        Family family = new Family();
        repository.addFamilyToRepository(family);

        assertEquals(1, repository.getNumberOfFamiliesInRepository());
    }

    @Test
    void checkIfFamilyIsInRepository() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        repository.addFamilyToRepository(family);

        assertTrue(repository.checkIfFamilyIsInRepository(family));
    }

    @Test
    void deleteFamilyFromRepository() {
        FamilyRepository repository = new FamilyRepository();

        assertEquals(0, repository.getNumberOfFamiliesInRepository());

        Family family = new Family();
        repository.addFamilyToRepository(family);

        assertEquals(1, repository.getNumberOfFamiliesInRepository());

        repository.deleteFamilyFromRepository(family);

        assertEquals(1, repository.getNumberOfFamiliesInRepository());
    }

    @Test
    void getFamiliesList() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        repository.addFamilyToRepository(family);

        List<Family> familyList = new ArrayList<>();
        familyList.add(family);

        assertEquals(familyList, repository.getFamiliesList());
    }

    @Test
    void getNumberOfChildrenInTheFamily() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        Child child = new Child("Szymon", "Dubowski", 13);
        family.addChild(child);
        repository.addFamilyToRepository(family);

        assertEquals(1, repository.getNumberOfChildrenInTheFamily(family));
    }

    @Test
    void addChildToFamily() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        repository.addFamilyToRepository(family);

        assertEquals(0, repository.getNumberOfChildrenInTheFamily(family));

        Child child = new Child("Szymon", "Dubowski", 13);
        repository.addChildToFamily(family, child);

        assertEquals(1, repository.getNumberOfChildrenInTheFamily(family));
    }

    @Test
    void deleteChildFromFamily() {
        FamilyRepository repository = new FamilyRepository();

        Family family = new Family();
        repository.addFamilyToRepository(family);

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
        repository.addFamilyToRepository(family1);
        Child child1 = new Child("Jan", "Dubowski", 13);
        repository.addChildToFamily(family1, child1);

        Family family2 = new Family();
        repository.addFamilyToRepository(family2);
        Child child2 = new Child("Jacek", "Dubowski", 17);
        repository.addChildToFamily(family2, child2);

        Family family3 = new Family();
        repository.addFamilyToRepository(family3);
        Child child3 = new Child("Jacek", "Dubowski", 19);
        repository.addChildToFamily(family3, child3);

        log.info(repository.toString());
    }
}