package com.movietickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @Column(nullable = false)
    private String selectedSeats; // Comma-separated seat IDs

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private String status; // PENDING, CONFIRMED, CANCELLED

    @Column(nullable = false)
    private LocalDateTime showDateTime;

    @Column(name = "booking_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime bookingDate;

    @Column(name = "payment_method")
    private String paymentMethod; // CREDIT_CARD, DEBIT_CARD, UPI, NET_BANKING

    @Column(name = "confirmation_number", unique = true)
    private String confirmationNumber;

    public Booking(User user, Movie movie, Theater theater, String selectedSeats, 
                   Double totalPrice, LocalDateTime showDateTime) {
        this.user = user;
        this.movie = movie;
        this.theater = theater;
        this.selectedSeats = selectedSeats;
        this.totalPrice = totalPrice;
        this.showDateTime = showDateTime;
        this.status = "PENDING";
        this.bookingDate = LocalDateTime.now();
    }
}
