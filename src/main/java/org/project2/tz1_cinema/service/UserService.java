/**
 * Service class for managing user-related operations such as registration and retrieval.
 * Provides transactional user registration with validation checks and entity conversion.
 */
package org.project2.tz1_cinema.service;

import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.dto.RegisterDto;
import org.project2.tz1_cinema.dto.converter.UserConverter;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repository.UserRepository;
import org.project2.tz1_cinema.util.BindCheckerUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    /**
     * Registers a new user if the provided email is not already in use.
     * Performs validation checks on the provided registration data.
     * @param registerDto Data transfer object containing user registration details.
     * @param bindingResult Contains validation results.
     * @throws IllegalArgumentException if the email is already used or validation fails.
     */
    @Transactional
    public void registerUser(RegisterDto registerDto, BindingResult bindingResult) {
        BindCheckerUtil.checkBind(bindingResult);
        if (userRepository.findByEmail(registerDto.getEmail()) == null) {
            throw new IllegalArgumentException("Email уже используется");
        }
        Users user = userConverter.toEntity(registerDto);
        userRepository.save(user);
    }

    /**
     * Finds a user by their email.
     * @param email the email of the user.
     * @return the Users entity if found, otherwise null.
     */
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Checks if a user with the specified email exists.
     * @param email the email to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean userExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}