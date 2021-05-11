package org.example.BloggingProject.service_impl;

import org.example.BloggingProject.exceptions.BadRequestException;
import org.example.BloggingProject.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${weight.file.max}")
    private long maxWeight;

    @Override
    public String addPhoto(MultipartFile image) throws BadRequestException, IOException {
        if (!image.isEmpty()) {
            int point = Objects.requireNonNull(image.getOriginalFilename()).lastIndexOf('.') + 1;
            String fileFormat = image.getOriginalFilename().substring(point);
            long weight = image.getSize();
            if (fileFormat.equals("png") || fileFormat.equals("jpg") || fileFormat.equals("jpeg")) {
                if (weight <= maxWeight) {
                    String uuidFile = UUID.randomUUID().toString();
                    String finalPathForPhoto = pathGenerate(uuidFile);
                    File uploadDir = new File(uploadPath + "/" + finalPathForPhoto);
                    uploadDir.mkdirs();

                    File newFile = new File(uploadPath + "/" + finalPathForPhoto + "/" + image.getOriginalFilename());
                    image.transferTo(newFile);
                    return "/image/" + finalPathForPhoto + "/" + image.getOriginalFilename();
                } else {
                    throw new BadRequestException("Размер файла превышает допустимый");
                }

            } else {
                throw new BadRequestException("Неверный формат");
            }

        } else {
            throw new BadRequestException("Файл не загружен");
        }

    }


    private String pathGenerate(String uuidFile) {
        return uuidFile.substring(0, uuidFile.length() / 4) +
                "/" +
                uuidFile.substring(uuidFile.length() / 4, uuidFile.length() / 2) +
                "/" +
                uuidFile.substring(uuidFile.length() / 2, uuidFile.length() * 3 / 4);
    }
}
