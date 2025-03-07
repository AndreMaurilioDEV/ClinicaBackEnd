package com.betrybe.Clinica.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;
import io.swagger.v3.oas.annotations.info.Contact;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestão Hospitalar",
                version = "1.0.0",
                description = "API para gerenciamento de consultas, pacientes e médicos em um hospital.",
                contact = @Contact(
                        name = "André Maurilio",
                        email = "andremaurilio@example.com",
                        url = "https://github.com/AndreMaurilioDEV"
                ),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT"
                )
        )
)
public class OpenApiConfig {
  @Bean
  public GroupedOpenApi api() {
    return GroupedOpenApi.builder()
            .group("v1")
            .packagesToScan("com.betrybe.Clinica")
            .build();
  }
}

