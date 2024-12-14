package br.com.univali.apiteste.resources.exceptions;

import br.com.univali.apiteste.services.exceptions.DataIntegratyViolationException;
import br.com.univali.apiteste.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    public ResponseEntity<StandarError>objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
        StandarError error = new StandarError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    public ResponseEntity<StandarError>DataIntegratyViolationException(DataIntegratyViolationException ex, HttpServletRequest request){
        StandarError error = new StandarError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
