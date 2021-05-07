package org.example.BloggingProject.service_impl;


import org.example.BloggingProject.models.User;
import org.example.BloggingProject.repository.UserRepository;
import org.example.BloggingProject.requests.login.LoginRequest;
import org.example.BloggingProject.response.login_logout.LoginResponse;
import org.example.BloggingProject.response.login_logout.LogoutResponse;
import org.example.BloggingProject.response.users.UserLoginResponse;
import org.example.BloggingProject.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        User userModel = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с email " +
                        loginRequest.getEmail() + " не зарегистрирован"));
        return ResponseEntity.ok(new LoginResponse(true, new UserLoginResponse(userModel)));
    }

    @Override
    public ResponseEntity<LoginResponse> check(Principal principal) {
        if (principal != null){
            User user = userRepository.findUserByEmail(principal.getName()).orElseThrow();
            return ResponseEntity.ok(new LoginResponse(true, new UserLoginResponse(user)));
        }else return ResponseEntity.ok(new LoginResponse(false, null));
   }

    @Override
    public ResponseEntity<LogoutResponse> logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok(new LogoutResponse(true));
    }
}
