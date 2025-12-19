package com.seuprojeto.appointmentsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Appointment System API")
                        .version("1.0.0")
                        .description("API RESTful para gerenciamento de agendamentos de serviços")
                        .contact(new Contact()
                                .name("Seu Nome")
                                .email("seu.email@example.com")
                                .url("https://github.com/seu-usuario"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
