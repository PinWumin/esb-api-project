package com.esb.emp_seat_api.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")

                .allowedOrigins("*")
                // .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST")
                // .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                // .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
        // .exposedHeaders("*");

    }
}
