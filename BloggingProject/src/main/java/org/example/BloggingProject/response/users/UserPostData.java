package org.example.BloggingProject.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BloggingProject.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostData {
    int id;
    String name;

    public UserPostData(User user){
        id = user.getId();
        name = user.getName();
    }
}
