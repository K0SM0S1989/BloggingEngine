package org.example.BloggingProject.mappers;

import org.example.BloggingProject.dto.PostDTO;
import org.example.BloggingProject.enums.ModerationStatus;
import org.example.BloggingProject.models.Post;
import org.example.BloggingProject.models.User;

import java.time.Instant;
import java.time.ZoneId;

public class PostDTOMap {
    public static Post map(PostDTO postDTO, User user) {
        Post post = new Post();

        post.setIsActive((byte) postDTO.getActive());
        post.setUserId(user);
        post.setText(postDTO.getText());
        post.setTitle(postDTO.getTitle());
        post.setModerationStatus(ModerationStatus.NEW);
        post.setTime(Instant.ofEpochMilli(postDTO.getTimestamp()).atZone(ZoneId.of("UTC")).toLocalDateTime());
        post.setViewCount(0);

        return post;
    }


}
