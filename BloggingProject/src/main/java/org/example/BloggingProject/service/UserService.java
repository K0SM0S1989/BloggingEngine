package org.example.BloggingProject.service;

import org.example.BloggingProject.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
//    UserDetails loadUserByUsername(String name) throws UsernameNotFoundException;

    String addPhoto(User user, MultipartFile file) throws IOException;

}
