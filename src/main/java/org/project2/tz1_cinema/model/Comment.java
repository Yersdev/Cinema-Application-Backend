package org.project2.tz1_cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a comment left by a user on a movie in the cinema application.
 * Contains details such as the comment content, associated movie, and the user who made the comment.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "comment_user")
public class Comment {

    /**
     * Unique identifier for the comment, auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The movie this comment is associated with, fetched eagerly.
     * JsonBackReference prevents serialization of this relationship in the JSON response.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    /**
     * The user who made the comment, fetched eagerly.
     * JsonBackReference prevents serialization of this relationship in the JSON response.
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    /**
     * The textual content of the comment.
     */
    private String comment;
}
