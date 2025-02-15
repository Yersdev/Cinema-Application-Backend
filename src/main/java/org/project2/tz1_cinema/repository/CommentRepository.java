/**
 * Repository interface for managing Comment entities.
 * Extends JpaRepository to provide CRUD operations for Comment objects.
 */
package org.project2.tz1_cinema.repository;

import org.project2.tz1_cinema.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Comment entity.
 * Provides basic CRUD operations through JpaRepository.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
