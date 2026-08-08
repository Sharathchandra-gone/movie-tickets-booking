package com.movietickets.model;

import java.io.Serializable;

public class Seat implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum SeatType {
        NORMAL(100),
        PREMIUM(200),
        RECLINABLE(300);

        private final double priceMultiplier;

        SeatType(double priceMultiplier) {
            this.priceMultiplier = priceMultiplier;
        }

        public double getPriceMultiplier() {
            return priceMultiplier;
        }
    }

    public enum SeatStatus {
        AVAILABLE,
        BOOKED,
        SELECTED
    }

    private int seatId;
    private String row; // A, B, C, etc.
    private int number; // 1, 2, 3, etc.
    private SeatType type;
    private SeatStatus status;
    private double price;

    public Seat(int seatId, String row, int number, SeatType type) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
        this.price = type.getPriceMultiplier();
    }

    // Getters and Setters
    public int getSeatId() { return seatId; }
    public String getRow() { return row; }
    public int getNumber() { return number; }
    public SeatType getType() { return type; }
    public SeatStatus getStatus() { return status; }
    public void setStatus(SeatStatus status) { this.status = status; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return row + number;
    }

    public String getDisplayInfo() {
        return String.format("%s%d (%s) - ₹%.2f", row, number, type.name(), price);
    }
}
