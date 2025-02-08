package org.project2.tz1_cinema.configuration;

import lombok.extern.slf4j.Slf4j;
import org.project2.tz1_cinema.model.Role;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repository.UserRepository;
import org.project2.tz1_cinema.service.UserInfoUserDetailsService;
import org.project2.tz1_cinema.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

    private final UserInfoUserDetailsService userInfoUserDetailsServices;
    private final UserService userService;

    public SecurityConfig(UserService userService, UserInfoUserDetailsService userInfoUserDetailsServices) {
        this.userService = userService;
        this.userInfoUserDetailsServices = userInfoUserDetailsServices;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .anyRequest().authenticated() // Все остальные запросы требуют авторизации
                )
                .formLogin(form -> form
                        .permitAll()
                        .defaultSuccessUrl("/movies", true)
                        .successHandler((request, response, authentication) -> {
                            String username = authentication.getName();
                            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                            log.info("Пользователь {} успешно вошёл в систему с ролями: {}", username, authorities);
                            response.sendRedirect("/movies/");
                        })
                ) // Используем стандартную страницу входа Spring Security
                .logout(LogoutConfigurer::permitAll)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .authenticationProvider(authenticationProvider());

        log.info("Настройка SecurityFilterChain завершена. Проверка роли пользователей будет осуществляться.");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userInfoUserDetailsServices);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Создание администратора по умолчанию
            if (userInfoRepository.findByEmail("admin@example.com").isEmpty()) {
                Users admin = new Users();
                admin.setName("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userInfoRepository.save(admin);
            }

            // Создание пользователя по умолчанию
            if (userInfoRepository.findByEmail("user@example.com").isEmpty()) {
                Users user = new Users();
                user.setName("user");
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);
                userInfoRepository.save(user);
            }
        };
    }

    // Регистрация нового пользователя
    @Bean
    public CommandLineRunner registerUser(UserRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userInfoRepository.findByEmail("newuser@example.com").isEmpty()) {
                Users newUser = new Users();
                newUser.setName("newuser");
                newUser.setEmail("newuser@example.com");
                newUser.setPassword(passwordEncoder.encode("newuser123"));
                newUser.setRole(Role.USER);
                userInfoRepository.save(newUser);
                log.info("Новый пользователь {} зарегистрирован.", newUser.getEmail());
            }
        };

    }

}
