package org.example.BloggingProject.security;

import org.example.BloggingProject.models.User;
import org.example.BloggingProject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public BlogUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User blogUser = userRepository.findUserByEmail(s).orElseThrow(() ->
                new UsernameNotFoundException("Person with email: " + s + " not found"));
        return new BlogUserDetails(blogUser);
    }
}
