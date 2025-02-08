package org.project2.tz1_cinema.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDto {
    private String title;
    private String country;
    private String genre;
    private Integer releaseYear;
    private List<ActorDto> actors;
    private DirectorDto director;
}
