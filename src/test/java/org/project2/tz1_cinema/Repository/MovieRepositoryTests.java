package org.project2.tz1_cinema.Repository;

import org.junit.jupiter.api.Test;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;  // Change to field injection

    @Test
    public void MovieRepository_Save() {
        Movie movie = new Movie();
        movie.setTitle("Test1");
        movie.setCountry("USA");
        movie.setGenre("Action");

        Movie savedMovie = movieRepository.save(movie);


        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getId()).isGreaterThan(0);
    }
    @Test
    public void MovieRepository_FindByTitle() {
        Movie movie = new Movie();
        movie.setTitle("Test1");
        movie.setCountry("USA");
        movie.setGenre("Action");
        movieRepository.save(movie);
        String title = "Test1";
        Optional<Movie> movieList = movieRepository.findByTitle(title);
        assertThat(movieList.isPresent()).isTrue();
    }
    @Test
    public void MovieRepository_FindByCountry() {
        Movie movie = new Movie();
        movie.setTitle("Test1");
        movie.setCountry("USA");
        movie.setGenre("Action");
        movieRepository.save(movie);
        String country = "USA";
        Optional<Movie> movieList = movieRepository.findByCountry(country);
        assertThat(movieList.isPresent()).isTrue();
    }
    @Test
    public void MovieRepository_Delete() {
        Movie movie = new Movie();
        movie.setTitle("Test1");
        movie.setCountry("USA");
        movie.setGenre("Action");
        movieRepository.save(movie);
        movieRepository.delete(movie);
        assertThat(movieRepository.findAll()).isEmpty();
    }
}
