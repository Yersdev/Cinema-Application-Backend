package org.project2.tz1_cinema.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс Spring, содержащий биновые настройки приложения.
 */
@Configuration
public class AppConfiguration {

    /**
     * Создаёт и настраивает бин {@link ModelMapper},
     * который используется для преобразования объектов между различными слоями приложения.
     *
     * @return настроенный экземпляр {@link ModelMapper}.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
