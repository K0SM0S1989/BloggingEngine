package org.example.BloggingProject.exceptions.old;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;
@EqualsAndHashCode(callSuper = true)
@Data
public class BadRequestException extends Exception{
    private boolean result;
    private Map<String, String> errors;

    public BadRequestException(boolean result, Map<String, String> errors) {
        this.result = result;
        this.errors = errors;
    }
}
