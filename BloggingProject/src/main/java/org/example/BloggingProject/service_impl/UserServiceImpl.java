package org.example.BloggingProject.service_impl;


import org.example.BloggingProject.models.User;
import org.example.BloggingProject.repository.UserRepository;
import org.example.BloggingProject.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    @Value("${upload.path}")
    private String uploadPath;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        return (UserDetails) userRepository.findByName(name);
//    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User blogUser = userRepository.findUserByEmail(email).orElseThrow(()->new UsernameNotFoundException("Person with email: " + email + " not found"));
//        return new BlogUserDetails(blogUser);
//    }

//    @Override
//    public String addPhoto(User user, MultipartFile file) throws IOException, BadRequestException {
//
//
//        if (file != null) {
//            int point = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf('.') + 1;
//            String fileFormat = file.getOriginalFilename().substring(point);
//            long weight = file.getSize();
//
//            if (fileFormat.equals("png") || fileFormat.equals("jpg") || fileFormat.equals("jpeg")) {
//                if (weight <= 2097152) {
//                    String uuidFile = UUID.randomUUID().toString();
//                    String finalPathForPhoto = pathGenerate(uuidFile);
//                    File uploadDir = new File(uploadPath + "/" + finalPathForPhoto);
//                    uploadDir.mkdirs();
//
//                    File newFile = new File(uploadPath + "/" + finalPathForPhoto + "/" + file.getOriginalFilename());
//                    file.transferTo(newFile);
//                    String userPhotoAddress = "/img/" + finalPathForPhoto + "/" + file.getOriginalFilename();
//
//                    user.setPhoto(userPhotoAddress);
//                    userRepository.save(user);
//
//                    return userPhotoAddress;
//                } else {
//                    Map<String, String> errors = new HashMap<>();
//                    errors.put("image", "Размер файла превышает допустимый");
//                    throw BadRequestException.createWith(false, errors);
//                }
//
//
//            } else {
//                Map<String, String> errors = new HashMap<>();
//                errors.put("image", "Неверный формат файла");
//                throw BadRequestException.createWith(false, errors);
//            }
//
//        } else {
//            Map<String, String> errors = new HashMap<>();
//            errors.put("image", "Файл не загружен");
//            throw BadRequestException.createWith(false, errors);
//        }
//    }

    private String pathGenerate(String uuidFile) {

        String firstDir;
        String secondDir;
        String thirdDir;
        firstDir = uuidFile.substring(0, uuidFile.length() / 4);
        secondDir = uuidFile.substring(uuidFile.length() / 4, uuidFile.length() / 2);
        thirdDir = uuidFile.substring(uuidFile.length() / 2, uuidFile.length() * 3 / 4);
        return firstDir + "/" + secondDir + "/" + thirdDir;
    }


    @Override
    public String addPhoto(User user, MultipartFile file) throws IOException {
        return null;
    }
}
