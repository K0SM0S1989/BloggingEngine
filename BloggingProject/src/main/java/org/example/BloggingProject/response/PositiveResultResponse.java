package org.example.BloggingProject.response;

import lombok.Data;

@Data
public class PositiveResultResponse {
    private boolean result;

    public PositiveResultResponse(){
        this.result = true;
    }
}
