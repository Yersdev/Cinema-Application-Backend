package org.project2.tz1_cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

/**
 * Entity class representing a movie in the cinema application.
 * Contains details such as title, release year, country, genre, director, actors, and comments.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "film")
@JsonIgnoreProperties
@ToString
@Builder
public class Movie {

    /**
     * Unique identifier for the movie, auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Title of the movie, with a maximum length of 150 characters and cannot be null.
     */
    @Column(nullable = false, length = 150)
    @Size(max = 150, message = "The title could not have more than 150 characters")
    private String title;

    /**
     * List of actors participating in the movie.
     * Managed with a many-to-many relationship and a join table named 'movie_actors'.
     */
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonBackReference
    private List<Actor> actors;

    /**
     * Director of the movie, linked with a many-to-one relationship.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "director_id")
    private Director director;

    /**
     * Release year of the movie.
     */
    @Column(name = "release_year")
    private Integer releaseYear;

    /**
     * Country where the movie was produced.
     */
    private String country;

    /**
     * Genre of the movie.
     */
    private String genre;

    /**
     * List of comments associated with the movie, managed with a one-to-many relationship.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "movie", cascade = CascadeType.PERSIST)
    private List<Comment> comments;

    /**
     * Adds an actor to the list of actors in the movie.
     *
     * @param actor The actor to be added.
     */
    public void addActor(Actor actor) {
        this.actors.add(actor);
    }
}
