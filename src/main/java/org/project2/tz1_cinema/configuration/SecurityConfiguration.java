package org.project2.tz1_cinema.configuration;

import lombok.RequiredArgsConstructor;
import org.project2.tz1_cinema.service.UserInfoUserDetailsService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурационный класс Spring Security для настройки безопасности веб-приложения.
 * <p>
 * Включает безопасность на уровне методов, управление сессиями,
 * шифрование паролей и аутентификацию через {@link UserInfoUserDetailsService}.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserInfoUserDetailsService userInfoUserDetailsServices;

    /**
     * Настраивает цепочку фильтров безопасности Spring Security.
     * <p>
     * - Отключает CSRF-защиту.
     * - Разрешает доступ к публичным URL-адресам, включая Swagger UI и документацию API.
     * - Требует аутентификации для всех остальных запросов.
     * - Настраивает вход через форму и перенаправляет после успешного входа на страницу с фильмами.
     * - Позволяет выход из системы всем пользователям.
     * - Устанавливает стратегию создания сессий как "если требуется".
     * - Устанавливает провайдера аутентификации.
     *
     * @param http объект {@link HttpSecurity} для конфигурации безопасности.
     * @return сконфигурированный объект {@link SecurityFilterChain}.
     * @throws Exception в случае ошибок конфигурации.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .permitAll()
                        .defaultSuccessUrl("/movies", true)
                        .successHandler((request, response, authentication) -> response.sendRedirect("/movies/"))
                )
                .logout(LogoutConfigurer::permitAll)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .authenticationProvider(authenticationProvider());
        return http.build();
    }

    /**
     * Создаёт бин для шифрования паролей с помощью {@link BCryptPasswordEncoder}.
     *
     * @return экземпляр {@link PasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Создаёт и настраивает провайдера аутентификации, используя {@link DaoAuthenticationProvider}.
     * <p>
     * Провайдер устанавливает сервис загрузки пользователей и шифратор паролей.
     *
     * @return настроенный {@link AuthenticationProvider}.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userInfoUserDetailsServices);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Создаёт бин менеджера аутентификации, используя переданную конфигурацию.
     *
     * @param config объект {@link AuthenticationConfiguration}, предоставляющий конфигурацию аутентификации.
     * @return экземпляр {@link AuthenticationManager}.
     * @throws Exception в случае ошибок получения менеджера аутентификации.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
