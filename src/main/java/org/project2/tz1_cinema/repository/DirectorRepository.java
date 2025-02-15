/**
 * Repository interface for managing Director entities.
 * Extends JpaRepository to provide CRUD operations for Director objects.
 */
package org.project2.tz1_cinema.repository;

import org.project2.tz1_cinema.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Director entity.
 * Provides basic CRUD operations through JpaRepository.
 */
public interface DirectorRepository extends JpaRepository<Director, Integer> {

}
