/**
 * Service class for managing Director entities.
 * Provides methods to save Director data to the database.
 */
package org.project2.tz1_cinema.service;

import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.model.Director;
import org.project2.tz1_cinema.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    /**
     * Saves a Director entity to the database.
     * @param director the Director entity to save.
     * @return the saved Director entity.
     */
    public Director save(Director director) {
        return directorRepository.save(director);
    }

    /**
     * Creates and saves a new Director entity.
     * @param firstName the first name of the director.
     * @param lastName the last name of the director.
     * @param yearOfBirth the year of birth of the director.
     */
    public void save(String firstName, String lastName, Integer yearOfBirth) {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setLastName(lastName);
        director.setYearOfBirth(yearOfBirth);
        directorRepository.save(director);
    }
}
