package org.example.BloggingProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorNotFound> handleContentNotAllowedException(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ApiErrorNotFound(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorBadRequest> handleContentBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(new ApiErrorBadRequest(false,ex.getErrors()), HttpStatus.BAD_REQUEST);
    }
}
