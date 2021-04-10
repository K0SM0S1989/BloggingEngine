package org.example.BloggingProject.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserForCommentOut {
    private int id;
    private String name;
    private String photo;
}
