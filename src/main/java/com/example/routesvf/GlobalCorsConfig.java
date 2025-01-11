package com.example.routesvf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Autorise toutes les origines
        config.addAllowedHeader("*"); // Autorise tous les en-têtes
        config.addAllowedMethod("*"); // Autorise toutes les méthodes (GET, POST, OPTIONS, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Applique cette configuration à tous les endpoints

        return new CorsFilter(source);
    }
}
