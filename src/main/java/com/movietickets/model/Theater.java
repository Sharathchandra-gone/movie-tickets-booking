package com.movietickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "theaters")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer rows;

    @Column(nullable = false)
    private Integer seatsPerRow;

    @Column(length = 500)
    private String address;

    @Column(length = 500)
    private String imageUrl;

    private String city;
    private String state;
    private String phone;

    private Boolean isActive = true;

    public Theater(String name, String location, Integer rows, Integer seatsPerRow) {
        this.name = name;
        this.location = location;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.totalSeats = rows * seatsPerRow;
        this.isActive = true;
    }
}
