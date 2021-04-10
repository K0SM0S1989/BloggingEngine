package org.example.BloggingProject.controllers;

import org.example.BloggingProject.dto.PostDTO;
import org.example.BloggingProject.exceptions.BadRequestException;
import org.example.BloggingProject.exceptions.EntityNotFoundException;
import org.example.BloggingProject.response.PostsOut;
import org.example.BloggingProject.enums.ModerationStatus;
import org.example.BloggingProject.models.User;
import org.example.BloggingProject.repository.UserRepository;
import org.example.BloggingProject.serv.PostService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/post")
public class ApiPostController {

    @Autowired
    private UserRepository userRepository;//не забыть удалить!!!!!!!!!!!!!!

    private final PostService postService;

    public ApiPostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping
    public PostsOut get(@RequestParam int offset, @RequestParam int limit, @RequestParam String mode) {
        return postService.get(offset, limit, mode);
    }


    @GetMapping("/search")
    public PostsOut getByQuery(@RequestParam("query") String query,
                               @RequestParam("offset") int offset,
                               @RequestParam("limit") int limit) {
        if (query.isEmpty()) {
            return postService.get(offset, limit);
        } else return postService.getByQuery(query, offset, limit);
    }

    @GetMapping("/byDate")
    public PostsOut getByDate(@RequestParam("date") String date,
                              @RequestParam("offset") int offset,
                              @RequestParam("limit") int limit) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dtf);
        return postService.getByDate(localDate, offset, limit);
    }

    @GetMapping("/byTag")
    public PostsOut getByTag(@RequestParam("tag") String tag,
                             @RequestParam("offset") int offset,
                             @RequestParam("limit") int limit) {
        return postService.getByTag(tag, offset, limit);
    }

    // @PreAuthorize(value = "hasAuthority('MODERATOR')")
    @GetMapping("/moderation")
    public PostsOut getByModeration(
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit,
            @RequestParam ModerationStatus status) {
        User user = createUserOrMod(1);

        return postService.getForModeration(user, offset, limit, status);
    }

    // @PreAuthorize(value = "hasAuthority('MODERATOR') or hasAuthority('USER')")
    @GetMapping("/my")
    public PostsOut getByMyPosts(
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit,
            @RequestParam String status) {

        User user = createUserOrMod(2);

        return postService.getForMyPosts(user, offset, limit, status);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPostById(@PathVariable int id) throws EntityNotFoundException {
        User user = createUserOrMod(0);
        return postService.getPostsById(id, user);
    }

    // @PreAuthorize(value = "hasAuthority('MODERATOR') or hasAuthority('USER')")
    @PostMapping
    public Map<String, Object> addPost(@RequestBody
                                       @Valid PostDTO postDTO) {
        User user = createUserOrMod(1);
        return postService.addPost(postDTO, user);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updatePost(@PathVariable int id,@RequestBody @Valid PostDTO postDTO) throws EntityNotFoundException, BadRequestException {
        User user = createUserOrMod(1);
        return postService.updatePost(id, postDTO, user);
    }


    /**
     * НЕ ЗАБУДЬ УДАЛИТЬ ЭТОТ МЕТОД И ПРИКРУТИТЬ SECURITY!!!!!!!!!!!!!!!!!!!!
     *
     * @param i
     * @return
     */

    private User createUserOrMod(int i) {
        User user;
        if (i == 1) {
            user = userRepository.findById(1).orElseThrow();
        } else user = userRepository.findById(2).orElseThrow();
        return user;
    }
}
