package org.project2.tz1_cinema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.util.List;

/**
 * Entity class representing a user in the cinema application.
 * This class is mapped to the 'users' table in the database and holds user-related data.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {
    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * First name of the user.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Last name of the user.
     */
    private String last_name;

    /**
     * Password for the user's account.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Email address of the user, must be unique and valid.
     */
    @Column(nullable = false, unique = true)
    @Email(message = "Please write a valid email")
    private String email;

    /**
     * Role of the user in the system (ADMIN or USER).
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * List of comments made by the user.
     */
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Comment> comments;
}