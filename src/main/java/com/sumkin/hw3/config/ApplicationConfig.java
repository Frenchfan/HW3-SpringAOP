package com.sumkin.hw3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ApplicationConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()

                .info(new Info()
                        .title("Домашнее задание 3 - открытая школа T1")
                        .description("Логгирование с AOP + тестирование")
                        .version("1.0")

                );
    }
}
