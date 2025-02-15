package org.project2.tz1_cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

/**
 * Entity class representing an actor in the cinema application.
 * Contains actor details such as first name, last name, year of birth, and associated movies.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actor")
public class Actor {

    /**
     * Unique identifier for the actor, generated using a sequence.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_seq")
    @SequenceGenerator(name = "actor_seq", sequenceName = "actor_sequence", allocationSize = 1)
    private int id;

    /**
     * First name of the actor, cannot exceed 50 characters and cannot be null.
     */
    @Column(length = 50, nullable = false)
    private String firstName;

    /**
     * Last name of the actor, must not be empty and cannot exceed 50 characters.
     */
    @Column(length = 50)
    @NotEmpty
    private String lastName;

    /**
     * List of movies associated with the actor, mapped by the "actors" field in the Movie entity.
     * JsonBackReference prevents serialization of this relationship in the JSON response.
     */
    @ManyToMany(mappedBy = "actors")
    @JsonBackReference
    private List<Movie> movies;

    /**
     * Year of birth of the actor, must be between 1900 and 2023.
     */
    @Column()
    @Min(value = 1900, message = "Year of birth must be greater than or equal to 1900")
    @Max(value = 2023, message = "Year of birth must be less than or equal to 2023")
    private int yearOfBirth;
}