package org.project2.tz1_cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project2.tz1_cinema.model.Movie;

/**
 * Data Transfer Object for movie comments.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieCommentDto {

    /**
     * The movie associated with the comment.
     */
    private Movie movie;

    /**
     * The content of the comment.
     */
    private String comment;

    /**
     * The user who posted the comment.
     */
    private UserDto userdto;
}
