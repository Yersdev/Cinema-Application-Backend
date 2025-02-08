package org.project2.tz1_cinema.service;
import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.dto.UserProfileDto;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserInfoUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userInfo = repository.findByEmail(username);
        return userInfo.map(UserProfileDto::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}