package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.dto.ActorDto;
import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.repository.ActorRepository;
import org.project2.tz1_cinema.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorDirectorService {

    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;

    @Autowired
    public ActorDirectorService(ActorRepository actorRepository, DirectorRepository directorRepository) {
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
    }

    public void addActor(ActorDto actorDto) {
        Actor actor = new Actor();
        actor.setFirstName(actorDto.getFirstName());
        actor.setLastName(actorDto.getLastName());
        actor.setYearOfBirth(actorDto.getYearOfBirth());

        actorRepository.save(actor);
    }

//    public void addDirector(Director directo) {
//        Director director = new Director();
//        director.setFirstName(directorDto.getFirstName());
//        director.setLastName(directorDto.getLastName());
//        director.setYearOfBirth(directorDto.getYearOfBirth());
//
//        directorRepository.save(director);
//    }
}
