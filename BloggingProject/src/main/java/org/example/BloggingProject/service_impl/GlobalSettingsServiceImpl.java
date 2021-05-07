package org.example.BloggingProject.service_impl;

import org.example.BloggingProject.mappers.SettingsResponseMapper;
import org.example.BloggingProject.models.GlobalSettings;
import org.example.BloggingProject.repository.GlobalSettingsRepository;
import org.example.BloggingProject.response.SettingsResponse;
import org.example.BloggingProject.service.GlobalSettingsService;
import org.springframework.stereotype.Service;

@Service
public class GlobalSettingsServiceImpl implements GlobalSettingsService {
    private final GlobalSettingsRepository globalSettingsRepository;

    public GlobalSettingsServiceImpl(GlobalSettingsRepository globalSettingsRepository) {
        this.globalSettingsRepository = globalSettingsRepository;
    }

    @Override
    public SettingsResponse getGlobalSettings() {
        Iterable<GlobalSettings> globalSettings = globalSettingsRepository.findAll();

        return SettingsResponseMapper.map(globalSettings);
    }
}
