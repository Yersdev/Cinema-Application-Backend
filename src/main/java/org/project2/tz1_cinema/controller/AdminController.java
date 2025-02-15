/**
 * Контроллер администратора для управления сущностями кинотеатра.
 * Поддерживает добавление фильмов, актёров и режиссёров.
 */
package org.project2.tz1_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.dto.converter.ActorConverter;
import org.project2.tz1_cinema.dto.*;
import org.project2.tz1_cinema.model.*;
import org.project2.tz1_cinema.service.ActorService;
import org.project2.tz1_cinema.service.DirectorService;
import org.project2.tz1_cinema.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для административных операций, доступный только пользователям с ролью ADMIN.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final MovieService movieService;
    private final ActorService actorService;
    private final DirectorService directorService;

    /**
     * Добавляет новый фильм.
     * @param movieDto DTO фильма
     * @return ResponseEntity с добавленным фильмом и статусом CREATED
     */
    @PostMapping(value = "/movies/add", consumes = "application/json")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        actorService.saveAll(ActorDtoListconvertActorList(movieDto.getActors()));
        movieService.save(movieDto.getTitle(), movieDto.getCountry(), movieDto.getGenre(), ActorDtoListconvertActorList(movieDto.getActors()), movieDto.getReleaseYear(), movieDto.getDirector());
        return new ResponseEntity<>(movieDto, HttpStatus.CREATED);
    }

    /**
     * Добавляет нового актёра.
     * @param actorDto DTO актёра
     * @return ResponseEntity с добавленным актёром и статусом CREATED, либо BAD_REQUEST в случае некорректных данных
     */
    @PostMapping("/actor/add")
    public ResponseEntity<ActorDto> addActor(@RequestBody ActorDto actorDto) {
        if (actorDto.getFirstName() == null || actorDto.getFirstName().isEmpty() || actorDto.getLastName() == null || actorDto.getLastName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        actorService.save(ActorConverter.dtoToActor(actorDto));
        return new ResponseEntity<>(actorDto, HttpStatus.CREATED);
    }

    /**
     * Добавляет нового режиссёра.
     * @param directorDto DTO режиссёра
     * @return ResponseEntity с добавленным режиссёром и статусом CREATED
     */
    @PostMapping("/director/add")
    public ResponseEntity<DirectorDto> addDirector(@RequestBody DirectorDto directorDto) {
        directorService.save(directorDto.getFirstName(), directorDto.getLastName(), directorDto.getYearOfBirth());
        return new ResponseEntity<>(directorDto, HttpStatus.CREATED);
    }

    /**
     * Конвертирует список DTO актёров в список сущностей актёров.
     * @param actorAddDtoList список DTO актёров
     * @return список сущностей Actor
     */
    private List<Actor> ActorDtoListconvertActorList(List<ActorDto> actorAddDtoList) {
        List<Actor> actors = new ArrayList<>();
        for (ActorDto actorAddDto : actorAddDtoList) {
            Actor actor = ActorConverter.dtoToActor(actorAddDto);
            actor.setFirstName(actorAddDto.getFirstName());
            actor.setLastName(actorAddDto.getLastName());
            actor.setYearOfBirth(actorAddDto.getYearOfBirth());
            actors.add(actor);
        }
        return actors;
    }
}