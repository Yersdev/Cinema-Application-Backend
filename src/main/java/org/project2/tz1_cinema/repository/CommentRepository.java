package org.project2.tz1_cinema.repository;

import org.project2.tz1_cinema.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
