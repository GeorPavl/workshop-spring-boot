package gr.infoteam.workshop_spring_boot.utils.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Spring Workshop")
                        .description("Spring Boot REST API")
                        .version("v1.0.0")
        );

    }
}
