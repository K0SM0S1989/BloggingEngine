package org.example.BloggingProject.repository;

import org.example.BloggingProject.models.GlobalSettings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalSettingsRepository extends CrudRepository<GlobalSettings, Integer> {
}
