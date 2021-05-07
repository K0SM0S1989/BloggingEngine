package org.example.BloggingProject.main;

import org.example.BloggingProject.models.Post;
import org.example.BloggingProject.models.PostVote;

import java.util.List;

public class StaticMethodsAndFields {
public static final int START_SYMBOL_ANNOUNCE = 0;
public static final int FINISH_SYMBOL_ANNOUNCE = 150;
public static final int DIVIDE_SHORT_TEXT = 3;


    public static void likeDisCount(Post post, int[] likeCount, int[] dislikeCount){
        List<PostVote> postVoteList = post.getPostVoteList();
        postVoteList.forEach(postVote -> {
            if (postVote.getValue() == 1) {
                likeCount[0]++;
            } else dislikeCount[0]++;
        });
    }


}
