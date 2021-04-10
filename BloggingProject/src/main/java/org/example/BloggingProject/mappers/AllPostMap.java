package org.example.BloggingProject.mappers;

import org.example.BloggingProject.response.AllPost;
import org.example.BloggingProject.response.UserForAllPostOut;
import org.example.BloggingProject.models.Post;
import org.example.BloggingProject.models.PostVote;
import org.example.BloggingProject.models.User;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class AllPostMap {
    public static AllPost map(Post post) {
        LocalDateTime postTime = post.getTime().minusHours(3);
        final int[] likeCount = {0};
        final int[] dislikeCount = {0};
        AllPost allPost = new AllPost();
        allPost.setId(post.getId());
        allPost.setTimespent(Timestamp.valueOf(postTime).getTime());

        User user = post.getUserId();
        UserForAllPostOut userForAllPostOut = new UserForAllPostOut(user.getId(), user.getName());

        allPost.setUser(userForAllPostOut);
        allPost.setTitle(post.getTitle());
        allPost.setAnnounce(post.getText());

        List<PostVote> postVoteList = post.getPostVoteList();
        postVoteList.forEach(postVote -> {
            if (postVote.getValue() == 1) {
                likeCount[0]++;
            } else dislikeCount[0]++;
        });

        allPost.setLikeCount(likeCount[0]);
        allPost.setDislikeCount(dislikeCount[0]);
        allPost.setCommentCount(post.getPostCommentList().size());
        allPost.setViewCount(post.getViewCount());
        return allPost;
    }

}
