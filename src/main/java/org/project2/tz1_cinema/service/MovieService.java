package org.project2.tz1_cinema.service;

import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.dto.DirectorDto;
import org.project2.tz1_cinema.dto.MovieDto;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Director;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;


    public void save(Movie movie){
        movieRepository.save(movie);
    }

    public List<MovieDto> getMoviesByReleaseYear(int year) {
        List<Movie> movies = movieRepository.getMovieByReleaseYear(year);
        return movieListToDtoList(movies);
    }

    public List<MovieDto> findByActor(Actor actor) {
        return movieListToDtoList(movieRepository.findByActor(actor));
    }

    public Movie findById(int movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }

    public List<Movie> findAll() {
    return movieRepository.findAll();
    }

    public void save(String title, String country, String genre, List<Actor> actors, Integer releaseYear, DirectorDto director) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setCountry(country);
        movie.setGenre(genre);
        movie.setActors(actors);
        movie.setReleaseYear(releaseYear);movie.setDirector(toEntity(director));
        movieRepository.save(movie);
    }
    private Director toEntity(DirectorDto DirectorAddDto) {
        Director director = new Director();
        director.setFirstName(DirectorAddDto.getFirstName());
        director.setLastName(DirectorAddDto.getLastName());
        director.setYearOfBirth(DirectorAddDto.getYearOfBirth());
        return director;
    }
}