package Family;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyManagerTest {

    @Test
    void addFamily() {
        FamilyManager familyManager = new FamilyManager(new FamilyRepository());

        assertEquals(0, familyManager.getNumberOfFamiliesInRepository());

        Family family = new Family();

        familyManager.addFamily(family);
        assertEquals(1, familyManager.getNumberOfFamiliesInRepository());
    }

    @Test
    void deleteFamily() {
        FamilyManager familyManager = new FamilyManager(new FamilyRepository());

        Family family = new Family();

        familyManager.addFamily(family);
        assertEquals(1, familyManager.getNumberOfFamiliesInRepository());

        familyManager.deleteFamily(family);
        assertEquals(0, familyManager.getNumberOfFamiliesInRepository());
    }

    @Test
    void addChildToFamily() {
        FamilyManager familyManager = new FamilyManager(new FamilyRepository());

        Family family = new Family();
        familyManager.addFamily(family);

        assertEquals(0, familyManager.getNumberOfChildrenInTheFamily(family));

        Child child = new Child("Tomek", "Kowalski", 7);
        familyManager.addChildToFamily(family, child);

        assertEquals(1, familyManager.getNumberOfChildrenInTheFamily(family));
    }

    @Test
    void deleteChildFromFamily() {
        FamilyManager familyManager = new FamilyManager(new FamilyRepository());

        Family family = new Family();
        familyManager.addFamily(family);

        Child child = new Child("Tomek", "Kowalski", 7);
        familyManager.addChildToFamily(family, child);

        assertEquals(1, familyManager.getNumberOfChildrenInTheFamily(family));

        familyManager.deleteChildFromFamily(family, child);
        assertEquals(0, familyManager.getNumberOfChildrenInTheFamily(family));
    }

    @Test
    void getNumberOfFamiliesInRepository() {
        FamilyManager familyManager = new FamilyManager(new FamilyRepository());

        Family family = new Family();
        familyManager.addFamily(family);

        assertEquals(1, familyManager.getNumberOfFamiliesInRepository());
    }

    @Test
    void getNumberOfChildrenInTheFamily() {
        FamilyManager familyManager = new FamilyManager(new FamilyRepository());

        Family family = new Family();
        familyManager.addFamily(family);

        assertEquals(0, familyManager.getNumberOfChildrenInTheFamily(family));

        Child child = new Child("Tomek", "Kowalski", 7);
        familyManager.addChildToFamily(family, child);

        assertEquals(1, familyManager.getNumberOfChildrenInTheFamily(family));
    }
}