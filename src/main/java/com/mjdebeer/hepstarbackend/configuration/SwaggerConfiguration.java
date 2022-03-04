package com.mjdebeer.hepstarbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;

import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .directModelSubstitute(LocalTime.class, String.class)
                .select()
                .apis(withMethodAnnotation(com.mjdebeer.hepstarbackend.configuration.SwaggerIncluded.class))
                .build();
    }

}
