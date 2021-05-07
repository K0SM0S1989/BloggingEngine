package org.example.BloggingProject.service;

import org.example.BloggingProject.requests.login.LoginRequest;
import org.example.BloggingProject.response.login_logout.LoginResponse;
import org.example.BloggingProject.response.login_logout.LogoutResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface AuthService {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
    ResponseEntity<LoginResponse> check(Principal principal);
    ResponseEntity<LogoutResponse> logout();

}
