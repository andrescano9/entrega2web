package co.javeriana.edu.ProyectoTransmilleno.advices;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.javeriana.edu.ProyectoTransmilleno.dto.ErrorDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<ErrorDTO> elementNotFound(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO("Resource does not exist"));
    }
}
