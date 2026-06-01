package com.com.er7.project_manag.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI OpenApiConfig() {
        return new OpenAPI()
                .info(new Info().title("Project Manager API").version("1.0").description(
                        "Documentação da Project Manager API. Project Manager é um sistema de projetos."));
    }
}
