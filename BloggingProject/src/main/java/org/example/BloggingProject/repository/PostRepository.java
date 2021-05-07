package org.example.BloggingProject.repository;

import org.example.BloggingProject.enums.ModerationStatus;
import org.example.BloggingProject.models.Post;
import org.example.BloggingProject.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE is_active = 1 " +
            "AND moderation_status = 'ACCEPTED' " +
            "AND time < ?1")
    Page<Post> findAllPostsByIsActiveAndModerationStatus(LocalDateTime localDateTime, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE is_active = 1 " +
            "AND moderation_status = 'ACCEPTED' " +
            "AND time < ?1 " +
            "AND CONCAT(title, text) ILIKE %?2%")
    Page<Post> findAllPostsByIsActiveAndModerationStatusByQuery(LocalDateTime localDateTime,
                                                                String query,
                                                                Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE is_active = 1 " +
            "AND moderation_status = 'ACCEPTED' " +
            "AND time < ?1 " +
            "AND DATE(time) = ?2")
    Page<Post> findAllPostsByIsActiveAndModerationStatusByDate(LocalDateTime localDateTime,
                                                               LocalDate date,
                                                               Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE is_active = 1 " +
            "AND moderation_status = ?1 " +
            "AND (moderator_id IS NULL " +
            "OR moderator_id = ?2)")
    Page<Post> findAllPostsByIsActiveAndModerationStatusModeration(String status,
                                                                   long moderatorId,
                                                                   Pageable pageable);

    Page<Post> findByUserIdAndIsActive(User user, byte isActive, Pageable pageable);


    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE user_id = ?1 AND is_active = 1 " +
            "AND moderation_status = ?2")
    Page<Post> findByUserIdAndIsActiveAndModerationStatus(long userId,
                                                          String status,
                                                          Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE id = ?1 AND is_active = 1 " +
            "AND moderation_status = 'ACCEPTED' " +
            "AND time < ?2")
    Optional<Post> findByIdAndIsActiveAndModerationStatusAndTime(long id, LocalDateTime localDateTime);

}
