package org.project2.tz1_cinema.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for user registration.
 */
@Data
public class RegisterDto {

    /**
     * First name of the user, cannot be empty.
     */
    @NotEmpty
    private String name;

    /**
     * Last name of the user, cannot be empty.
     */
    @NotEmpty
    private String last_name;

    /**
     * Email of the user, must be valid.
     */
    @NotEmpty
    @Email
    private String email;

    /**
     * Password of the user, minimum length is 6 characters and must contain at least one special character.
     */
    @Size(min = 6, message = "Minimum password length is 6 characters")
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = "Пароль должен содержать хотя бы один специальный символ.")
    private String password;

    /**
     * Confirmation of the password.
     */
    private String confirmPassword;
}
