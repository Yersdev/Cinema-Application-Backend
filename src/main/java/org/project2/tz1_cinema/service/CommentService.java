package org.project2.tz1_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repository.CommentRepository;
import org.project2.tz1_cinema.repository.MovieRepository;
import org.project2.tz1_cinema.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommentService {
    private CommentRepository commentRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    public CommentService(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Comment createComment(String content, Integer movieId, Integer userId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with ID " + movieId + " not found"));

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        Comment newComment = new Comment();
        newComment.setComment(content);
        newComment.setMovie(movie);
        newComment.setUsers(user);

        return commentRepository.save(newComment);
    }

}
