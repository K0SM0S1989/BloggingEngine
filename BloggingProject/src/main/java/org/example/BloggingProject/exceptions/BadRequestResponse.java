package org.example.BloggingProject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestResponse {
    private boolean result;
    private ErrorsInterface errors;
}
