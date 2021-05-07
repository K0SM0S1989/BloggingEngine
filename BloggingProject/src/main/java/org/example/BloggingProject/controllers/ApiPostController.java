package org.example.BloggingProject.controllers;

import org.example.BloggingProject.exceptions.old.EntityNotFoundException;
import org.example.BloggingProject.response.posts.PostResponse;
import org.example.BloggingProject.response.posts.PostResponseList;
import org.example.BloggingProject.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping(value = "/api/post")
public class ApiPostController {


    private final PostService postService;

    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<PostResponseList> get(@RequestParam int offset,
                                                @RequestParam int limit,
                                                @RequestParam String mode) {
        return postService.get(offset, limit, mode);
    }

    @GetMapping("/search")
    public ResponseEntity<PostResponseList> searchPosts(@RequestParam("query") String query,
                                                        @RequestParam("offset") int offset,
                                                        @RequestParam("limit") int limit) {
        return postService.searchPosts(query, offset, limit);
    }

    @GetMapping("/byDate")
    public ResponseEntity<PostResponseList> getByDate(@RequestParam("date") String date,
                                                      @RequestParam("offset") int offset,
                                                      @RequestParam("limit") int limit) {


        return postService.getByDate(date, offset, limit);
    }

    @GetMapping("/byTag")
    public ResponseEntity<PostResponseList> getByTag(@RequestParam("tag") String tag,
                                                     @RequestParam("offset") int offset,
                                                     @RequestParam("limit") int limit) {
        return postService.getByTag(tag, offset, limit);
    }

    @PreAuthorize(value = "hasAuthority('MODERATOR')")
    @GetMapping("/moderation")
    public ResponseEntity<PostResponseList> getByModeration(Principal principal,
                                                            @RequestParam("offset") int offset,
                                                            @RequestParam("limit") int limit,
                                                            @RequestParam String status) {
        return postService.getForModeration(principal, offset, limit, status);
    }

    @PreAuthorize(value = "hasAuthority('MODERATOR') or hasAuthority('USER')")
    @GetMapping("/my")
    public ResponseEntity<PostResponseList> getMyPosts(Principal principal,
                                                       @RequestParam("offset") int offset,
                                                       @RequestParam("limit") int limit,
                                                       @RequestParam String status) {
        return postService.getMyPosts(principal, offset, limit, status);
    }

    //
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable int id, Principal principal) throws EntityNotFoundException {
        return postService.getPostsById(id, principal);
    }
//
//    // @PreAuthorize(value = "hasAuthority('MODERATOR') or hasAuthority('USER')")
//    @PostMapping
//    public Map<String, Object> addPost(@RequestBody
//                                       @Valid PostDTO postDTO) {
//
//        return postService.addPost(postDTO, user);
//    }
//
//    @PutMapping("/{id}")
//    public Map<String, Object> updatePost(@PathVariable int id,@RequestBody @Valid PostDTO postDTO) throws EntityNotFoundException, BadRequestException {
//
//        return postService.updatePost(id, postDTO, user);
//    }


}
