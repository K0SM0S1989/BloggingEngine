package org.example.BloggingProject.controllers;

import org.example.BloggingProject.requests.login.LoginRequest;
import org.example.BloggingProject.response.login_logout.LoginResponse;
import org.example.BloggingProject.response.login_logout.LogoutResponse;
import org.example.BloggingProject.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/auth")
public class ApiAuthController {

    private final AuthService authService;

    public ApiAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/logout")
    public ResponseEntity<LogoutResponse> logout() {
        return authService.logout();
    }

    @GetMapping("/check")
    public ResponseEntity<LoginResponse> getCheck(Principal principal) {
        return authService.check(principal);
    }

}
