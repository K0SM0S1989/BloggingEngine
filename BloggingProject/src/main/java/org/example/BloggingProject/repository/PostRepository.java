package org.example.BloggingProject.repository;

import org.example.BloggingProject.enums.ModerationStatus;
import org.example.BloggingProject.models.Post;
import org.example.BloggingProject.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Page<Post> findAllByModeratorIdAndModerationStatusAndIsActive(User ModeratorId,
                                                                  Pageable pageable,
                                                                  ModerationStatus status,
                                                                  byte isActive);

    Page<Post> findAllByUserIdAndIsActive(User userId,
                                          Pageable pageable,
                                          byte isActive);

    Page<Post> findAllByUserIdAndIsActiveAndModerationStatus(User userId,
                                                             Pageable pageable,
                                                             byte isActive,
                                                             ModerationStatus status);

    Page<Post> findAll(Pageable pageable);


}
