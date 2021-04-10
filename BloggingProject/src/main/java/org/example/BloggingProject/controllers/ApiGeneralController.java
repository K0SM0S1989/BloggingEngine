package org.example.BloggingProject.controllers;


import lombok.SneakyThrows;
import org.example.BloggingProject.exceptions.BadRequestException;
import org.example.BloggingProject.models.User;
import org.example.BloggingProject.repository.UserRepository;
import org.example.BloggingProject.response.InitResponse;
import org.example.BloggingProject.response.SettingsResponse;
import org.example.BloggingProject.serv.GlobalSettingsService;
import org.example.BloggingProject.serv.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/api")
public class ApiGeneralController {

    //security прикрутишь

    private final UserService userService;
    private final InitResponse initResponse;
    private final GlobalSettingsService globalSettingsService;

    public ApiGeneralController(UserService userService, InitResponse initResponse, GlobalSettingsService globalSettingsService) {

        this.userService = userService;
        this.initResponse = initResponse;
        this.globalSettingsService = globalSettingsService;
    }


    @GetMapping("/init")
    public InitResponse getInit() {
        return initResponse;
    }

    @GetMapping("/settings")
    public SettingsResponse getSettings() {
        return globalSettingsService.getGlobalSettings();
    }

    @GetMapping("/tag")
    public InitResponse getTags() {
        return null;
    }

//    @SneakyThrows
//    @PostMapping("/image")
//    public String addPhoto(@RequestParam MultipartFile image) throws BadRequestException {
//
//
//        return userService.addPhoto(user, image);
//    }




//    private User createUserOrMod(int i) {
//        User user;
//        if (i == 1) {
//            user = userRepository.findById(1).orElseThrow();
//        } else user = userRepository.findById(2).orElseThrow();
//        return user;
//    }
}
