package org.example.BloggingProject.exceptions.old;

import lombok.Data;

import java.util.Map;
@Data
public class BadRequestException extends Exception{
    private boolean result;
    private Map<String, String> errors;


    public static BadRequestException createWith(boolean result, Map<String, String> errors) {
        return new BadRequestException(result, errors);
    }

    public BadRequestException(boolean result, Map<String, String> errors) {

        this.result = result;
        this.errors = errors;
    }



}
