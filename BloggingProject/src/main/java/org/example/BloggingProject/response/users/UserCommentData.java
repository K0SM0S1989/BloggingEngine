package org.example.BloggingProject.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.BloggingProject.models.User;

@Data
@AllArgsConstructor
public class UserCommentData {
    private int id;
    private String name;
    private String photo;

    public UserCommentData(User user){
        id = user.getId();
        name = user.getName();
        photo = user.getPhoto();
    }
}
