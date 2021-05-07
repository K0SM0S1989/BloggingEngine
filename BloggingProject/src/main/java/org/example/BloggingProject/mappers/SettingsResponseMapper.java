package org.example.BloggingProject.mappers;

import org.example.BloggingProject.models.GlobalSettings;
import org.example.BloggingProject.response.SettingsResponse;

public class SettingsResponseMapper {
    public static SettingsResponse map(Iterable<GlobalSettings> globalSettings) {

        SettingsResponse settingsResponse = new SettingsResponse();
        globalSettings.forEach(globSet -> {
            boolean value = false;
            if (globSet.getValue().equals("YES")) value = true;
            switch (globSet.getCode()) {
                case "MULTIUSER_MODE":
                    settingsResponse.setMultiuserMode(value);
                    break;
                case "POST_PREMODERATION":
                    settingsResponse.setPostPremoderation(value);
                    break;
                case "STATISTICS_IS_PUBLIC":
                    settingsResponse.setStatisticsIsPublic(value);
                    break;
            }
        });

        return settingsResponse;
    }
}
