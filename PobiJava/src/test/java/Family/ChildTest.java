package Family;

import Exceptions.FamilyException;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ChildTest {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Test
    void constructor() {
        assertThrows(FamilyException.class, () -> new Child("Szymon", "Dubowski", -4));
    }

    @Test
    void getAge() {
        Child child = new Child("Szymon", "Dubowski", 6);
        assertEquals(6, child.getAge());
    }

    @Test
    void testToString() {
        Child child = new Child("Szymon", "Dubowski", 6);
        log.config(child.toString());
    }
}