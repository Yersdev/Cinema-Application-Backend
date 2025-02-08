package org.project2.tz1_cinema.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {
    private String movie_title;
    private String firstName;
    private String lastName;
    private String email;
    private String comments;
}
