package org.example.BloggingProject.service;

import org.example.BloggingProject.exceptions.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String addPhoto(MultipartFile image) throws BadRequestException, IOException;
}
