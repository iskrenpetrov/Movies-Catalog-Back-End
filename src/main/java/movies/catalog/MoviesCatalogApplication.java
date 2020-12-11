package movies.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"movies.catalog"})

public class MoviesCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesCatalogApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/").allowedOrigins("http://localhost:3000");
                registry.addMapping("/categories").allowedOrigins("http://localhost:3000");
                registry.addMapping("/categories/{id}").allowedOrigins("http://localhost:3000");
                registry.addMapping("/categories/{id}/movies").allowedOrigins("http://localhost:3000");
                registry.addMapping("/categories/{id}/movies/{id2}").allowedOrigins("http://localhost:3000");
            }
        };
    }

}
