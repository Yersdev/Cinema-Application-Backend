/**
 * Repository interface for managing Actor entities.
 * Extends JpaRepository to provide CRUD operations and custom queries for Actor objects.
 */
package org.project2.tz1_cinema.repository;

import org.project2.tz1_cinema.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Actor entity.
 * Provides methods to perform CRUD operations and find actors by specific attributes.
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    /**
     * Finds an actor by its unique identifier.
     * @param id the ID of the actor.
     * @return the Actor object if found, otherwise null.
     */
    Actor findById(int id);

    /**
     * Finds an actor by first and last name.
     * @param actorName the first name of the actor.
     * @param actorLastName the last name of the actor.
     * @return the Actor object matching the given first and last name.
     */
    Actor findByFirstNameAndLastName(String actorName, String actorLastName);
}
