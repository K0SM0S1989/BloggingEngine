package org.example.BloggingProject.serv;

import org.example.BloggingProject.exceptions.BadRequestException;
import org.example.BloggingProject.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
 String addPhoto(User user, MultipartFile file) throws IOException, BadRequestException;

}
