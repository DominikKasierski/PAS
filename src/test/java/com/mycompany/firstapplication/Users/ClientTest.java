package com.mycompany.firstapplication.Users;

import com.mycompany.firstapplication.Exceptions.UserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void uniqueLogin() {
        Client client = new Client("Samsung");
        assertEquals("Samsung", client.getLogin());

        assertThrows(UserException.class, ()-> new Client("Samsung"));
    }
}