package org.project2.tz1_cinema.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.project2.tz1_cinema.dto.*;
import org.project2.tz1_cinema.model.*;
import org.project2.tz1_cinema.repository.ActorRepository;
import org.project2.tz1_cinema.repository.CommentRepository;
import org.project2.tz1_cinema.repository.MovieRepository;
import org.project2.tz1_cinema.repository.UserRepository;
import org.project2.tz1_cinema.service.ActorService;
import org.project2.tz1_cinema.service.DirectorService;
import org.project2.tz1_cinema.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final ActorRepository actorRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ActorService actorService;
    private final DirectorService directorService;

    //all movies
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieConvertToDto(movieRepository.findAll());
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{year}")
    public ResponseEntity<List<MovieDto>> getMoviesByYear(@PathVariable int year) {
        List<Movie> movies = movieService.getByReleaseYear(year);
        List<MovieDto> movieDtos = movieConvertToDto(movies);

        if (movieDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    //get movie where we have the current actor
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{actorId}/all_movies")
    public ResponseEntity<List<MovieDto>> getAllMoviesByActor(@PathVariable int actorId) {
        // Получаем актера
        Actor actor = actorRepository.findById(actorId);
        if (actor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Movie> movies = movieRepository.findByActors(actor);
        List<MovieDto> movieDtos = movieConvertToDto(movies);
        return ResponseEntity.ok(movieDtos);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{movieId}/comments")
    public ResponseEntity<List<MovieCommentDto>> getCommentsByMovie(@PathVariable int movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Comment> comments = movie.getComments();
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<MovieCommentDto> commentDtos = commentConvertToDto(comments);
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{movieId}/actors")
    public ResponseEntity<List<ActorDto>> getActorsByMovie(@PathVariable int movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Actor> actors = movie.getActors();
        List<ActorDto> actorDtos = actorConvertToDtoList(actors);
        if (actorDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(actorDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/{idCinema}/addComment", consumes = "application/json")
    public ResponseEntity<Comment> addMovie(@RequestBody CommentDto dto,
                                            @PathVariable Integer idCinema) {
        Movie movie = movieRepository.findById(idCinema).orElse(null);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!userRepository.existsByName(dto.getFirstName())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Movie movie1 = movieRepository.findById(idCinema)
                .orElseThrow(() -> new EntityNotFoundException("Movie with ID " + idCinema + " not found"));
        Users user = userRepository.findById(userRepository.findUsersByEmail(dto.getEmail()).getId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userRepository.findUsersByEmail(dto.getEmail()).getId() + " not found"));
        Comment newComment = new Comment();
        newComment.setComment(dto.getComments());
        newComment.setMovie(movie);
        newComment.setUsers(user);
        commentRepository.save(newComment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }
//    ------------------ADMIN----------------------

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/movies/add", consumes = "application/json")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDto movieDto) {
        Movie movie1 = new Movie();
        movie1.setTitle(movieDto.getTitle());
        movie1.setCountry(movieDto.getCountry());
        movie1.setGenre(movieDto.getGenre());
        movie1.setActors(ActorDtoListconvertActorList(movieDto.getActors()));
        List<Actor> actors = ActorDtoListconvertActorList(movieDto.getActors());
        actorRepository.saveAll(actors);
        movie1.setReleaseYear(movieDto.getReleaseYear());
        movie1.setComments(movie1.getComments());
        movie1.setDirector(convactorDtoDirector(movieDto.getDirector()));
        movieService.save(movie1);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie1);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/actor/add")
    public ResponseEntity<ActorDto> addActor(@RequestBody ActorDto actorDto) {
        if (actorDto.getFirstName() == null || actorDto.getFirstName().isEmpty() || actorDto.getLastName() == null || actorDto.getLastName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        actorService.save(convertToActorDto(actorDto));

        return new ResponseEntity<>(actorDto, HttpStatus.CREATED);
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/director/add")
    public ResponseEntity<DirectorDto> addDirector(@RequestBody DirectorDto directorDto) {
        Director director = new Director();
        director.setFirstName(directorDto.getFirstName());
        director.setLastName(directorDto.getLastName());
        director.setYearOfBirth(directorDto.getYearOfBirth());
        directorService.save(director);
        return new ResponseEntity<>(directorDto, HttpStatus.CREATED);
    }

//----------------------CONVERTER--------------------------------
    public UserDto userConvertToDto(Users users) {
        if (users == null) {
            return null;}
        UserDto userDto = new UserDto();
        userDto.setName(users.getName());
        userDto.setLast_name(users.getLast_name());
        userDto.setEmail(users.getEmail());
        return userDto;
    }
    private DirectorDto directorDtoConvertToDto(Director director) {
        if (director == null) {
            return null;
        }
        DirectorDto directorDto = new DirectorDto();
        directorDto.setFirstName(director.getFirstName());
        directorDto.setLastName(director.getLastName());
        directorDto.setYearOfBirth(director.getYearOfBirth());
        return directorDto;
    }
    public List<MovieCommentDto> commentConvertToDto(List<Comment> comments) {
        if (comments == null || comments.isEmpty()) {
            return Collections.emptyList();
        }
        List<MovieCommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            MovieCommentDto commentDto = new MovieCommentDto();
            commentDto.setComment(comment.getComment());
            commentDto.setUserdto(userConvertToDto(comment.getUsers()));
            commentDto.setMovie(comment.getMovie()); commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    private ActorDto actorConvertToDto(Actor actor) {
        if (actor == null) {
            return null;
        }
        ActorDto actorDto = new ActorDto();
        actorDto.setFirstName(actor.getFirstName());
        actorDto.setLastName(actor.getLastName());
        actorDto.setYearOfBirth(actor.getYearOfBirth());
        return actorDto;
    }

    private List<ActorDto> actorConvertToDtoList(List<Actor> actors) {
        if (actors == null || actors.isEmpty()) {
            return Collections.emptyList();
        }
        List<ActorDto> actorDtos = new ArrayList<>();
        for (Actor actor : actors) {
            actorDtos.add(actorConvertToDto(actor));
        }
        return actorDtos;
    }

    private List<MovieDto> movieConvertToDto(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return Collections.emptyList();
        }
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            MovieDto movieDto = new MovieDto();
            movieDto.setTitle(movie.getTitle());
            List<ActorDto> actorDtos = new ArrayList<>();
            for (Actor actor : movie.getActors()) {
                actorDtos.add(actorConvertToDto(actor));
            }
            movieDto.setActors(actorDtos);
            movieDto.setDirector(directorDtoConvertToDto(movie.getDirector()));
            movieDto.setReleaseYear(movie.getReleaseYear());
            movieDto.setCountry(movie.getCountry());
            movieDto.setGenre(movie.getGenre());
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }
    private List<Actor> ActorDtoListconvertActorList(List<ActorDto> actorAddDtoList) {
        List<Actor> actors = new ArrayList<>();
        for (ActorDto actorAddDto : actorAddDtoList) {
            Actor actor = convertToActorDto(actorAddDto);
            actor.setFirstName(actorAddDto.getFirstName());
            actor.setLastName(actorAddDto.getLastName());
            actor.setYearOfBirth(actorAddDto.getYearOfBirth());
            actors.add(actor);
        }
        return actors;
    }
    private Actor convertToActorDto(ActorDto actorAddDto) {
        Actor actor = new Actor();
        actor.setFirstName(actorAddDto.getFirstName());
        actor.setLastName(actorAddDto.getLastName());
        actor.setYearOfBirth(actorAddDto.getYearOfBirth());
        return actor;
    }
    private Director convactorDtoDirector(DirectorDto DirectorAddDto) {
        Director director = new Director();
        director.setFirstName(DirectorAddDto.getFirstName());
        director.setLastName(DirectorAddDto.getLastName());
        director.setYearOfBirth(DirectorAddDto.getYearOfBirth());
        return director;
    }
}
