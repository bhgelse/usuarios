package com.gelse.usuarios.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratandoErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(EntityNotFoundException ex){
        String errorMessage = "El recurso no fue encontrado: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException ex){
        var errorMessage = ex.getFieldErrors().stream().map(DatosErroresValidacion::new).toList();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    public record DatosErroresValidacion(String campo, String message) {
        public DatosErroresValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
