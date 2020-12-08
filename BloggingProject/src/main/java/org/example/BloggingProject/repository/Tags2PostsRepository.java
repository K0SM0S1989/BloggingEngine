package org.example.BloggingProject.repository;

import org.example.BloggingProject.models.Tags2Posts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tags2PostsRepository extends CrudRepository<Tags2Posts, Integer> {
}
