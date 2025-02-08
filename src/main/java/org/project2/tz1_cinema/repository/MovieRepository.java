package org.project2.tz1_cinema.repository;

import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Override
    List<Movie> findAll();

    default List<Movie> findByRelease_data(int releaseData) {
        return null;
    }
    List<Movie> findByActors(Actor actor);


    List<Movie> getMovieByReleaseYear(int releaseYear);

    Movie getMovieByTitle(String title);

    Optional<Movie> findByTitle(String title);

    Movie getById(int id);

    Optional<Movie> findByCountry(String country);
}
