package com.movietickets.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int movieId;
    private String title;
    private String genre;
    private double rating;
    private int duration; // in minutes
    private String description;
    private String posterUrl;
    private LocalDateTime releaseDate;
    private String language;
    private String certification;
    private String director;
    private double basePrice; // base ticket price

    public Movie(int movieId, String title, String genre, double rating, int duration) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.basePrice = 150.0; // Default base price
    }

    // Getters and Setters
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }

    public LocalDateTime getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDateTime releaseDate) { this.releaseDate = releaseDate; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getCertification() { return certification; }
    public void setCertification(String certification) { this.certification = certification; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    @Override
    public String toString() {
        return title + " (" + genre + ") - Rating: " + rating + "/10";
    }
}
