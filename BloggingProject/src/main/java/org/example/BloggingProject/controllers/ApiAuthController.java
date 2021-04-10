package org.example.BloggingProject.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BloggingProject.models.User;
import org.example.BloggingProject.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/")
public class ApiAuthController {
private final UserRepository userRepository;

    public ApiAuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("check")
    public Map<String, Object> getCheck(){
        Map<String, Object> check = new HashMap<>();
        User userFromDb = userRepository.findById(2).orElseThrow();
        check.put("result", true);

//        String photo = "/img/avatars/maxresdefault.jpg";
        CheckUser user = new CheckUser(userFromDb.getId(),
                userFromDb.getName(),
//                photo,
                userFromDb.getPhoto(),
                userFromDb.getEmail(),
                false,
                0, true);
        check.put("user", user);
        return check;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class CheckUser{
        private int id;
        private String name;
        private String photo;
        private String email;
        private boolean moderation;
        private int moderationCount;
        private boolean settings;
    }
}
