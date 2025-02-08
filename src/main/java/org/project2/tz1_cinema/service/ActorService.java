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

    public void save(Actor actor) {
        actorRepository.save(actor);
    }
}
