package org.project2.tz1_cinema.dto;

import lombok.*;

/**
 * Data Transfer Object for User entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    /**
     * First name of the user.
     */
    private String name;

    /**
     * Last name of the user.
     */
    private String last_name;

    /**
     * Email of the user.
     */
    private String email;
}
