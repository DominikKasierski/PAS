package Family;

import Exceptions.FamilyException;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Test
    void addChild() {
        Family family = new Family();
        Child child = new Child("Szymon", "Dubowski", 6);

        assertEquals(0, family.getChildren().size());

        family.addChild(child);
        assertEquals(1, family.getChildren().size());
    }

    @Test
    void deleteChild() {
        Family family = new Family();
        Child child = new Child("Szymon", "Dubowski", 6);
        family.addChild(child);

        assertEquals(1, family.getChildren().size());

        family.deleteChild(child);
        assertEquals(0, family.getChildren().size());
    }

    @Test
    void numberOfChildren() {
        Family family = new Family();
        Child child = new Child("Szymon", "Dubowski", 6);
        family.addChild(child);

        assertEquals(family.numberOfChildren(), family.getChildren().size());
        assertEquals(1, family.numberOfChildren());
    }

    @Test
    void getAgeOfTheYoungestChild() {
        Family family = new Family();

        Child child1 = new Child("Szymon", "Dubowski", 6);
        family.addChild(child1);

        Child child2 = new Child("Szymon", "Dubowski", 3);
        family.addChild(child2);

        Child child3 = new Child("Szymon", "Dubowski", 9);
        family.addChild(child3);

        assertEquals(3, family.getAgeOfTheYoungestChild());
    }

    @Test
    void getAgeOfTheYoungestChild_EmptyList() {
        Family family = new Family();
        assertEquals(0, family.numberOfChildren());
        assertThrows(FamilyException.class, family::getAgeOfTheYoungestChild);
    }

    @Test
    void getAgeOfTheYoungestChild_childrenWithTheSameAge() {
        Family family = new Family();

        Child child1 = new Child("Szymon", "Dubowski", 6);
        family.addChild(child1);

        Child child2 = new Child("Szymon", "Dubowski", 6);
        family.addChild(child2);

        Child child3 = new Child("Szymon", "Dubowski", 6);
        family.addChild(child3);

        assertEquals(6, family.getAgeOfTheYoungestChild());
    }


    @Test
    void testToString() {
        Family family = new Family();
        Child child = new Child("Szymon", "Dubowski", 6);
        family.addChild(child);

        log.info(family.toString());
    }
}