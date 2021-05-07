package org.example.BloggingProject.mappers;

import org.example.BloggingProject.main.StaticMethodsAndFields;
import org.example.BloggingProject.models.Post;
import org.example.BloggingProject.models.User;
import org.example.BloggingProject.response.posts.PostData;
import org.example.BloggingProject.response.users.UserPostData;

import java.sql.Date;
import java.time.ZoneId;

public class PostResponseListMap {

    public static PostData map(Post post) {
        final int[] likeCount = {0};
        final int[] dislikeCount = {0};
        PostData allPost = new PostData();
        allPost.setId(post.getId());
        allPost.setTimestamp(Date.from
                (post.getTime().atZone(ZoneId.systemDefault()).toInstant())
                .getTime()/1000);
        User user = post.getUserId();
        UserPostData userForAllPostOut = new UserPostData(user.getId(), user.getName());

        allPost.setUser(userForAllPostOut);
        allPost.setTitle(post.getTitle());
        allPost.setAnnounce(post.getText());

        StaticMethodsAndFields.likeDisCount(post, likeCount, dislikeCount);

        allPost.setLikeCount(likeCount[0]);
        allPost.setDislikeCount(dislikeCount[0]);
        allPost.setCommentCount(post.getPostCommentList().size());
        allPost.setViewCount(post.getViewCount());
        return allPost;
    }

}
