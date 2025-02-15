/**
 * Service class for managing Actor entities.
 * Provides methods for saving and retrieving actors from the database.
 */
package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.model.Actor;
import org.project2.tz1_cinema.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    public final ActorRepository actorRepository;

    /**
     * Constructor for ActorService.
     * @param actorRepository repository for Actor entities.
     */
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    /**
     * Saves an actor entity.
     * @param actor the actor entity to save.
     */
    public void save(Actor actor) {
        actorRepository.save(actor);
    }

    /**
     * Finds an actor by their ID.
     * @param actorId the ID of the actor.
     * @return the actor entity found.
     */
    public Actor findById(int actorId) {
        return actorRepository.findById(actorId);
    }

    /**
     * Saves a list of actor entities.
     * @param actors the list of actors to save.
     */
    public void saveAll(List<Actor> actors) {
        actorRepository.saveAll(actors);
    }

}