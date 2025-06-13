package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer { // Assurez-vous d'implémenter WebMvcConfigurer
    @Override // Ajoutez cette annotation
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permet toutes les routes
                .allowedOrigins("http://localhost:3000") // Remplacez par l'URL de votre front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Méthodes autorisées
                .allowCredentials(true); // Si vous avez besoin d'envoyer des cookies
    }
}
