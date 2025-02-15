package org.project2.tz1_cinema.dto.converter;

import org.project2.tz1_cinema.dto.ActorDto;
import org.project2.tz1_cinema.model.Actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Converter class for converting Actor entities to DTOs and vice versa.
 */
public class ActorConverter {

    /**
     * Converts an Actor entity to an ActorDto.
     * @param actor Actor entity to convert.
     * @return Converted ActorDto or null if input actor is null.
     */
    public static ActorDto actorToDto(Actor actor) {
        if (actor == null) return null;
        ActorDto actorDto = new ActorDto();
        actorDto.setFirstName(actor.getFirstName());
        actorDto.setLastName(actor.getLastName());
        actorDto.setYearOfBirth(actor.getYearOfBirth());
        return actorDto;
    }

    /**
     * Converts a list of Actor entities to a list of ActorDto objects.
     * @param actors List of Actor entities to convert.
     * @return List of ActorDto objects or empty list if input is null or empty.
     */
    public static List<ActorDto> actorListToDtoList(List<Actor> actors) {
        if (actors == null || actors.isEmpty()) return Collections.emptyList();
        List<ActorDto> actorDtos = new ArrayList<>();
        for (Actor actor : actors) actorDtos.add(actorToDto(actor));
        return actorDtos;
    }

    /**
     * Converts an ActorDto to an Actor entity.
     * @param actorDto ActorDto object to convert.
     * @return Converted Actor entity.
     */
    public static Actor dtoToActor(ActorDto actorDto) {
        Actor actor = new Actor();
        actor.setFirstName(actorDto.getFirstName());
        actor.setLastName(actorDto.getLastName());
        actor.setYearOfBirth(actorDto.getYearOfBirth());
        return actor;
    }
}
