package org.example.BloggingProject.service;

import org.example.BloggingProject.exceptions.BadRequestException;
import org.example.BloggingProject.exceptions.NotFoundException;
import org.example.BloggingProject.requests.posts.PostRequest;
import org.example.BloggingProject.response.PositiveResultResponse;
import org.example.BloggingProject.response.posts.PostResponse;
import org.example.BloggingProject.response.posts.PostResponseList;
import org.springframework.http.ResponseEntity;

import java.security.Principal;


public interface PostService {

    ResponseEntity<PostResponseList> get(int offset, int limit, String mode);

    ResponseEntity<PostResponseList> searchPosts(String query, int offset, int limit);

    ResponseEntity<PostResponseList> getByDate(String date, int offset, int limit);

    ResponseEntity<PostResponseList> getByTag(String tag, int offset, int limit) throws NotFoundException;

    ResponseEntity<PostResponseList> getForModeration(Principal principal, int offset, int limit, String status);

    ResponseEntity<PostResponseList> getMyPosts(Principal principal, int offset, int limit, String status);

    ResponseEntity<PostResponse> getPostsById(int id, Principal principal) throws NotFoundException;

    ResponseEntity<PositiveResultResponse> addPost(PostRequest postRequest, Principal principal) throws BadRequestException;

    ResponseEntity<PositiveResultResponse> updatePost(int id, PostRequest postRequest, Principal principal) throws NotFoundException, BadRequestException;



}
