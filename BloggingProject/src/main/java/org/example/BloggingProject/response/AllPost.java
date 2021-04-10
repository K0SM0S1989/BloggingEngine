package org.example.BloggingProject.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AllPost {
    public AllPost() {
    }

    int id;
    long timespent;
    UserForAllPostOut user;
    String title;
    String announce;
    int likeCount;
    int dislikeCount;
    int commentCount;
    int viewCount;


    public void setAnnounce(String text) {
        String[] textArray = text.split(" ");
        StringBuilder builder = new StringBuilder();
        int k = 10;
        if (textArray.length <= 10) {
            k = textArray.length;
        }
        for (int i = 0; i < k; i++) {
            builder.append(textArray[i] + " ");
        }
        builder.append(" ...");
        this.announce = builder.toString();
    }
}
