package org.example.BloggingProject.repository;

import org.example.BloggingProject.models.Tag;
import org.example.BloggingProject.models.Tags2Posts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Tags2PostsRepository extends CrudRepository<Tags2Posts, Integer> {
    List<Tags2Posts> findAllByTagId(Tag tag);
}
