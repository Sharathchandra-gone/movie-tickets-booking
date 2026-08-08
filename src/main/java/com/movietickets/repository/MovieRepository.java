package com.movietickets.repository;

import com.movietickets.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenre(String genre);
    List<Movie> findByIsActiveTrue();
    List<Movie> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT m FROM Movie m WHERE m.isActive = true ORDER BY m.rating DESC")
    List<Movie> findTopRatedMovies();
}
