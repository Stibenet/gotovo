package ru.gotovoweb.gotovobackend.config; // Замените на ваш пакет

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Применить ко всем путям
                .allowedOriginPatterns("http://localhost:5173") // Разрешить origin фронтенда
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешить нужные методы
                .allowedHeaders("*") // Разрешить все заголовки
                .allowCredentials(true); // Разрешить отправку учетных данных (например, JWT в заголовке Authorization)
    }
}