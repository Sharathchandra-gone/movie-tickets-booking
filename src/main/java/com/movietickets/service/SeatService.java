package com.movietickets.service;

import com.movietickets.model.Seat;
import com.movietickets.model.Theater;
import com.movietickets.repository.SeatRepository;
import com.movietickets.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final TheaterRepository theaterRepository;

    public List<Seat> getAvailableSeats(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
        return seatRepository.findByTheaterAndStatus(theater, Seat.SeatStatus.AVAILABLE);
    }

    public List<Seat> getSeatsByTheater(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
        return seatRepository.findByTheater(theater);
    }

    public Optional<Seat> getSeatById(Long seatId) {
        return seatRepository.findById(seatId);
    }

    public Seat updateSeatStatus(Long seatId, Seat.SeatStatus status) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        seat.setStatus(status);
        return seatRepository.save(seat);
    }

    public List<Seat> initializeSeatsForTheater(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
        
        List<Seat> seats = new ArrayList<>();
        String[] rowLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K"};

        for (int i = 0; i < theater.getRows() && i < rowLetters.length; i++) {
            for (int j = 1; j <= theater.getSeatsPerRow(); j++) {
                Seat.SeatType type = Seat.SeatType.NORMAL;
                if (i >= 2 && i <= 5) type = Seat.SeatType.PREMIUM;
                if (i >= 6) type = Seat.SeatType.RECLINABLE;
                
                Seat seat = new Seat(theater, rowLetters[i], j, type);
                seats.add(seat);
            }
        }
        
        return seatRepository.saveAll(seats);
    }
}
