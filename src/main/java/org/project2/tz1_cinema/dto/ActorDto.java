package org.project2.tz1_cinema.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActorDto {



    @Size(min = 3, max = 50, message = "Please, the name should be between 3 - 50 characters")
    private String firstName;

    private String lastName;
    @Min(value = 1900, message = "Year of birth must be greater than or equal to 1900")
    @Max(value = 2023, message = "Year of birth must be less than or equal to 2023")

    private Integer yearOfBirth;
}
