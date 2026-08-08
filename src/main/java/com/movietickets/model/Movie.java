package com.movietickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private Integer duration; // in minutes

    @Column(length = 1000)
    private String description;

    @Column(length = 500)
    private String posterUrl;

    private String language;
    private String certification; // U, UA, A, S
    private String director;

    @Column(nullable = false)
    private Double basePrice; // base ticket price

    private Boolean isActive = true;

    public Movie(String title, String genre, Double rating, Integer duration, Double basePrice) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.basePrice = basePrice;
        this.isActive = true;
    }
}
