package com.movietickets;

import com.movietickets.model.Movie;
import com.movietickets.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieTicketsBookingApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MovieTicketsBookingApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieTicketsBookingApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(MovieRepository movieRepository) {
        return args -> {
            // Initialize sample movies
            if (movieRepository.count() == 0) {
                movieRepository.save(new Movie("Inception", "Sci-Fi", 8.8, 148, 150.0));
                movieRepository.save(new Movie("Avatar", "Sci-Fi", 7.8, 162, 150.0));
                movieRepository.save(new Movie("The Dark Knight", "Action", 9.0, 152, 150.0));
                movieRepository.save(new Movie("Interstellar", "Sci-Fi", 8.6, 169, 150.0));
                movieRepository.save(new Movie("Parasite", "Thriller", 8.5, 132, 150.0));
                movieRepository.save(new Movie("The Shawshank Redemption", "Drama", 9.3, 142, 150.0));
                System.out.println("✅ Sample movies loaded successfully!");
            }
        };
    }
}
