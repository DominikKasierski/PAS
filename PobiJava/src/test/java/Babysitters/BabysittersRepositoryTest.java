package Babysitters;

import Family.Family;
import Family.FamilyRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class BabysittersRepositoryTest {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Test
    void getNumberOfElements() {
        BabysittersRepository babysittersRepository = new BabysittersRepository();

        assertEquals(0, babysittersRepository.getElements().size());
        assertEquals(babysittersRepository.getElements().size(), babysittersRepository.getNumberOfElements());

        Babysitter babysitter = new TeachingSitter("Ola", "Nowak", 20, 3, 4, 13);
        babysittersRepository.addElement(babysitter);

        assertEquals(1, babysittersRepository.getElements().size());
        assertEquals(babysittersRepository.getElements().size(), babysittersRepository.getNumberOfElements());
    }

    @Test
    void checkIfTheElementIsPresent() {
        BabysittersRepository babysittersRepository = new BabysittersRepository();

        Babysitter babysitter = new TidingSitter("Ola", "Nowak", 20, 3, 4, 560);
        babysittersRepository.addElement(babysitter);

        assertTrue(babysittersRepository.checkIfTheElementIsPresent(babysitter));
    }

    @Test
    void addElement() {
        BabysittersRepository babysittersRepository = new BabysittersRepository();

        assertEquals(0, babysittersRepository.getNumberOfElements());

        Babysitter babysitter = new Babysitter("Ola", "Nowak", 20, 3, 4);
        babysittersRepository.addElement(babysitter);

        assertEquals(1, babysittersRepository.getNumberOfElements());
    }

    @Test
    void deleteElement() {
        BabysittersRepository babysittersRepository = new BabysittersRepository();

        Babysitter babysitter = new TeachingSitter("Ola", "Nowak", 20, 3, 4, 13);
        babysittersRepository.addElement(babysitter);

        assertEquals(1, babysittersRepository.getNumberOfElements());

        babysittersRepository.deleteElement(babysitter);

        assertEquals(0, babysittersRepository.getNumberOfElements());
    }

    @Test
    void getElements() {
        BabysittersRepository babysittersRepository = new BabysittersRepository();

        Babysitter babysitter = new Babysitter("Ola", "Nowak", 20, 3, 4);
        babysittersRepository.addElement(babysitter);

        List<Babysitter> babysitterList = new ArrayList<>();
        babysitterList.add(babysitter);

        assertEquals(babysitterList, babysittersRepository.getElements());
    }

    @Test
    void testToString() {
        BabysittersRepository babysittersRepository = new BabysittersRepository();

        Babysitter babysitter1 = new Babysitter("Ola", "Nowak", 20, 3, 4);
        babysittersRepository.addElement(babysitter1);

        Babysitter babysitter2 = new TeachingSitter("Anna", "Kowalska", 15, 5, 2, 5);
        babysittersRepository.addElement(babysitter2);

        Babysitter babysitter3 = new TidingSitter("Kasia", "Parkowska", 11, 10, 2, 560);
        babysittersRepository.addElement(babysitter3);

        log.info(babysittersRepository.toString());
    }
}