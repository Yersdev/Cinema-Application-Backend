package org.project2.tz1_cinema.controller;

import lombok.AllArgsConstructor;
import org.project2.tz1_cinema.dto.*;
import org.project2.tz1_cinema.dto.converter.CommentConverter;
import org.project2.tz1_cinema.dto.converter.MovieConverter;
import org.project2.tz1_cinema.model.*;
import org.project2.tz1_cinema.service.ActorService;
import org.project2.tz1_cinema.service.CommentService;
import org.project2.tz1_cinema.service.MovieService;
import org.project2.tz1_cinema.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.project2.tz1_cinema.dto.converter.ActorConverter.actorListToDtoList;

/**
 * Controller for managing movie-related operations accessible by users and admins.
 */
@RestController
@RequestMapping("/user/movies")
@AllArgsConstructor
@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
public class MovieController {
    private final MovieConverter movieConverter;
    private final MovieService movieService;
    private final ActorService actorService;
    private final UserService userService;
    private final CommentConverter commentConverter;
    private final CommentService commentService;

    /**
     * Get all movies.
     * @return List of all movies as DTOs or NO_CONTENT if none found.
     */
    @GetMapping("")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        if (movieConverter.movieListToDtoList(movieService.findAll()).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movieConverter.movieListToDtoList(movieService.findAll()), HttpStatus.OK);
    }

    /**
     * Get movies by release year.
     * @param year Release year of movies.
     * @return List of movies released in the specified year or NO_CONTENT if none found.
     */
    @GetMapping("/{year}")
    public ResponseEntity<List<MovieDto>> getMoviesByYear(@PathVariable int year) {
        if (movieService.getMoviesByReleaseYear(year).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movieService.getMoviesByReleaseYear(year), HttpStatus.OK);
    }

    /**
     * Get all movies by a specific actor.
     * @param actorId ID of the actor.
     * @return List of movies with the specified actor or NOT_FOUND if actor does not exist.
     */
    @GetMapping("/{actorId}/all_movies")
    public ResponseEntity<List<MovieDto>> getAllMoviesByActor(@PathVariable int actorId) {
        if (actorService.findById(actorId) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(movieService.findByActor(actorService.findById(actorId)));
    }

    /**
     * Get comments for a specific movie.
     * @param movieId ID of the movie.
     * @return List of comments for the movie, NOT_FOUND if movie doesn't exist, or NO_CONTENT if no comments found.
     */
    @GetMapping("/{movieId}/comments")
    public ResponseEntity<CommentDto> getCommentsByMovie(@PathVariable int movieId) {
        if (movieService.findById(movieId) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (movieService.findById(movieId).getComments().isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(commentConverter.commentListToDtoList((Comment) movieService.findById(movieId).getComments()), HttpStatus.OK);
    }

    /**
     * Get actors for a specific movie.
     * @param movieId ID of the movie.
     * @return List of actors in the movie, NOT_FOUND if movie doesn't exist, or NO_CONTENT if no actors found.
     */
    @GetMapping("/{movieId}/actors")
    public ResponseEntity<List<ActorDto>> getActorsByMovie(@PathVariable int movieId) {
        if (movieService.findById(movieId) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (actorListToDtoList(movieService.findById(movieId).getActors()).isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(actorListToDtoList(movieService.findById(movieId).getActors()), HttpStatus.OK);
    }

    /**
     * Add a comment to a specific movie.
     * @param dto Comment data transfer object containing comment details.
     * @param idCinema ID of the movie.
     * @return Saved comment, NOT_FOUND if movie or user doesn't exist.
     */
    @PostMapping(value = "/{idCinema}/addComment", consumes = "application/json")
    public ResponseEntity<Comment> addMovie(@RequestBody CommentDto dto,
                                            @PathVariable Integer idCinema) {
        if (movieService.findById(idCinema) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (userService.userExists(dto.getEmail())) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(commentService.save(dto.getComments(), movieService.findById(idCinema), userService.findByEmail(dto.getEmail())), HttpStatus.OK);
    }
}