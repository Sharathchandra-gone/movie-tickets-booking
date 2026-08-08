package com.movietickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @Column(nullable = false)
    private String row;

    @Column(nullable = false)
    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

    @Column(nullable = false)
    private Double price;

    public enum SeatType {
        NORMAL(100.0),
        PREMIUM(200.0),
        RECLINABLE(300.0);

        public final double priceMultiplier;

        SeatType(double priceMultiplier) {
            this.priceMultiplier = priceMultiplier;
        }
    }

    public enum SeatStatus {
        AVAILABLE,
        BOOKED,
        SELECTED
    }

    public Seat(Theater theater, String row, Integer number, SeatType type) {
        this.theater = theater;
        this.row = row;
        this.number = number;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
        this.price = type.priceMultiplier;
    }

    public String getDisplayInfo() {
        return String.format("%s%d (%s) - ₹%.2f", row, number, type.name(), price);
    }
}
