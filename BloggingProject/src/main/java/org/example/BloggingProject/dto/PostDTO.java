package org.example.BloggingProject.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDTO {
    private long timestamp;
    private int active;
    private String title;
    private List<String> tags;
    private String text;
}
