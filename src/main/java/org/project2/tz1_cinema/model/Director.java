package org.project2.tz1_cinema.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a director in the cinema application.
 * Contains details such as the director's first name, last name, and year of birth.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "director")
public class Director {

    /**
     * Unique identifier for the director, auto-generated using IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * First name of the director, with a maximum length of 50 characters and cannot be null.
     */
    @Column(length = 50, nullable = false)
    private String firstName;

    /**
     * Last name of the director, with a maximum length of 50 characters.
     */
    @Column(length = 50)
    private String lastName;

    /**
     * Year of birth of the director, stored as an Integer and cannot be null.
     */
    @Column(length = 50, nullable = false)
    private Integer yearOfBirth;
}