package org.example.BloggingProject.repository;

import org.example.BloggingProject.models.PostVote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostVoteRepository extends CrudRepository<PostVote, Integer> {
}
