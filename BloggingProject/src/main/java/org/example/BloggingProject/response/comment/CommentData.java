package org.example.BloggingProject.response.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BloggingProject.main.StaticMethodsAndFields;
import org.example.BloggingProject.models.PostComment;
import org.example.BloggingProject.response.users.UserCommentData;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentData {

    private int id;

    private long timestamp;

    private String text;

    private UserCommentData user;

    public CommentData(PostComment postComment) {
        id = postComment.getId();
        timestamp = Timestamp.valueOf(postComment.getTime()).getTime() / StaticMethodsAndFields.MILLISECOND_TO_SECOND;
        text = postComment.getText();
        user = new UserCommentData(postComment.getUserId());
    }

}
