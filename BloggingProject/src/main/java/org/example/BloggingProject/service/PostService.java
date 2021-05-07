package org.example.BloggingProject.service;

import org.example.BloggingProject.requests.posts.PostRequest;
import org.example.BloggingProject.exceptions.old.BadRequestException;
import org.example.BloggingProject.exceptions.old.EntityNotFoundException;
import org.example.BloggingProject.models.User;
import org.example.BloggingProject.response.posts.PostResponse;
import org.example.BloggingProject.response.posts.PostResponseList;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.Map;


public interface PostService {

    ResponseEntity<PostResponseList> get(int offset, int limit, String mode);

    ResponseEntity<PostResponseList> searchPosts(String query, int offset, int limit);

    ResponseEntity<PostResponseList> getByDate(String date, int offset, int limit);

    ResponseEntity<PostResponseList> getByTag(String tag, int offset, int limit);

    ResponseEntity<PostResponseList> getForModeration(Principal principal, int offset, int limit, String status);

    ResponseEntity<PostResponseList> getMyPosts(Principal principal, int offset, int limit, String status);

    ResponseEntity<PostResponse> getPostsById(int id, Principal principal) throws EntityNotFoundException;

    Map<String, Object> addPost(PostRequest postRequest, User user);

    Map<String, Object> updatePost(int id, PostRequest postRequest, User user) throws EntityNotFoundException, BadRequestException;



}
