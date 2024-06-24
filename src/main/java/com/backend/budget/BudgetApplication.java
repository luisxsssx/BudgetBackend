package com.backend.budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BudgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://127.0.0.1:5500") // Reemplaza con la URL de tu frontend
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Agrega OPTIONS

						.allowedHeaders("*")
						.allowCredentials(true) // Habilita las credenciales
						.maxAge(3600); // Controla el tiempo de caché de las opciones preflight
			}
		};
	}

}