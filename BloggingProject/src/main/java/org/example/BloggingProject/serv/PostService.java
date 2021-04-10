package org.example.BloggingProject.serv;

import org.example.BloggingProject.dto.PostDTO;
import org.example.BloggingProject.exceptions.BadRequestException;
import org.example.BloggingProject.exceptions.EntityNotFoundException;
import org.example.BloggingProject.response.PostsOut;
import org.example.BloggingProject.enums.ModerationStatus;
import org.example.BloggingProject.models.User;

import java.time.LocalDate;
import java.util.Map;


public interface PostService {

    PostsOut get(int offset, int limit, String mode);

    PostsOut get(int offset, int limit);

    PostsOut getByQuery(String query, int offset, int limit);

    PostsOut getByDate(LocalDate date, int offset, int limit);

    PostsOut getByTag(String tag, int offset, int limit);

    PostsOut getForModeration(User user, int offset, int limit, ModerationStatus status);

    PostsOut getForMyPosts(User user, int offset, int limit, String status);

    Map<String, Object> getPostsById(int id, User user) throws EntityNotFoundException;

    Map<String, Object> addPost(PostDTO postDTO, User user);

    Map<String, Object> updatePost(int id,PostDTO postDTO, User user) throws EntityNotFoundException, BadRequestException;



}
