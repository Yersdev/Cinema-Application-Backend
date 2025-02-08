package org.project2.tz1_cinema.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.dto.RegisterDto;
import org.project2.tz1_cinema.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult) {
        userService.registerUser(registerDto, bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь зарегистрирован успешно!");
    }
}
