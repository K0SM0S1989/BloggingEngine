package org.example.BloggingProject.requests.comments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentRequest {
    @JsonProperty("parent_id")
    private long parentId;

    @JsonProperty("post_id")
    private long postId;

    private String text;
}
