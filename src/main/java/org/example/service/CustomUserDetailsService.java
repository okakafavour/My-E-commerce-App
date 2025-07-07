package org.example.service;

import org.example.data.model.User;
import org.example.data.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Return Spring Security User with username, password, and authorities
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),                          // username
                user.getPassword(),                      // hashed password
                Collections.singleton(                   // single authority (role)
                        new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
                )
        );
    }
}
