package org.example.BloggingProject.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShortTextAndTitle implements ErrorsData {
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
