package Users;

import Exceptions.UserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void uniqueLogin() {
        Client client = new Client("Dupa");
        assertEquals("Dupa", client.getLogin());

        assertThrows(UserException.class, ()-> new Client("Dupa"));
    }
}