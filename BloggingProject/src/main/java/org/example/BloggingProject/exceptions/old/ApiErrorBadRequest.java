package org.example.BloggingProject.exceptions.old;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorBadRequest {
    private boolean result;
    private Map<String, String> errors;
}
