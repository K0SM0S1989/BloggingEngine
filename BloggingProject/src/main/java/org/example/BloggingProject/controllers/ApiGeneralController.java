package org.example.BloggingProject.controllers;


import lombok.SneakyThrows;
import org.example.BloggingProject.response.InitResponse;
import org.example.BloggingProject.response.SettingsResponse;
import org.example.BloggingProject.service.GlobalSettingsService;
import org.example.BloggingProject.service.StorageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/api")
public class ApiGeneralController {

    private final StorageService storageService;
    private final InitResponse initResponse;
    private final GlobalSettingsService globalSettingsService;

    public ApiGeneralController(StorageService storageService, InitResponse initResponse, GlobalSettingsService globalSettingsService) {
        this.storageService = storageService;
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

    @PreAuthorize(value = "hasAuthority('MODERATOR') or hasAuthority('USER')")
    @SneakyThrows
    @PostMapping("/image")
    public String addPhoto(@RequestParam MultipartFile image) {
        return storageService.addPhoto(image);
    }


}
