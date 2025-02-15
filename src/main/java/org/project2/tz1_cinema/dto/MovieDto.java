package org.project2.tz1_cinema.dto;

import lombok.*;
import java.util.List;

/**
 * Data Transfer Object for Movie entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDto {

    /**
     * Title of the movie.
     */
    private String title;

    /**
     * Country where the movie was produced.
     */
    private String country;

    /**
     * Genre of the movie.
     */
    private String genre;

    /**
     * Release year of the movie.
     */
    private Integer releaseYear;

    /**
     * List of actors featured in the movie.
     */
    private List<ActorDto> actors;

    /**
     * Director of the movie.
     */
    private DirectorDto director;
}
