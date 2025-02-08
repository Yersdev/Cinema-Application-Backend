package org.project2.tz1_cinema.service;

import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.model.Movie;
import org.project2.tz1_cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getByReleaseYear(int year) {
        return movieRepository.getMovieByReleaseYear(year);
    }

    public void save(Movie movie){
        movieRepository.save(movie);
    }
}