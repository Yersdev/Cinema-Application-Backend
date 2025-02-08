package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.dto.ActorDto;
import org.project2.tz1_cinema.dto.MovieDto;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.model.Comment;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repository.ActorRepository;
import org.project2.tz1_cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }
    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title).orElse(null);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getByReleaseYear(int year) {
        return movieRepository.getMovieByReleaseYear(year);
    }

    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }
    public List<Movie> releaseDate(int releaseData){
        return movieRepository.findByRelease_data(releaseData);
    }
    public List<Movie> findByActor(Actor actor) {
        return movieRepository.findByActors(actor);
    }
    public Movie addMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setCountry(movieDto.getCountry());
        movie.setGenre(movieDto.getGenre());

        List<Actor> actors = new ArrayList<>();

        for (ActorDto actor : movieDto.getActors()) {
            Optional<Actor> existingActorOpt = Optional.ofNullable(actorRepository.findByFirstNameAndLastName(actor.getFirstName(), actor.getLastName()));

            Actor actorToAdd;
            actorToAdd = existingActorOpt.orElseGet(() -> actorRepository.save(convertActorDtoToActor(actor)));
            actors.add(actorToAdd);
        }

        movie.setActors(actors);

        return movieRepository.save(movie);
    }
    public void save(Movie movie){
        movieRepository.save(movie);
    }
    public Actor convertActorDtoToActor(ActorDto actorDto) {
        Actor actor = new Actor();
        actor.setFirstName(actorDto.getFirstName());
        actor.setLastName(actorDto.getLastName());
        actor.setYearOfBirth(actorDto.getYearOfBirth());
        return actor;
    }
    public void updateComment(Comment comment) {

    }
}