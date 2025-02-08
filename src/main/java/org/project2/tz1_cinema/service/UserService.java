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

    @Transactional
    public void registerUser(RegisterDto registerDto, BindingResult bindingResult) {

        BindCheckerUtil.checkBind(bindingResult);
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email уже используется");
        }
        Users user = userConverter.toEntity(registerDto);
        userRepository.save(user);
    }
}
