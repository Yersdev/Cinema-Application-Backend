package org.project2.tz1_cinema.dto.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project2.tz1_cinema.dto.MovieDto;
import org.project2.tz1_cinema.model.Movie;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for transforming a list of {@link Movie} entities into a list of {@link MovieDto} objects.
 * Uses {@link ModelMapper} for object mapping.
 */
@Component
@RequiredArgsConstructor
public class MovieConverter {
    private final ModelMapper modelMapper;

    /**
     * Converts a list of {@link Movie} entities into a list of {@link MovieDto} objects.
     *
     * @param movieList the list of Movie entities to be converted
     * @return a list of corresponding MovieDto objects
     */
    public List<MovieDto> movieListToDtoList(List<Movie> movieList) {
        return movieList.stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }
}