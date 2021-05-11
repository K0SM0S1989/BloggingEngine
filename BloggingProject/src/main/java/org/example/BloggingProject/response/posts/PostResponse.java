package org.example.BloggingProject.response.posts;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BloggingProject.main.StaticMethodsAndFields;
import org.example.BloggingProject.models.Post;
import org.example.BloggingProject.response.comment.CommentData;
import org.example.BloggingProject.response.users.UserPostData;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostResponse {
    private long id;
    private long timestamp;
    private boolean active;
    private UserPostData user;
    private String title;
    private String text;
    private int likeCount;
    private int dislikeCount;
    private int viewCount;
    private List<CommentData> comments;
    private List<String> tags;

    public PostResponse(Post post) {
        final int[] likeCount = {0};
        final int[] dislikeCount = {0};
        id = post.getId();
        timestamp = Timestamp.valueOf(post.getTime()).getTime() / StaticMethodsAndFields.MILLISECOND_TO_SECOND;
        active = post.getIsActive() == 1;
        user = new UserPostData(post.getUserId());
        title = post.getTitle();
        text = post.getText();
        StaticMethodsAndFields.likeDisCount(post, likeCount, dislikeCount);
        this.likeCount = likeCount[0];
        this.dislikeCount = dislikeCount[0];
        viewCount = post.getViewCount();
        comments = post.getPostCommentList().stream().map(CommentData::new).collect(Collectors.toList());
        tags = post.getTags2PostsList().stream().map(tags2Posts -> tags2Posts.getTagId().getName()).collect(Collectors.toList());


    }

}
