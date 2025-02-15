/**
 * Repository interface for managing Movie entities.
 * Extends JpaRepository to provide CRUD operations for Movie objects.
 */
package org.project2.tz1_cinema.repository;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Movie entity.
 * Provides methods to find movies by actor, release year, title, and country.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    /**
     * Retrieves all movies.
     * @return list of all movies.
     */
    @Override
    List<Movie> findAll();

    /**
     * Finds movies by the given actor.
     * @param actor the actor entity.
     * @return list of movies featuring the given actor.
     */
    List<Movie> findByActor(Actor actor);

    /**
     * Retrieves movies by release year.
     * @param releaseYear the year of release.
     * @return list of movies released in the specified year.
     */
    List<Movie> getMovieByReleaseYear(int releaseYear);

    /**
     * Finds a movie by its title.
     * @param title the title of the movie.
     * @return an Optional containing the movie if found.
     */
    Optional<Movie> findByTitle(String title);

    /**
     * Finds a movie by its country of origin.
     * @param country the country of the movie.
     * @return an Optional containing the movie if found.
     */
    Optional<Movie> findByCountry(String country);
}
