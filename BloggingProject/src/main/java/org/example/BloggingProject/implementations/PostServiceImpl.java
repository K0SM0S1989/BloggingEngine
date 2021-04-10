package org.example.BloggingProject.implementations;

import org.example.BloggingProject.dto.*;
import org.example.BloggingProject.enums.ModerationStatus;
import org.example.BloggingProject.exceptions.AddPostError;
import org.example.BloggingProject.exceptions.BadRequestException;
import org.example.BloggingProject.exceptions.EntityNotFoundException;
import org.example.BloggingProject.mappers.AllPostMap;
import org.example.BloggingProject.mappers.CommentOutMap;
import org.example.BloggingProject.mappers.PostDTOMap;
import org.example.BloggingProject.models.*;
import org.example.BloggingProject.repository.PostRepository;

import org.example.BloggingProject.repository.TagRepository;

import org.example.BloggingProject.repository.Tags2PostsRepository;
import org.example.BloggingProject.response.AllPost;
import org.example.BloggingProject.response.CommentOut;
import org.example.BloggingProject.response.PostsOut;
import org.example.BloggingProject.response.UserForAllPostOut;
import org.example.BloggingProject.serv.PostService;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final Tags2PostsRepository tags2PostsRepository;


    public PostServiceImpl(PostRepository postRepository, TagRepository tagRepository, Tags2PostsRepository tags2PostsRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.tags2PostsRepository = tags2PostsRepository;
    }


    /**
     * Метод выводит список постов
     */
    @Override
    public PostsOut get(int offset, int limit, String mode) {
        List<AllPost> postOutList = new ArrayList<>();
        fillingPostOutList(offset, limit, postOutList);
        switch (mode) {
            case "recent":
                postOutList.sort(Comparator.comparing(AllPost::getTimespent).reversed());
                break;
            case "popular":
                postOutList.sort(Comparator.comparing(AllPost::getCommentCount).reversed());
                break;
            case "best":
                postOutList.sort(Comparator.comparing(AllPost::getLikeCount).reversed());
                break;
            case "early":
                postOutList.sort(Comparator.comparing(AllPost::getTimespent));
                break;
        }

        return new PostsOut(postOutList.size(), postOutList);
    }


    @Override
    public PostsOut get(int offset, int limit) {
        List<AllPost> postOutList = new ArrayList<>();
        fillingPostOutList(offset, limit, postOutList);
        return new PostsOut(postOutList.size(), postOutList);
    }

    private void fillingPostOutList(int offset, int limit, List<AllPost> postOutList) {
        Page<Post> postList = postRepository.findAll(PageRequest.of(offset, limit));
        postList.forEach(post -> {
            if (isCorrect(post)) {
                postOutList.add(AllPostMap.map(post));
            }
        });
    }

    @Override
    public PostsOut getByQuery(String query, int offset, int limit) {
        List<AllPost> postOutList = new ArrayList<>();
        Page<Post> postList = postRepository.findAll(PageRequest.of(offset, limit));
        postList.forEach(post -> {
            if (isCorrect(post)) {
                if (post.getTitle().contains(query) || post.getText().contains(query))
                    postOutList.add(AllPostMap.map(post));
            }
        });
        return new PostsOut(postOutList.size(), postOutList);
    }


    @Override
    public PostsOut getByDate(LocalDate date, int offset, int limit) {
        Page<Post> postList = postRepository.findAll(PageRequest.of(offset, limit));
        List<AllPost> postOutList = new ArrayList<>();
        postList.forEach(post -> {
            if (isCorrect(post)) {
                if (post.getTime().minusHours(3).getDayOfYear() == date.getDayOfYear())//костыль с тремя часами
                    postOutList.add(AllPostMap.map(post));
            }
        });

        return new PostsOut(postOutList.size(), postOutList);
    }

    @Override
    public PostsOut getByTag(String tagName, int offset, int limit) {
        Tag tag = tagRepository.findByName(tagName);
        List<Post> postList = new ArrayList<>();
        List<AllPost> allPostList = new ArrayList<>();
        if (tag != null) {
            List<Tags2Posts> tags2PostsList = tag.getTags2PostsList();
            tags2PostsList.forEach(tags2Posts -> {
                Post post = tags2Posts.getPostId();
                postList.add(post);
            });
            postList.forEach(post -> {
                if (isCorrect(post)) {
                    allPostList.add(AllPostMap.map(post));
                }
            });
        }
        return new PostsOut(allPostList.size(), allPostList);
    }

    @Override
    public PostsOut getForModeration(User user, int offset, int limit, ModerationStatus status) {
        Page<Post> postList = postRepository.
                findAllByModeratorIdAndModerationStatusAndIsActive(user,
                        PageRequest.of(offset, limit),
                        status,
                        (byte) 1);
        List<AllPost> postOutList = new ArrayList<>();
        postList.forEach(post -> postOutList.add(AllPostMap.map(post)));
        return new PostsOut(postOutList.size(), postOutList);


    }

    @Override
    public PostsOut getForMyPosts(User user, int offset, int limit, String status) {
        List<AllPost> postOutList = new ArrayList<>();
        Page<Post> postList;
        switch (status) {
            case "inactive":
                postList = postRepository.findAllByUserIdAndIsActive(user,
                        PageRequest.of(offset, limit), (byte) 0);
                postList.forEach(post -> postOutList.add(AllPostMap.map(post)));
                break;
            case "pending":
                postList = postRepository.
                        findAllByUserIdAndIsActiveAndModerationStatus(user,
                                PageRequest.of(offset, limit),
                                (byte) 1,
                                ModerationStatus.NEW);
                postList.forEach(post -> postOutList.add(AllPostMap.map(post)));
                break;
            case "declined":
                postList = postRepository.
                        findAllByUserIdAndIsActiveAndModerationStatus(user,
                                PageRequest.of(offset, limit),
                                (byte) 1,
                                ModerationStatus.DECLINED
                        );
                postList.forEach(post -> postOutList.add(AllPostMap.map(post)));
                break;
            case "published":
                postList = postRepository.
                        findAllByUserIdAndIsActiveAndModerationStatus(user,
                                PageRequest.of(offset, limit),
                                (byte) 1,
                                ModerationStatus.ACCEPTED);
                postList.forEach(post -> postOutList.add(AllPostMap.map(post)));
                break;
        }
        return new PostsOut(postOutList.size(), postOutList);

    }

    @Override
    public Map<String, Object> getPostsById(int id, User user) throws EntityNotFoundException {
        boolean modOrUser = true;
        Map<String, Object> postById = new HashMap<>();
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw EntityNotFoundException.createWith(id, "Документ не найден");
        } else {
            LocalDateTime postTime = post.get().getTime().minusHours(3);//костыль
            final int[] likeCount = {0};
            final int[] dislikeCount = {0};

            if (isCorrect(post.get())) {
                if (!user.equals(post.get().getUserId()) && user.getIsModerator() != 1) {
                    modOrUser = false;
                    post.get().setViewCount(post.get().getViewCount() + 1);
                    postRepository.save(post.get());
                }
                postById.put("id", id);
                postById.put("timestamp", Timestamp.valueOf(postTime).getTime());
                if (modOrUser) {
                    postById.put("active", true);
                }
                User userOfPost = post.get().getUserId();
                UserForAllPostOut userForAllPostOut = new UserForAllPostOut(userOfPost.getId(), userOfPost.getName());
                postById.put("user", userForAllPostOut);
                postById.put("title", post.get().getTitle());
                postById.put("text", post.get().getText());

                List<PostVote> postVoteList = post.get().getPostVoteList();
                postVoteList.forEach(postVote -> {
                    if (postVote.getValue() == 1) {
                        likeCount[0]++;
                    } else dislikeCount[0]++;
                });
                postById.put("likeCount", likeCount[0]);
                postById.put("dislikeCount", dislikeCount[0]);
                postById.put("viewCount", post.get().getViewCount());

                List<PostComment> postCommentList = post.get().getPostCommentList();
                List<CommentOut> commentOuts = new ArrayList<>();
                postCommentList.forEach(postComment -> commentOuts.add(CommentOutMap.map(postComment)));
                postById.put("comments", commentOuts);

                List<String> tagList = new ArrayList<>();
                List<Tags2Posts> tags2PostsList = post.get().getTags2PostsList();
                tags2PostsList.forEach(tags2Posts -> tagList.add(tags2Posts.getTagId().getName()));

                postById.put("tags", tagList);

            }
            return postById;
        }

    }

    @Override
    public Map<String, Object> addPost(PostDTO postDTO, User user) {
        if (postDTO.getText().length() < 50 || postDTO.getTitle().length() < 3) {
            Map<String, Object> failAdd = new HashMap<>();
            failAdd.put("result", false);
            failAdd.put("errors", AddPostError.errorFail(postDTO.getText(), postDTO.getTitle()));
            return failAdd;
        } else {
            Map<String, Object> successAdd = new HashMap<>();
            Post post = PostDTOMap.map(postDTO, user);
            postRepository.save(post);
            addTags(postDTO, post);
            successAdd.put("result", true);
            return successAdd;
        }
    }

    @Override
    public Map<String, Object> updatePost(int id, PostDTO postDTO, User user) throws EntityNotFoundException, BadRequestException {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isEmpty()) {
            throw EntityNotFoundException.createWith(id, "Документ не найден");
        } else {
            if (postDTO.getText().length() < 50 || postDTO.getTitle().length() < 3) {
                Map<String, String> errors = AddPostError.errorFail(postDTO.getText(), postDTO.getTitle());
                throw BadRequestException.createWith(false, errors);
            } else {
                Map<String, Object> successPut = new HashMap<>();
                postOptional.get().setTime(Instant.ofEpochMilli(postDTO.getTimestamp()).atZone(ZoneId.of("UTC")).toLocalDateTime());
                postOptional.get().setIsActive((byte) postDTO.getActive());
                postOptional.get().setTitle(postDTO.getTitle());
                postOptional.get().setText(postDTO.getText());
                postRepository.save(postOptional.get());
                addTags(postDTO, postOptional.get());
                successPut.put("result", true);
                return successPut;
            }
        }
    }


    private boolean isCorrect(Post post) {
        LocalDateTime postTime = post.getTime().minusHours(3); //костыль, потому что hibernate почему то прибавляет 3 часа к дате из БД
        LocalDateTime currentTime = LocalDateTime.now();
        return post.getIsActive() == 1
                && post.getModerationStatus().equals(ModerationStatus.ACCEPTED)
                && postTime.isBefore(currentTime);
    }


    private void addTags(PostDTO postDTO, Post post) {
        List<String> tagNames = postDTO.getTags();
        tagNames.forEach(tagName -> {
            Tag tag = tagRepository.findByName(tagName);
            if (tag == null) {
                Tag newTag = new Tag();
                newTag.setName(tagName);

                tagRepository.save(newTag);
                Tags2Posts tags2Posts = new Tags2Posts();
                tags2Posts.setTagId(newTag);
                tags2Posts.setPostId(post);
                tags2PostsRepository.save(tags2Posts);

            } else {
                List<Tags2Posts> tags2PostsListOfPost = post.getTags2PostsList();
               AtomicBoolean postHaveTag = new AtomicBoolean(true);

                tags2PostsListOfPost.forEach(post2tags -> {
                    if (post2tags.getTagId().equals(tag)){
                        postHaveTag.set(false);
                    }
                });

                if (postHaveTag.get()) {
                    Tags2Posts tags2Posts = new Tags2Posts();
                    tags2Posts.setTagId(tag);
                    tags2Posts.setPostId(post);
                    tags2PostsRepository.save(tags2Posts);
                }
            }
        });
    }

}
