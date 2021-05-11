package org.example.BloggingProject.mappers;

import org.example.BloggingProject.enums.ModerationStatus;
import org.example.BloggingProject.main.StaticMethodsAndFields;
import org.example.BloggingProject.requests.posts.PostRequest;
import org.example.BloggingProject.models.Post;

import org.example.BloggingProject.models.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PostRequestMap {
    public static Post map(PostRequest postRequest, User user) {
        Post post = new Post();
        post.setIsActive((byte) postRequest.getActive());
        post.setUserId(user);
        post.setText(postRequest.getText());
        post.setTitle(postRequest.getTitle());
        post.setModerationStatus(ModerationStatus.NEW);
        post.setTime(Instant.
                ofEpochMilli(postRequest.getTimestamp() * StaticMethodsAndFields.MILLISECOND_TO_SECOND).
                atZone(ZoneId.of("UTC")).toLocalDateTime().plusHours(3));
        post.setViewCount(0);
        return post;
    }

    public static void updatePost(Post post, PostRequest postRequest, User user) {
        LocalDateTime postRequestTime = Instant.
                ofEpochMilli(postRequest.getTimestamp() * StaticMethodsAndFields.MILLISECOND_TO_SECOND).
                atZone(ZoneId.of("UTC")).toLocalDateTime().plusHours(3);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime newPostTime = post.getTime().isBefore(currentTime) ? currentTime : postRequestTime;
        post.setTime(newPostTime);
        if (user.getIsModerator() == 0) {
            post.setModerationStatus(ModerationStatus.NEW);
        }
        post.setIsActive((byte) postRequest.getActive());
        post.setText(postRequest.getText());
        post.setTitle(postRequest.getTitle());
    }


}
