package org.example.BloggingProject.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostsOut {
    private int count;
    private List<AllPost> posts;
}
