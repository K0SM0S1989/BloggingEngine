package org.example.BloggingProject.exceptions.old;

import java.util.HashMap;
import java.util.Map;

public class AddPostError {


    public static Map<String, String> errorFail(String text, String title) {
        Map<String, String> textOfErrors = new HashMap<>();
        if (text.length() < 50 && text.length() != 0) {
            textOfErrors.put("text", "Текст публикации слишком короткий");
        }
        if (title.length() < 3 && title.length() != 0) {
            textOfErrors.put("title", "Заголовок публикации слишком короткий");
        }
        if (text.length() == 0) {
            textOfErrors.put("text", "Текст не установлен");
        }
        if (title.length() == 0) {
            textOfErrors.put("title", "Заголовок не установлен");
        }
        return textOfErrors;
    }


}
