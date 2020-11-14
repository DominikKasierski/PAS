package Babysitters;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class TidingSitterTest {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Test
    void getValueOfCleaningEquipment() {
        TidingSitter babysitter = new TidingSitter("Ola", "Nowak", 20, 3, 4, 450);

        assertEquals(450, babysitter.getValueOfCleaningEquipment());
    }

    @Test
    void priceForHour() {
        TidingSitter babysitter = new TidingSitter("Ola", "Nowak", 20, 3, 4, 450);
        double priceForHour = babysitter.getBasePriceForHour() * (1 + babysitter.getValueOfCleaningEquipment() / 500.0);

        assertEquals(priceForHour, babysitter.priceForHour());
    }

    @Test
    void testToString() {
        Babysitter babysitter = new TidingSitter("Ola", "Nowak", 20, 3, 4, 450);
        log.config(babysitter.toString());
    }
}