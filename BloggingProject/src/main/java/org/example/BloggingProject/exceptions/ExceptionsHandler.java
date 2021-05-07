package org.example.BloggingProject.exceptions;

import org.example.BloggingProject.exceptions.old.ApiErrorNotFound;
import org.example.BloggingProject.exceptions.old.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundEntity.class)
    public ResponseEntity<NotFoundResponse> handleContentNotAllowedException(NotFoundEntity ex) {
        return new ResponseEntity<>(new NotFoundResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
