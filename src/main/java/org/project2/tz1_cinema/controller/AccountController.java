package org.project2.tz1_cinema.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.dto.RegisterDto;
import org.project2.tz1_cinema.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления аутентификацией и регистрацией пользователей.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    /**
     * Обрабатывает HTTP POST-запрос для регистрации нового пользователя.
     * <p>
     * Метод принимает данные для регистрации, валидирует их и передаёт в {@link UserService}
     * для выполнения процесса регистрации.
     *
     * @param registerDto DTO с данными для регистрации пользователя.
     * @param bindingResult объект {@link BindingResult} для обработки ошибок валидации.
     * @return {@link ResponseEntity} с HTTP-статусом 201 (Created) и сообщением об успешной регистрации.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult) {
        userService.registerUser(registerDto, bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь зарегистрирован успешно!");
    }
}
