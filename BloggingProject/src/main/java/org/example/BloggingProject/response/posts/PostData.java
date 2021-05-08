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

    /**
     * очистка текста от тегов и вывод короткого анонса
     * @param text
     */
    public void setAnnounce(String text) {
        String freeHtmlTags = Jsoup.parse(text).text();
        if (text.length() > StaticMethodsAndFields.FINISH_SYMBOL_ANNOUNCE) {
            this.announce = freeHtmlTags.substring(StaticMethodsAndFields.START_SYMBOL_ANNOUNCE,
                    StaticMethodsAndFields.FINISH_SYMBOL_ANNOUNCE) + "...";
        } else this.announce = freeHtmlTags.substring(StaticMethodsAndFields.START_SYMBOL_ANNOUNCE,
                StaticMethodsAndFields.FINISH_SYMBOL_ANNOUNCE / StaticMethodsAndFields.DIVIDE_SHORT_TEXT) +
                "...";
    }

}
