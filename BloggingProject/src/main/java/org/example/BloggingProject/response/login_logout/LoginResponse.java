package org.example.BloggingProject.response.login_logout;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.BloggingProject.response.users.UserLoginResponse;

@Data
@AllArgsConstructor
public class LoginResponse {
    private boolean result;
    private UserLoginResponse user;

    public LoginResponse() {
        this.result = true;
    }
}
