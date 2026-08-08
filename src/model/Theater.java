package com.movietickets.model;

import java.io.Serializable;
import java.util.*;

public class Theater implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int theaterId;
    private String name;
    private String location;
    private int totalSeats;
    private List<Seat> seats;
    private int rows;
    private int seatsPerRow;

    public Theater(int theaterId, String name, String location, int rows, int seatsPerRow) {
        this.theaterId = theaterId;
        this.name = name;
        this.location = location;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.totalSeats = rows * seatsPerRow;
        this.seats = new ArrayList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        int seatId = 1;
        String[] rowLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K"};

        for (int i = 0; i < rows && i < rowLetters.length; i++) {
            for (int j = 1; j <= seatsPerRow; j++) {
                Seat.SeatType type = Seat.SeatType.NORMAL;
                // Premium seats in middle rows
                if (i >= 2 && i <= 5) {
                    type = Seat.SeatType.PREMIUM;
                }
                // Reclinable seats in back rows
                if (i >= 6) {
                    type = Seat.SeatType.RECLINABLE;
                }
                seats.add(new Seat(seatId++, rowLetters[i], j, type));
            }
        }
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.getStatus() == Seat.SeatStatus.AVAILABLE) {
                available.add(seat);
            }
        }
        return available;
    }

    public Seat getSeatByRowAndNumber(String row, int number) {
        for (Seat seat : seats) {
            if (seat.getRow().equals(row) && seat.getNumber() == number) {
                return seat;
            }
        }
        return null;
    }

    // Getters and Setters
    public int getTheaterId() { return theaterId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getTotalSeats() { return totalSeats; }
    public List<Seat> getSeats() { return seats; }
    public int getRows() { return rows; }
    public int getSeatsPerRow() { return seatsPerRow; }

    @Override
    public String toString() {
        return name + " - " + location;
    }
}
