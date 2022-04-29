package com.sisprog.keyboard.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    public static final String AUTH_TAG = "Authentication service";
    public static final String REG_TAG = "Registration service";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sisprog.keyboard.controller")) // Пакет сканирования Swagger
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(AUTH_TAG, "API for user authentication by login and password"),
                        new Tag(REG_TAG, "API for creating new user with provided login and password"));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("NewsBag Swagger2 for RESTful API")
                .contact(new Contact("Ilya Fisenko and Dmitriy Gritsenko", "", ""))
                .version("1.0")
                .build();
    }
}
