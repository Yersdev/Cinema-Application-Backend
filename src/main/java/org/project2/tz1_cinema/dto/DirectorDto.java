package org.project2.tz1_cinema.dto;

import lombok.*;

/**
 * Data Transfer Object for Director entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DirectorDto {

    /**
     * First name of the director.
     */
    private String firstName;

    /**
     * Last name of the director.
     */
    private String lastName;

    /**
     * Year of birth of the director.
     */
    private Integer yearOfBirth;
}
