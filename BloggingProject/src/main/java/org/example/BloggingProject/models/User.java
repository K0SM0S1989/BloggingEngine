package org.example.BloggingProject.models;


import lombok.Data;
import org.example.BloggingProject.enums.Role;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_moderator", nullable = false)
    private byte isModerator;

    @Column(name = "reg_time", nullable = false)
    private Date regTime;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String code;

    private String photo;

    @OneToMany(targetEntity = Post.class, mappedBy = "userId")
    private List<Post> postListUser;

    @OneToMany(mappedBy = "moderatorId")
    private List<Post> postListModerator;

    @OneToMany(targetEntity = PostVote.class, mappedBy = "userId")
    private List<PostVote> postVoteList;

    @OneToMany(targetEntity = PostComment.class, mappedBy = "userId")
    private List<PostComment> postCommentList;

//    @Enumerated(EnumType.STRING)
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "roles")
//    private Set<Role> roles = new HashSet<>();


    public Set<Role> getRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(isModerator == 1 ? Role.MODERATOR : Role.USER);
        return roles;
    }
}
