package com.platform.blogging.config;


import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {


        return new OpenAPI()
                .info(new Info().title("Blog Application Beckend API")
                        .description("This is blog application project api and Developed by Aditi Singh")
                        .version("1.0"));
    }

}
