package org.project2.tz1_cinema.service;

import lombok.extern.slf4j.Slf4j;
import org.project2.tz1_cinema.dto.UserInfoUserDetails;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repository.CommentRepository;
import org.project2.tz1_cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public UserService(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }
    public Users findByUsername(String username) {
        return userRepository.findUsersByName(username);
    }
    public Users findByEmail(String email) {
        return userRepository.findUsersByEmail(email);
    }

    @Transactional
    public void saveComment(Users user, Comment comment) {
        List<Comment> comments = user.getComments();
        comments.add(comment);

        comment.setUsers(user);
        commentRepository.save(comment);

        userRepository.save(user);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Используем email вместо имени для поиска пользователя
        Optional<Users> userInfo = userRepository.findByEmail(username);
        log.info("user info: {}", userInfo);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
    public List<Comment> getComments(Users user) {
        return user.getComments();
    }

}
