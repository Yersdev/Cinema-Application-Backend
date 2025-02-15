/**
 * Repository interface for managing User entities.
 * Extends JpaRepository to provide CRUD operations for User objects.
 */
package org.project2.tz1_cinema.repository;

import org.project2.tz1_cinema.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for User entity.
 * Provides methods to find users by name, email.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    /**
     * Finds a user by their username.
     * @param username the username to search for.
     * @return the user entity found.
     */
    Users findUsersByName(String username);

    /**
     * Finds a user by their email.
     * @param email the email to search for.
     * @return the user entity found.
     */
    Users findUsersByEmail(String email);

    /**
     * Finds a user by email.
     * @param email the email address.
     * @return the user entity found.
     */
    Users findByEmail(String email);

}
