package org.example.BloggingProject.mappers;

import org.example.BloggingProject.models.GlobalSettings;
import org.example.BloggingProject.response.SettingsResponse;

public class SettingsResponseMapper {
    public static SettingsResponse map(Iterable<GlobalSettings> globalSettings) {

        SettingsResponse settingsResponse = new SettingsResponse();
        globalSettings.forEach(globSet -> {
            boolean value = false;
            if (globSet.getValue().equals("YES")) value = true;
            if (globSet.getCode().equals("MULTIUSER_MODE")) {
                settingsResponse.setMultiuserMode(value);
            } else if (globSet.getCode().equals("POST_PREMODERATION")) {
                settingsResponse.setPostPremoderation(value);
            } else if (globSet.getCode().equals("STATISTICS_IS_PUBLIC")) {
                settingsResponse.setStatisticsIsPublic(value);
            }
        });

        return settingsResponse;
    }
}
