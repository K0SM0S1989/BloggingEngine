package org.example.BloggingProject.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentOut {

    public CommentOut() {
    }

    private int id;
    private long timeSpent;
    private String text;
    private UserForCommentOut user;

}
