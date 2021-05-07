package org.example.BloggingProject.response.posts;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponseList {
    private int count;
    private List<PostData> posts;
}
