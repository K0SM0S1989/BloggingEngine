package org.example.BloggingProject.response.posts;

import lombok.Data;
import org.example.BloggingProject.main.StaticMethodsAndFields;
import org.example.BloggingProject.response.users.UserPostData;
import org.jsoup.Jsoup;


@Data
public class PostData {
    int id;
    long timestamp;
    UserPostData user;
    String title;
    String announce;
    int likeCount;
    int dislikeCount;
    int commentCount;
    int viewCount;


    public void setAnnounce(String text) {

//        String freeHtmlTags = Html.fromHtml(text).toString();
//        int start = (int)"${announce.start}";
        String freeHtmlTags = Jsoup.parse(text).text();
//        System.out.println(startTextForAnnounce);
//        System.out.println(finish);
//        System.out.println(divideText);


        if (text.length() > StaticMethodsAndFields.FINISH_SYMBOL_ANNOUNCE) {
            this.announce = text.substring(StaticMethodsAndFields.START_SYMBOL_ANNOUNCE,
                    StaticMethodsAndFields.FINISH_SYMBOL_ANNOUNCE) + "...";
        } else this.announce = text.substring(StaticMethodsAndFields.START_SYMBOL_ANNOUNCE,
                StaticMethodsAndFields.FINISH_SYMBOL_ANNOUNCE / StaticMethodsAndFields.DIVIDE_SHORT_TEXT) +
                "...";
    }

}
