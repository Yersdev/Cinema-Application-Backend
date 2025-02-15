package org.project2.tz1_cinema.dto;

import lombok.*;

/**
 * Data Transfer Object for comments related to movies.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {

    /**
     * Title of the movie the comment is associated with.
     */
    private String movie_title;

    /**
     * First name of the user who made the comment.
     */
    private String firstName;

    /**
     * Last name of the user who made the comment.
     */
    private String lastName;

    /**
     * Email of the user who made the comment.
     */
    private String email;

    /**
     * Content of the comment.
     */
    private String comments;
}