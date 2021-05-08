package org.example.BloggingProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundResponse> handleContentNotAllowedException(NotFoundException ex) {
        return new ResponseEntity<>(new NotFoundResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestResponse> handleContentNotAllowedException(BadRequestException ex) {
        return new ResponseEntity<>(new BadRequestResponse(ex.isResult(), ex.getErrors()), HttpStatus.BAD_REQUEST);
    }
}
