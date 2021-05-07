package org.example.BloggingProject.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BloggingProject.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    private long id;
    private String name;
    private String photo;
    private String email;
    private boolean moderation;
    private int moderationCount;
    private boolean settings;

    public UserLoginResponse(User user) {
        id = user.getId();
        name = user.getName();
        photo = user.getPhoto();
        email = user.getEmail();
        moderation = user.getIsModerator() == 1;
    }
}
