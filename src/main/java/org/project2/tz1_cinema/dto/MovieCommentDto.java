package org.project2.tz1_cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project2.tz1_cinema.model.Movie;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieCommentDto {
    private Movie movie;
    private String comment;
    private UserDto userdto;
}
