package org.example.BloggingProject.service_impl;


import org.example.BloggingProject.enums.ModerationStatus;
import org.example.BloggingProject.exceptions.NotFoundEntity;
import org.example.BloggingProject.exceptions.old.BadRequestException;
import org.example.BloggingProject.exceptions.old.EntityNotFoundException;
import org.example.BloggingProject.mappers.PostRequestMap;
import org.example.BloggingProject.mappers.PostResponseListMap;
import org.example.BloggingProject.models.Post;
import org.example.BloggingProject.models.Tag;
import org.example.BloggingProject.models.Tags2Posts;
import org.example.BloggingProject.models.User;
import org.example.BloggingProject.repository.PostRepository;
import org.example.BloggingProject.repository.TagRepository;
import org.example.BloggingProject.repository.Tags2PostsRepository;
import org.example.BloggingProject.repository.UserRepository;
import org.example.BloggingProject.requests.posts.PostRequest;
import org.example.BloggingProject.response.PositiveResultResponse;
import org.example.BloggingProject.response.posts.PostData;
import org.example.BloggingProject.response.posts.PostResponse;
import org.example.BloggingProject.response.posts.PostResponseList;
import org.example.BloggingProject.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Value("${text.notfoundexception}")
    private String messageNotFound;

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final Tags2PostsRepository tags2PostsRepository;

    public PostServiceImpl(PostRepository postRepository, TagRepository tagRepository, UserRepository userRepository, Tags2PostsRepository tags2PostsRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.tags2PostsRepository = tags2PostsRepository;
    }

    @Override
    public ResponseEntity<PostResponseList> get(int offset, int limit, String mode) {
        Page<Post> postList = postRepository.findAllPostsByIsActiveAndModerationStatus(LocalDateTime.now(), PageRequest.of(offset, limit));
        List<PostData> postOutList = new ArrayList<>();
        postList.forEach(post -> postOutList.add(PostResponseListMap.map(post)));
        switch (mode) {
            case "recent":
                postOutList.sort(Comparator.comparing(PostData::getTimestamp).reversed());
                break;
            case "popular":
                postOutList.sort(Comparator.comparing(PostData::getCommentCount).reversed());
                break;
            case "best":
                postOutList.sort(Comparator.comparing(PostData::getLikeCount).reversed());
                break;
            case "early":
                postOutList.sort(Comparator.comparing(PostData::getTimestamp));
                break;
        }
        return ResponseEntity.ok(new PostResponseList(postOutList.size(), postOutList));
    }


    @Override
    public ResponseEntity<PostResponseList> searchPosts(String query, int offset, int limit) {
        Page<Post> postList = postRepository
                .findAllPostsByIsActiveAndModerationStatusByQuery(LocalDateTime.now(),
                        query,
                        PageRequest.of(offset, limit));
        List<PostData> postOutList = new ArrayList<>();
        postList.forEach(post -> postOutList.add(PostResponseListMap.map(post)));
        return ResponseEntity.ok(new PostResponseList(postOutList.size(), postOutList));
    }

    @Override
    public ResponseEntity<PostResponseList> getByDate(String date, int offset, int limit) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dtf);
        Page<Post> postList = postRepository.
                findAllPostsByIsActiveAndModerationStatusByDate(LocalDateTime.now(),
                        localDate,
                        PageRequest.of(offset, limit));
        List<PostData> postOutList = new ArrayList<>();
        postList.forEach(post -> postOutList.add(PostResponseListMap.map(post)));
        return ResponseEntity.ok(new PostResponseList(postOutList.size(), postOutList));
    }

    @Override
    public ResponseEntity<PostResponseList> getByTag(String tagName, int offset, int limit) throws NotFoundEntity {
        Tag tag = tagRepository.findByName(tagName).orElseThrow(() -> new NotFoundEntity(messageNotFound));
        List<PostData> postOutList = new ArrayList<>();
        if (tag != null) {
            List<Tags2Posts> tags2PostsList = tag.getTags2PostsList();
            tags2PostsList.forEach(tags2Posts -> {
                if (isCorrect(tags2Posts.getPostId())) {
                    postOutList.add(PostResponseListMap.map(tags2Posts.getPostId()));
                }
            });
        }
        return ResponseEntity.ok(new PostResponseList(postOutList.size(), postOutList));
    }

    @Override
    public ResponseEntity<PostResponseList> getForModeration(Principal principal,
                                                             int offset,
                                                             int limit,
                                                             String status) {
        User moder = userRepository.findUserByEmail(principal.getName()).orElseThrow();
        Page<Post> postList = postRepository.findAllPostsByIsActiveAndModerationStatusModeration(status.toUpperCase(),
                moder.getId(),
                PageRequest.of(offset, limit));
        List<PostData> postOutList = new ArrayList<>();
        postList.forEach(post -> postOutList.add(PostResponseListMap.map(post)));
        return ResponseEntity.ok(new PostResponseList(postOutList.size(), postOutList));
    }

    @Override
    public ResponseEntity<PostResponseList> getMyPosts(Principal principal, int offset, int limit, String status) {
        User user = userRepository.findUserByEmail(principal.getName()).orElseThrow();
        List<PostData> postOutList = new ArrayList<>();
        byte isActive = 0;
        switch (status) {
            case "inactive":
                Page<Post> postListInAct = postRepository.findByUserIdAndIsActive(user,
                        isActive,
                        PageRequest.of(offset, limit));
                postListInAct.forEach(post -> postOutList.add(PostResponseListMap.map(post)));
                break;
            case "pending":
                Page<Post> postListPend = postRepository.findByUserIdAndIsActiveAndModerationStatus(user.getId(),
                        ModerationStatus.NEW.toString(),
                        PageRequest.of(offset, limit));
                postListPend.forEach(post -> postOutList.add(PostResponseListMap.map(post)));
                break;
            case "declined":
                Page<Post> postListDecl = postRepository.findByUserIdAndIsActiveAndModerationStatus(user.getId(),
                        ModerationStatus.DECLINED.toString(),
                        PageRequest.of(offset, limit));
                postListDecl.forEach(post -> postOutList.add(PostResponseListMap.map(post)));
                break;
            case "published":
                Page<Post> postListPubl = postRepository.findByUserIdAndIsActiveAndModerationStatus(user.getId(),
                        ModerationStatus.ACCEPTED.toString(),
                        PageRequest.of(offset, limit));
                postListPubl.forEach(post -> postOutList.add(PostResponseListMap.map(post)));
                break;
        }

        return ResponseEntity.ok(new PostResponseList(postOutList.size(), postOutList));
    }

    @Override
    public ResponseEntity<PostResponse> getPostsById(int id, Principal principal) throws NotFoundEntity {
        Post post;
        if (principal == null) {
            post = postRepository.findByIdAndIsActiveAndModerationStatusAndTime(id,
                    LocalDateTime.now()).orElseThrow(() -> new NotFoundEntity(messageNotFound));
            postRepository.save(post);
        } else {
            User user = userRepository.findUserByEmail(principal.getName()).orElseThrow();
            post = postRepository.findById(id).orElseThrow(() -> new NotFoundEntity(messageNotFound));
            if (user.getIsModerator() != 1 && !user.getPostListUser().contains(post)) {
                post.setViewCount(post.getViewCount() + 1);
                postRepository.save(post);
            }
        }
        return ResponseEntity.ok(new PostResponse(post));
    }

    @Override
    public ResponseEntity<PositiveResultResponse> addPost(PostRequest postRequest, Principal principal) {
        User user = userRepository.findUserByEmail(principal.getName()).orElseThrow();
        Post post = PostRequestMap.map(postRequest, user);
        postRepository.save(post);
        if (!postRequest.getTags().isEmpty()) {
            addTag2Post(post, postRequest);
        }
        return ResponseEntity.ok(new PositiveResultResponse());
    }

    @Override
    public Map<String, Object> updatePost(int id, PostRequest postRequest, User user) throws EntityNotFoundException, BadRequestException {
        return null;
    }

    private boolean isCorrect(Post post) {
        LocalDateTime currentTime = LocalDateTime.now();
        return post.getIsActive() == 1
                && post.getModerationStatus().equals(ModerationStatus.ACCEPTED)
                && post.getTime().isBefore(currentTime);
    }

    private void addTag2Post(Post post, PostRequest postRequest) {
        postRequest.getTags().forEach(tagName -> {
            Optional<Tag> tagOptional = tagRepository.findByName(tagName);
            if (tagOptional.isPresent()) {
                Tags2Posts tags2Posts = new Tags2Posts();
                tags2Posts.setPostId(post);
                tags2Posts.setTagId(tagOptional.orElseThrow());
                tags2PostsRepository.save(tags2Posts);
            } else {
                Tag tag = new Tag();
                tag.setName(tagName);
                tagRepository.save(tag);
                Tags2Posts tags2Posts = new Tags2Posts();
                tags2Posts.setPostId(post);
                tags2Posts.setTagId(tag);
                tags2PostsRepository.save(tags2Posts);
            }
        });
    }
}
