package org.example.BloggingProject.mappers;

import org.example.BloggingProject.response.CommentOut;
import org.example.BloggingProject.response.UserForCommentOut;
import org.example.BloggingProject.models.PostComment;
import org.example.BloggingProject.models.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentOutMap {
    public static CommentOut map(PostComment postComment){
        LocalDateTime commentTime = postComment.getTime().minusHours(3);

        CommentOut commentOut = new CommentOut();

        commentOut.setId(postComment.getId());
        commentOut.setTimeSpent(Timestamp.valueOf(commentTime).getTime());
        commentOut.setText(postComment.getText());
        User user = postComment.getUserId();
        commentOut.setUser(new UserForCommentOut(user.getId(), user.getName(), user.getPhoto()));

        return commentOut;
    }
}
