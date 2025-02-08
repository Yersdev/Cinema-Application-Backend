package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.repository.ActorRepository;
import org.springframework.stereotype.Service;


@Service
public class ActorService {
    public final ActorRepository actorRepository;
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor getActor(int id) {
        return actorRepository.findById(id);
    }
    public void save(Actor actor) {
        actorRepository.save(actor);
    }
    public Actor ActorIsHere(String actorName, String actorLastName) {
        return actorRepository.findByFirstNameAndLastName(actorName, actorLastName);
    }
}
