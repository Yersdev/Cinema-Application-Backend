package org.project2.tz1_cinema.service;

import org.project2.tz1_cinema.model.Director;
import org.project2.tz1_cinema.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirectorService {

    private final DirectorRepository repo;

    @Autowired
    public DirectorService(DirectorRepository repo) {
        this.repo = repo;
    }

    public Director getDirector(int id) {
        Optional<Director> director = repo.findById(id);
        return director.orElse(null);
    }

    public Director save(Director director) {
        return repo.save(director);
    }
}
