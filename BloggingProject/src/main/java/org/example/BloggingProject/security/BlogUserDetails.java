package org.example.BloggingProject.security;

import org.example.BloggingProject.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class BlogUserDetails implements UserDetails {
    private final User blogUser;

    public BlogUserDetails(User blogUser) {
        this.blogUser = blogUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return blogUser.getRoles();
    }

    @Override
    public String getPassword() {
        return blogUser.getPassword();
    }

    @Override
    public String getUsername() {
        return blogUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
