package org.example.BloggingProject.repository;

import org.example.BloggingProject.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);

    Optional<User> findUserByEmail(String email);
}
