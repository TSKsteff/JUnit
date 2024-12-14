package br.com.univali.apiteste.resources.exceptions;

import br.com.univali.apiteste.services.exceptions.DataIntegratyViolationException;
import br.com.univali.apiteste.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {

        ResponseEntity<StandarError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException("Objeto nao Encontrado"),
                        new MockHttpServletRequest()
                );

        assertNotNull(response);
        assertEquals("Objeto nao Encontrado", response.getBody().getError());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getBody().getSattus());

    }

    @Test
    void dataIntegrityViolationException() {

        ResponseEntity<StandarError> response = exceptionHandler
                .DataIntegratyViolationException(
                        new DataIntegratyViolationException("E-mail já cadastrado no sistema"),
                        new MockHttpServletRequest()
                );

        assertNotNull(response);
        assertEquals("E-mail já cadastrado no sistema", response.getBody().getError());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getBody().getSattus());
    }
}