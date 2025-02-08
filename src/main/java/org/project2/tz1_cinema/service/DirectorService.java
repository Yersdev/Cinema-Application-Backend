package org.project2.tz1_cinema.service;

import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.model.Director;
import org.project2.tz1_cinema.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository repo;

    public Director save(Director director) {
        return repo.save(director);
    }
}
