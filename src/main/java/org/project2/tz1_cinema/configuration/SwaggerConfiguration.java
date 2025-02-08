package org.project2.tz1_cinema.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Swagger для генерации документации API.
 * Включает настройку общей информации о API, а также группировку маршрутов по категориям.
 */
@Configuration
public class SwaggerConfiguration {

    /**
     * Создает и настраивает объект OpenAPI, который будет использоваться для генерации документации API.
     * Включает общую информацию о API, такую как название, версия и описание.
     *
     * @return объект OpenAPI с информацией о API
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("People API")
                        .version("1.0")
                        .description("API для управления людьми"));
    }

    /**
     * Создает и настраивает группу маршрутов для аутентификации.
     * Эта группа включает все маршруты, начинающиеся с "/auth/**".
     *
     * @return объект GroupedOpenApi для маршрутов аутентификации
     */
    @Bean
    public GroupedOpenApi authentificationApi() {
        return GroupedOpenApi.builder()
                .group("authentification")
                .pathsToMatch("/auth/**")
                .build();
    }
}
