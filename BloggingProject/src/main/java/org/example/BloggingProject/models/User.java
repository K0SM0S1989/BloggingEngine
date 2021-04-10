package org.example.BloggingProject.models;


import lombok.Data;
import org.example.BloggingProject.enums.Role;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "users")
public class User{
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


//    @Enumerated(EnumType.STRING)
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "roles")
//    Set<Role> roles = new HashSet<>();
//
//    public void setRoles() {
//        Set<Role> roles;
//        if (isModerator == 0) {
//            roles = Collections.singleton(Role.USER);
//        }else  roles = Collections.singleton(Role.MODERATOR);
//        this.roles = roles;
//    }

    @OneToMany(targetEntity = Post.class, mappedBy = "userId")
    private List<Post> postListUser;

    @OneToMany(mappedBy = "moderatorId")
    private List<Post> postListModerator;

    @OneToMany(targetEntity = PostVote.class, mappedBy = "userId")
    private List<PostVote> postVoteList;

    @OneToMany(targetEntity = PostComment.class, mappedBy = "userId")
    private List<PostComment> postCommentList;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
//    }
//
//    @Override
//    public String getUsername() {
//        return name;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }


}
