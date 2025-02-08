package org.project2.tz1_cinema.repository;

import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByMovies(Movie movie);
    Actor findById(int id);
    Actor findByFirstNameAndLastName(String actorName, String actorLastName);
}