package nl.novi.eindopdrachtv3.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.novi.eindopdrachtv3.exceptions.UsernameNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


class ExceptionControllerTest {

    @Test
    void testException() {
        ExceptionController exceptionController = new ExceptionController();
        ResponseEntity<Object> actualExceptionResult = exceptionController
                .exception(new UsernameNotFoundException("janedoe"));
        assertEquals("Cannot find user janedoe", actualExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualExceptionResult.getStatusCode());
        assertTrue(actualExceptionResult.getHeaders().isEmpty());
    }

}

