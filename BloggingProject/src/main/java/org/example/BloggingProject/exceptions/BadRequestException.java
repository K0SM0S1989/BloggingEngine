package org.example.BloggingProject.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BadRequestException extends Exception {
    private boolean result;
    private ErrorsInterface errors;


    public BadRequestException(String text, String title) {
        result = false;
        errors = new ShortTextAndTitle(text, title);
    }

    public BadRequestException(String imageErrorMessage) {
        result = false;
        errors = new ImageError(imageErrorMessage);
    }
}
