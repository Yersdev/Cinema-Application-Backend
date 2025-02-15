/**
 * Service class for managing Comment entities.
 * Provides methods to save and create comments associated with movies and users.
 */
package org.project2.tz1_cinema.service;

import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * Saves a Comment entity to the database.
     * @param comment the comment entity to save.
     */
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * Creates and saves a new Comment entity.
     * @param comments the text of the comment.
     * @param byId the movie associated with the comment.
     * @param byEmail the user who made the comment.
     * @return the saved Comment entity.
     */
    public Comment save(String comments, Movie byId, Users byEmail) {
        Comment newComment = new Comment();
        newComment.setComment(comments);
        newComment.setMovie(byId);
        newComment.setUsers(byEmail);
        return commentRepository.save(newComment);
    }
}
