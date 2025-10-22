package com.zexson.signor_p.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurer {

    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4321", "http://192.168.100.157:4321", "https://f238217cb58c.ngrok-free.app")
                        .allowedMethods("POST", "GET", "PUT")
                        .allowCredentials(true);
            }
        };
    }
}
