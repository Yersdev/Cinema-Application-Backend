package org.project2.tz1_cinema.service;
import lombok.extern.slf4j.Slf4j;
import org.project2.tz1_cinema.dto.UserInfoUserDetails;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Используем email вместо имени для поиска пользователя
        Optional<Users> userInfo = repository.findByEmail(username);
    log.info("user info: {}", userInfo);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

    }
}