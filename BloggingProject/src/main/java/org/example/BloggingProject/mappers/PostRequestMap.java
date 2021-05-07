package org.example.BloggingProject.mappers;

import org.example.BloggingProject.enums.ModerationStatus;
import org.example.BloggingProject.main.StaticMethodsAndFields;
import org.example.BloggingProject.requests.posts.PostRequest;
import org.example.BloggingProject.models.Post;

import org.example.BloggingProject.models.User;

import java.time.Instant;
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


}
