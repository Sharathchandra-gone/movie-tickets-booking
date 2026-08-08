package com.movietickets.service;

import com.movietickets.model.Movie;
import com.movietickets.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllActiveMovies() {
        return movieRepository.findByIsActiveTrue();
    }

    public Optional<Movie> getMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public List<Movie> searchMovies(String keyword) {
        return movieRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Movie> getTopRatedMovies() {
        return movieRepository.findTopRatedMovies();
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long movieId, Movie movieDetails) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        
        if (movieDetails.getTitle() != null) movie.setTitle(movieDetails.getTitle());
        if (movieDetails.getGenre() != null) movie.setGenre(movieDetails.getGenre());
        if (movieDetails.getRating() != null) movie.setRating(movieDetails.getRating());
        if (movieDetails.getDuration() != null) movie.setDuration(movieDetails.getDuration());
        if (movieDetails.getDescription() != null) movie.setDescription(movieDetails.getDescription());
        if (movieDetails.getBasePrice() != null) movie.setBasePrice(movieDetails.getBasePrice());
        
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}
