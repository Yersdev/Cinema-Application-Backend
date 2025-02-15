package org.project2.tz1_cinema.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object for Actor entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActorDto {

    /**
     * First name of the actor, with size constraints between 3 and 50 characters.
     */
    @Size(min = 3, max = 50, message = "Please, the name should be between 3 - 50 characters")
    private String firstName;

    /**
     * Last name of the actor.
     */
    private String lastName;

    /**
     * Year of birth of the actor, with minimum value 1900 and maximum value 2023.
     */
    @Min(value = 1900, message = "Year of birth must be greater than or equal to 1900")
    @Max(value = 2023, message = "Year of birth must be less than or equal to 2023")
    private Integer yearOfBirth;
}
