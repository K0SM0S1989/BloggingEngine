package org.example.BloggingProject.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortTextAndTitle implements ErrorsInterface{
    private String text;
    private String title;

    public ShortTextAndTitle(String text, String title) {

        if (text.length() < 50 && text.length() != 0) {
            this.text = "Текст публикации слишком короткий";
        }
        if (title.length() < 3 && title.length() != 0) {
            this.title = "Заголовок публикации слишком короткий";
        }
        if (text.length() == 0) {
            this.text = "Текст не установлен";
        }
        if (title.length() == 0) {
            this.title = "Заголовок не установлен";
        }
    }
}
