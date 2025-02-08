package org.project2.tz1_cinema.dto.converter;

import org.modelmapper.ModelMapper;
import org.project2.tz1_cinema.dto.RegisterDto;
import org.project2.tz1_cinema.model.Role;
import org.project2.tz1_cinema.model.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserConverter(ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

        this.modelMapper.typeMap(RegisterDto.class, Users.class)
                .addMappings(mapper -> {
                    mapper.skip(Users::setId);
                    mapper.map(RegisterDto::getName, Users::setName);
                    mapper.map(RegisterDto::getLast_name, Users::setLast_name);
                    mapper.map(RegisterDto::getEmail, Users::setEmail);
                });
    }

    public Users toEntity(RegisterDto registerDto) {
        Users user = modelMapper.map(registerDto, Users.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(Role.USER);
        return user;
    }
}
