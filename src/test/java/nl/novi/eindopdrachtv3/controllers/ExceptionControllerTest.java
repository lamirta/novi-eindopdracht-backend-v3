package nl.novi.eindopdrachtv3.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.novi.eindopdrachtv3.exceptions.UsernameNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ExceptionControllerTest {
    /**
     * Method under test: {@link ExceptionController#exception(UsernameNotFoundException)}
     */
    @Test
    void testException() {
        ExceptionController exceptionController = new ExceptionController();
        ResponseEntity<Object> actualExceptionResult = exceptionController
                .exception(new UsernameNotFoundException("janedoe"));
        assertEquals("Cannot find user janedoe", actualExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualExceptionResult.getStatusCode());
        assertTrue(actualExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ExceptionController#exception(UsernameNotFoundException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "nl.novi.eindopdrachtv3.exceptions.UsernameNotFoundException.getMessage()" because "exception" is null
        //       at nl.novi.eindopdrachtv3.controllers.ExceptionController.exception(ExceptionController.java:27)
        //   In order to prevent exception(UsernameNotFoundException)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   exception(UsernameNotFoundException).
        //   See https://diff.blue/R013 to resolve this issue.

        (new ExceptionController()).exception((UsernameNotFoundException) null);
    }
}

